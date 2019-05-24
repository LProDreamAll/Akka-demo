package lhh.day03.watch.lhh.day03.Router;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.actor.UntypedActor;
import akka.routing.ActorRefRoutee;
import akka.routing.RandomRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import lhh.day03.watch.lhh.day03.Inbox.InboxTest;
import com.typesafe.config.ConfigFactory;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Copyright (C), 2019-2019
 * FileName: RouterTest
 * Author:   s·D·bs
 * Date:     2019/5/23 14:31
 * Description: 负载均衡或者说路由策略
 * 一组actor提供相同的服务，我们在调用任务的时候只需要选择其中一个actor进行处理即可。
 * 其实这就是一个负载均衡或者说路由策略，akka作为一个高性能支持并发的actor模型，可以用来作为任务调度集群使用，当然负载均衡就是其本职工作*了，akka提供了Router来进行消息的调度。
 * Motto: 0.45%
 */
public class RouterTest extends UntypedActor {

    public static AtomicBoolean flag = new AtomicBoolean(true);

    public Router router;

    {
        ArrayList<Routee> routees = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            //借用上面的 inboxActor
            ActorRef worker = getContext().actorOf(Props.create(InboxTest.class), "worker_" + i);
            getContext().watch(worker);//监听
            routees.add(new ActorRefRoutee(worker));
        }
        /*
          RoundRobinRoutingLogic: 轮询
          BroadcastRoutingLogic: 广播
          RandomRoutingLogic: 随机
          SmallestMailboxRoutingLogic: 空闲
         */
//        router = new Router(new RoundRobinRoutingLogic(), routees);
        router = new Router(new RandomRoutingLogic(), routees);
    }

    @Override
    public void onReceive(Object msg) throws Exception {
        if (msg instanceof InboxTest.Msg) {
            router.route(msg, getSender());//进行路由转发
        } else if (msg instanceof Terminated) {
            //发生中断，将该actor删除。当然这里可以参考之前的actor重启策略，进行优化，为了简单，这里仅进行删除处理
            router = router.removeRoutee(((Terminated) msg).actor());
            System.out.println(((Terminated) msg).actor().path() + " 该actor已经删除。router.size=" + router.routees().size());
            if (router.routees().size() == 0) {//没有可用actor了
                System.out.print("没有可用actor了，系统关闭。");
                flag.compareAndSet(true, false);
                getContext().system().shutdown();
            }
        } else {
            unhandled(msg);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ActorSystem system = ActorSystem.create("strategy", ConfigFactory.load("akka.config"));
        ActorRef routerTest = system.actorOf(Props.create(RouterTest.class), "RouterTest");
        int i = 1;
        while (flag.get()) {
            routerTest.tell(InboxTest.Msg.WORKING, ActorRef.noSender());
            //一直发送
            if (i % 10 == 0) routerTest.tell(InboxTest.Msg.CLOSE, ActorRef.noSender());
            Thread.sleep(500);
            i++;
        }
    }
}
