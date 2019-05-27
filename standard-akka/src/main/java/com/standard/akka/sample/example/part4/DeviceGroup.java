package com.standard.akka.sample.example.part4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.google.common.collect.Maps;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * Copyright (C), 2019-2019
 * FileName: DeviceGroup
 * Author:   s·D·bs
 * Date:     2019/5/27 15:40
 * Description:设备组
 * 处理注册请求
 * Motto: 0.45%
 */
@RequiredArgsConstructor(staticName = "of")
public class DeviceGroup extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);

    @NonNull
    final String groupId;

    public static Props props(String groupId) {
        return Props.create(DeviceGroup.class, () -> DeviceGroup.of(groupId));
    }

    final Map<String, ActorRef> deviceIdToActor = Maps.newHashMap();
    @Override
    public void preStart() {
        log.info("DeviceGroup :[{}] started", groupId);
    }

    @Override
    public void postStop() {
        log.info("DeviceGroup :[{}] stopped", groupId);
    }

    private void onTrackDevice(Device.RequestTrackDevice trackMsg) {
        if (this.groupId.equals(trackMsg.groupId)) {
            ActorRef deviceActor = deviceIdToActor.get(trackMsg.deviceId);
            if (deviceActor != null) {
                log.info("TrackMsg :[{}] ,getContext():[{}]", trackMsg, getContext());
                deviceActor.forward(trackMsg, getContext());
            } else {
                log.info("Creating device actor for :[{}]", trackMsg.deviceId);
                deviceActor = getContext().actorOf(Device.props(groupId, trackMsg.deviceId), "device-" + trackMsg.deviceId);
                deviceIdToActor.put(trackMsg.deviceId, deviceActor);
                deviceActor.forward(trackMsg, getContext());
            }
        } else {
            log.warning(
                    "Ignoring TrackDevice request for :[{}]. This actor is responsible for :[{}].",
                    groupId,
                    this.groupId);
        }
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Device.RequestTrackDevice.class, this::onTrackDevice)
                .build();
    }
}
