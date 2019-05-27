package com.standard.akka.sample.example.part5;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Cancellable;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.standard.akka.sample.example.part4.Device;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import scala.concurrent.duration.FiniteDuration;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Copyright (C), 2019-2019
 * FileName: DeviceGroupQuery
 * Author:   s·D·bs
 * Date:     2019/5/27 18:03
 * Description: 实现actor查询
 * <p>
 * 定义查询actor：
 * 要查询的活动设备actor的快照和ID。
 * 启动查询的请求的ID（以便我们可以将其包含在回复中）。
 * 发送查询的actor的引用。我们会直接将回复发送给这位演员。
 * 截止日期，指示查询应等待回复的时间。使这个参数将简化测试。
 * <p>
 * 调度查询超时： 内置的调度程序工具，
 * 我们从中获取调度程序ActorSystem，而调度程序可以从actor的上下文中访问：。这需要一个将执行计时器任务本身的线程池。在我们的例子中，我们通过传入使用与actor相同的调度程序。getContext().getSystem().scheduler() ExecutionContext getContext().getDispatcher()
 * 该方法将指定的消息安排到将来，并将其发送给actor 。 scheduler.scheduleOnce(time, actorRef, message, executor, sender)messagetimeactorRef
 * <p>
 * <p>
 * <p>
 * Motto: 0.45%
 */

public class DeviceGroupQuery extends AbstractActor {

    /**
     * 创建一个表示查询超时的消息
     */
    public static final class CollectionTimeout {

    }

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    final Map<ActorRef, String> actorToDeviceId;
    final long requestId;
    final ActorRef requester;
    Cancellable queryTimeoutTimer;

    public DeviceGroupQuery(
            Map<ActorRef, String> actorToDeviceId,
            long requestId,
            ActorRef requester,
            FiniteDuration timeout) {
        this.actorToDeviceId = actorToDeviceId;
        this.requestId = requestId;
        this.requester = requester;
        queryTimeoutTimer = getContext()
                .getSystem()
                .scheduler()
                .scheduleOnce(
                        timeout,
                        getSelf(),
                        new CollectionTimeout(),
                        getContext().getDispatcher(),
                        getSelf());
    }

    public static Props props(
            Map<ActorRef, String> actorToDeviceId,
            long requestId,
            ActorRef requester,
            FiniteDuration timeout) {
        return Props.create(
                DeviceGroupQuery.class,
                () -> new DeviceGroupQuery(actorToDeviceId, requestId, requester, timeout));
    }

    @Override
    public void preStart() {
        for (ActorRef deviceActor : actorToDeviceId.keySet()) {
            getContext().watch(deviceActor);
            deviceActor.tell(Device.ReadTemperature.of(0L), getSelf());
        }
    }

    @Override
    public void postStop() {
        queryTimeoutTimer.cancel();
    }

    /**
     * @return akka.actor.AbstractActor.Receive
     * @Author s·D·bs
     * @Description 不是receive直接定义，而是委托waitingForReplies创建一个函数Receive。
     * 该waitingForReplies函数将跟踪两个不断变化的值：一个Map已经收到的回复 一个Set我们还在等待的actor
     * 有三个活动可以采取行动：
     * 可以RespondTemperature从其中一个设备收到消息。
     * 可以收到Terminated已同时停止的设备actor 的消息。
     * 可以到达截止日期并收到一个CollectionTimeout。
     * 在前两种情况下，我们需要跟踪回复，我们现在将其委托给一个方法receivedResponse。
     * 在超时的情况下，我们需要简单地接受所有尚未回复的演员（集合的成员stillWaiting）并DeviceTimedOut在最终答复中添加状态。
     * 然后我们用收集的结果回复查询的提交者并停止查询actor。
     * @Date 18:11 2019/5/27
     * @Param []
     */
    @Override
    public Receive createReceive() {
        return waitingForReplies(new HashMap<>(), actorToDeviceId.keySet());
    }

    public Receive waitingForReplies(
            Map<String, DeviceGroupQuery.TemperatureReading> repliesSoFar, Set<ActorRef> stillWaiting) {
        return receiveBuilder()
                .match(
                        Device.RespondTemperature.class,
                        r -> {
                            ActorRef deviceActor = getSender();
                            DeviceGroupQuery.TemperatureReading reading =
                                    r.value
                                            .map(v -> (DeviceGroupQuery.TemperatureReading) new DeviceGroupQuery.Temperature(v))
                                            .orElse(DeviceGroupQuery.TemperatureNotAvailable.INSTANCE);
                            receivedResponse(deviceActor, reading, stillWaiting, repliesSoFar);
                        })
                .match(
                        Terminated.class,
                        t -> {
                            receivedResponse(
                                    t.getActor(),
                                    DeviceGroupQuery.DeviceNotAvailable.INSTANCE,
                                    stillWaiting,
                                    repliesSoFar);
                        })
                .match(
                        CollectionTimeout.class,
                        t -> {
                            Map<String, DeviceGroupQuery.TemperatureReading> replies = Maps.newHashMap(repliesSoFar);
                            for (ActorRef deviceActor : stillWaiting) {
                                String deviceId = actorToDeviceId.get(deviceActor);
                                replies.put(deviceId, DeviceGroupQuery.DeviceTimedOut.INSTANCE);
                            }
                            requester.tell(new DeviceGroupQuery.RespondAllTemperatures(requestId, replies), getSelf());
                            getContext().stop(getSelf());
                        })
                .build();

    }

    public void receivedResponse(
            ActorRef deviceActor,
            DeviceGroupQuery.TemperatureReading reading,
            Set<ActorRef> stillWaiting,
            Map<String, DeviceGroupQuery.TemperatureReading> repliesSoFar) {
        getContext().unwatch(deviceActor);
        String deviceId = actorToDeviceId.get(deviceActor);

        Set<ActorRef> newStillWaiting = Sets.newHashSet(stillWaiting);
        newStillWaiting.remove(deviceActor);

        Map<String, DeviceGroupQuery.TemperatureReading> newRepliesSoFar = new HashMap<>(repliesSoFar);
        newRepliesSoFar.put(deviceId, reading);
        if (newStillWaiting.isEmpty()) {
            requester.tell(new DeviceGroupQuery.RespondAllTemperatures(requestId, newRepliesSoFar), getSelf());
            getContext().stop(getSelf());
        } else {
            getContext().become(waitingForReplies(newRepliesSoFar, newStillWaiting));
        }
    }

    @RequiredArgsConstructor(staticName = "of")
    public static final class RequestAllTemperatures {

        @NonNull
        final long requestId;
    }

    @RequiredArgsConstructor(staticName = "of")
    public static final class RespondAllTemperatures {

        @NonNull
        public final long requestId;
        @NonNull
        public final Map<String, TemperatureReading> temperatures;
    }

    public static interface TemperatureReading {

    }

    @RequiredArgsConstructor(staticName = "of")
    public static final class Temperature implements TemperatureReading {

        @NonNull
        public final double value;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Temperature that = (Temperature) o;

            return Double.compare(that.value, value) == 0;
        }

        @Override
        public int hashCode() {
            long temp = Double.doubleToLongBits(value);
            return (int) (temp ^ (temp >>> 32));
        }

        @Override
        public String toString() {
            return "Temperature{" + "value=" + value + '}';
        }
    }


    public enum TemperatureNotAvailable implements TemperatureReading {
        INSTANCE
    }

    public enum DeviceNotAvailable implements TemperatureReading {
        INSTANCE
    }

    public enum DeviceTimedOut implements TemperatureReading {
        INSTANCE
    }
}

