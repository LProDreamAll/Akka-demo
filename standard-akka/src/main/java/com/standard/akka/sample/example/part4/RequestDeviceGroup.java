package com.standard.akka.sample.example.part4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Set;

/**
 * Copyright (C), 2019-2019
 * FileName: RequestDeviceGroup
 * Author:   s·D·bs
 * Date:     2019/5/27 17:01
 * Description: 删除actor功能，新的查询功能
 * Motto: 0.45%
 */
@RequiredArgsConstructor(staticName = "of")
public class RequestDeviceGroup extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    final Map<String, ActorRef> deviceIdToActor = Maps.newHashMap();

    final Map<ActorRef, String> actorToDeviceId = Maps.newHashMap();

    final String groupId;

    public static Props props(String groupId) {
        //不能直接进行new 操作
        return Props.create(RequestDeviceGroup.class, () -> RequestDeviceGroup.of(groupId));
    }

    @Override
    public void preStart() {
        log.info("RequestDeviceGroup :[{}]  started", groupId);
    }

    @Override
    public void postStop() {
        log.info("RequestDeviceGroup :[{}]  stopped", groupId);
    }

    private void onTrackDevice(Device.RequestTrackDevice trackMsg) {
        if (this.groupId.equals(trackMsg.groupId)) {
            ActorRef deviceActor = deviceIdToActor.get(trackMsg.deviceId);
            if (deviceActor != null) {
                deviceActor.forward(trackMsg, getContext());
            } else {
                log.info("Creating device actor for :[{}] ", trackMsg.deviceId);
                deviceActor = getContext()
                        .actorOf(Device.props(groupId, trackMsg.deviceId), "device-" + trackMsg.deviceId);
                getContext().watch(deviceActor);
                actorToDeviceId.put(deviceActor, trackMsg.deviceId);
                deviceIdToActor.put(trackMsg.deviceId, deviceActor);
                deviceActor.forward(trackMsg, getContext());
            }
        } else {
            log.warning(
                    "Ignoring TrackDevice request for :[{}] . This actor is responsible for :[{}] .",
                    groupId,
                    this.groupId);
        }
    }

    private void onDeviceList(RequestDeviceList r) {
        getSender().tell(new ReplyDeviceList(r.requestId, deviceIdToActor.keySet()), getSelf());
    }


    /**
     * 终止
     *
     * @param t
     */
    private void onTerminated(Terminated t) {
        ActorRef deviceActor = t.getActor();
        String deviceId = actorToDeviceId.get(deviceActor);
        log.info("Device actor for :[{}]  has been terminated", deviceId);
        actorToDeviceId.remove(deviceActor);
        deviceIdToActor.remove(deviceId);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Device.RequestTrackDevice.class, this::onTrackDevice)
                .match(RequestDeviceList.class, this::onDeviceList)
                .match(Terminated.class, this::onTerminated)
                .build();
    }

    /**
     * 请求查询的列表
     */
    @RequiredArgsConstructor(staticName = "of")
    public static final class RequestDeviceList {

        @NonNull
        final long requestId;

    }

    @RequiredArgsConstructor(staticName = "of")
    @Getter
    public static final class ReplyDeviceList {

        @NonNull
        final long requestId;
        @NonNull
        final Set<String> ids;
    }

}
