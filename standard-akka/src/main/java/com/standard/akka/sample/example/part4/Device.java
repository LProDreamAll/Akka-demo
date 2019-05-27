package com.standard.akka.sample.example.part4;

import akka.actor.AbstractActor;
import akka.actor.Props;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * Copyright (C), 2019-2019
 * FileName: Device
 * Author:   s·D·bs
 * Date:     2019/5/27 14:47
 * Description:
 * Motto: 0.45%
 */

@RequiredArgsConstructor(staticName = "of")
public class Device extends AbstractActor {

    private final LoggingAdapter log = Logging.getLogger(getContext().getSystem(), this);


    @NonNull
    final String groupId;
    @NonNull
    final String deviceId;

    Optional<Double> lastTemperatureReading = Optional.empty();

    public static Props props(String groupId, String deviceId) {
        return Props.create(Device.class, () -> new Device(groupId, deviceId));
    }

    @Override
    public void preStart() {
        log.info("Device actor:[{}]-:[{}] started", groupId, deviceId);
    }

    @Override
    public void postStop() {
        log.info("Device actor :[{}]-:[{}] stopped", groupId, deviceId);
    }


    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(
                        RequestTrackDevice.class,
                        r -> {
                            //就是构造方法中传递的id，是否和发送消息时候接受的id相同
                            if (this.groupId.equals(r.groupId) && this.deviceId.equals(r.deviceId)) {
                                log.info("转发到 DeviceRegistered");
                                getSender().tell(new DeviceRegistered(), getSelf());
                            } else {

                                log.warning(
                                        "Ignoring TrackDevice request for :[{}]-:[{}].This actor is responsible for :[{}]-:[{}].",
                                        r.groupId,
                                        r.deviceId,
                                        this.groupId,
                                        this.deviceId);
                            }
                        })
                .match(
                        RecordTemperature.class,
                        r -> {
                            log.info("Recorded temperature reading :[{}] with :[{}]", r.value, r.requestId);
                            lastTemperatureReading = Optional.of(r.value);
                            getSender().tell(new TemperatureRecorded(r.requestId), getSelf());
                        })
                .match(
                        ReadTemperature.class,
                        r -> {
                            getSender().tell(new RespondTemperature(r.requestId, lastTemperatureReading), getSelf());
                        })
                .build();
    }

    @RequiredArgsConstructor(staticName = "of")
    public static final class RecordTemperature {

        @NonNull
        final long requestId;
        @NonNull
        final double value;
    }

    @RequiredArgsConstructor(staticName = "of")
    public static final class TemperatureRecorded {

        @NonNull
        final long requestId;
    }

    @RequiredArgsConstructor(staticName = "of")
    public static final class ReadTemperature {

        @NonNull
        final long requestId;
    }

    @RequiredArgsConstructor(staticName = "of")
    public static final class RespondTemperature {
        @NonNull
        public final long requestId;
        @NonNull
        public final Optional<Double> value;
    }


    @RequiredArgsConstructor(staticName = "of")
    @Getter
    public static final class RequestTrackDevice {
        @NonNull
        public final String groupId;
        @NonNull
        public final String deviceId;
    }

    public static final class DeviceRegistered {

    }

}



