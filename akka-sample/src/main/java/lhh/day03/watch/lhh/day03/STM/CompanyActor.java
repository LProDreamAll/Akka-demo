package lhh.day03.watch.lhh.day03.STM;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.transactor.Coordinated;
import scala.concurrent.stm.Ref;
import scala.concurrent.stm.japi.STM;

/**
 * Copyright (C), 2019-2019
 * FileName: CompanyActor
 * Author:   s·D·bs
 * Date:     2019/5/23 17:07
 * Description: 公司actor
 * Motto: 0.45%
 */
public class CompanyActor extends UntypedActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().system(), this);
    private Ref.View<Integer> count = STM.newRef(40);//定义账户余额

    @Override
    public void onReceive(Object o) throws Exception {
        if (o instanceof Coordinated) {
            Coordinated coordinated = (Coordinated) o;
            //传递过来的参数，减多少。
            int downCount = (int) coordinated.getMessage();
            //通知employeeActor增加费用
            STMTest.employeeActor.tell(coordinated.coordinate(downCount), getSelf());

            try {
                coordinated.atomic(() -> {
                    if (count.get() < downCount) throw new RuntimeException("余额不足！");
                    STM.increment(count, -downCount);//减余额
                });
            } catch (Exception e) {
                log.error("CompanyActor coordinated  atomic error :{}", e);
            }
        } else if ("getCount".equals(o)) {
            getSender().tell(count.get(), getSelf());
        } else {
            unhandled(o);
        }
    }
}
