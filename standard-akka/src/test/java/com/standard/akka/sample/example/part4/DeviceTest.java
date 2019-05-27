package com.standard.akka.sample.example.part4;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.PoisonPill;
import akka.testkit.javadsl.TestKit;
import com.google.common.collect.Maps;
import com.standard.akka.sample.example.part5.DeviceGroupQuery;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import scala.concurrent.duration.FiniteDuration;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Copyright (C), 2019-2019
 * FileName: DeviceTest
 * Author:   s·D·bs
 * Date:     2019/5/27 15:12
 * Description: 测试类
 * Motto: 0.45%
 */
@Slf4j
public class DeviceTest {

    ActorSystem system = ActorSystem.create("system");

    //expectMsgClass 如果在此期间收到任何消息，则会失败
    @Test
    public void test_Device_ReplyToRegistrationRequests() {
        TestKit probe = new TestKit(system);
        ActorRef deviceActor = system.actorOf(Device.props("group", "device"));//
        deviceActor.tell(Device.RequestTrackDevice.of("group", "device"), probe.getRef());
        probe.expectMsgClass(Device.DeviceRegistered.class);
        assertEquals(deviceActor, probe.getLastSender());
    }

    @Test
    public void test_Device_IgnoreWrongRegistrationRequests() {
        TestKit probe = new TestKit(system);
        ActorRef deviceActor = system.actorOf(Device.props("group", "device"));

        deviceActor.tell(Device.RequestTrackDevice.of("wrongGroup", "device"), probe.getRef());
        probe.expectNoMessage();

        deviceActor.tell(Device.RequestTrackDevice.of("group", "wrongDevice"), probe.getRef());
        probe.expectNoMessage();
    }


    //测试了两个不同ID返回的演员实际上是不同的，我们还尝试记录每个设备的温度读数，以查看演员是否在响应。
    @Test
    public void test_DeviceGroup_RegisterDeviceActor() {
        TestKit probe = new TestKit(system);
        ActorRef groupActor = system.actorOf(DeviceGroup.props("group"));

        groupActor.tell(Device.RequestTrackDevice.of("group", "device1"), probe.getRef());
        probe.expectMsgClass(Device.DeviceRegistered.class);
        ActorRef deviceActor1 = probe.getLastSender();

        groupActor.tell(Device.RequestTrackDevice.of("group", "device2"), probe.getRef());
        probe.expectMsgClass(Device.DeviceRegistered.class);
        ActorRef deviceActor2 = probe.getLastSender();
        assertNotEquals(deviceActor1, deviceActor2);

        // Check that the device actors are working
        deviceActor1.tell(Device.RecordTemperature.of(0L, 1.0), probe.getRef());
        assertEquals(0L, probe.expectMsgClass(Device.TemperatureRecorded.class).requestId);
        deviceActor2.tell(Device.RecordTemperature.of(1L, 2.0), probe.getRef());
        assertEquals(1L, probe.expectMsgClass(Device.TemperatureRecorded.class).requestId);
    }


    @Test
    public void test_DeviceGroup_IgnoreRequestsForWrongGroupId() {
        TestKit probe = new TestKit(system);
        ActorRef groupActor = system.actorOf(DeviceGroup.props("group"));
        groupActor.tell(Device.RequestTrackDevice.of("wrongGroup", "device1"), probe.getRef());
        probe.expectNoMessage();
    }


    /**
     * @return void
     * @Author s·D·bs
     * @Description 注册请求已存在设备actor，希望使用现有的actor而不是新的actor
     * device1相同都放到 Map<String, ActorRef>deviceIdToActor中
     * @Date 16:57 2019/5/27
     * @Param []
     */
    @Test
    public void test_DeviceGroup_ReturnSameActorForSameDeviceId() {

        TestKit probe = new TestKit(system);
        ActorRef groupActor = system.actorOf(DeviceGroup.props("group"));

        groupActor.tell(Device.RequestTrackDevice.of("group", "device1"), probe.getRef());
        probe.expectMsgClass(Device.DeviceRegistered.class);
        ActorRef deviceActor1 = probe.getLastSender();

        groupActor.tell(Device.RequestTrackDevice.of("group", "device1"), probe.getRef());
        probe.expectMsgClass(Device.DeviceRegistered.class);
        ActorRef deviceActor2 = probe.getLastSender();
        log.info("deviceActor1 code :[{}] ,deviceActor2 code :[{}]", deviceActor1.hashCode(), deviceActor2.hashCode());
        assertEquals(deviceActor1, deviceActor2);
    }


    /**
     * @return void
     * @Author s·D·bs
     * @Description 测试用例中停止设备actor
     * 从外部，任何演员都可以通过发送特殊的内置消息PoisonPill来停止，该消息指示演员停止
     * 在设备actor停止后收到通知。我们也可以将Death Watch设施用于此目的。
     * 我们可以很容易地使用，两个消息看一个具体的演员，并 断言，看着演员已经终止。 TestKit watch()expectTerminated
     * @Date 17:24 2019/5/27
     * @Param []
     */
    @Test
    public void test_RequestDeviceGroup_ListActiveDevices() {

        TestKit probe = new TestKit(system);
        ActorRef groupActor = system.actorOf(RequestDeviceGroup.props("group"));

        groupActor.tell(Device.RequestTrackDevice.of("group", "device1"), probe.getRef());
        probe.expectMsgClass(Device.DeviceRegistered.class);

        groupActor.tell(Device.RequestTrackDevice.of("group", "device2"), probe.getRef());
        probe.expectMsgClass(Device.DeviceRegistered.class);

        groupActor.tell(RequestDeviceGroup.RequestDeviceList.of(0L), probe.getRef());
        RequestDeviceGroup.ReplyDeviceList reply = probe.expectMsgClass(RequestDeviceGroup.ReplyDeviceList.class);
        assertEquals(0L, reply.requestId);
        assertEquals(Stream.of("device1", "device2").collect(Collectors.toSet()), reply.ids);
    }

    /**
     * @return void
     * @Author s·D·bs
     * @Description 确保在设备actor停止后正确删除设备ID：
     * @Date 17:38 2019/5/27
     * @Param []
     */
    @Test
    public void testListActiveDevicesAfterOneShutsDown() {

        TestKit probe = new TestKit(system);
        ActorRef groupActor = system.actorOf(DeviceGroup.props("group"));

        groupActor.tell(Device.RequestTrackDevice.of("group", "device1"), probe.getRef());
        probe.expectMsgClass(Device.DeviceRegistered.class);
        ActorRef toShutDown = probe.getLastSender();

        groupActor.tell(Device.RequestTrackDevice.of("group", "device2"), probe.getRef());
        probe.expectMsgClass(Device.DeviceRegistered.class);

        groupActor.tell(RequestDeviceGroup.RequestDeviceList.of(0L), probe.getRef());
        RequestDeviceGroup.ReplyDeviceList reply = probe.expectMsgClass(RequestDeviceGroup.ReplyDeviceList.class);
        assertEquals(0L, reply.requestId);
        assertEquals(Stream.of("device1", "device2").collect(Collectors.toSet()), reply.ids);

        probe.watch(toShutDown);
        //发送停止信号
        toShutDown.tell(PoisonPill.getInstance(), ActorRef.noSender());
        probe.expectTerminated(toShutDown);

        // using awaitAssert to retry because it might take longer for the groupActor
        // to see the Terminated, that order is undefined
        probe.awaitAssert(
                () -> {
                    groupActor.tell(RequestDeviceGroup.RequestDeviceList.of(1L), probe.getRef());
                    RequestDeviceGroup.ReplyDeviceList r = probe.expectMsgClass(RequestDeviceGroup.ReplyDeviceList.class);
                    assertEquals(1L, r.requestId);
                    assertEquals(Stream.of("device2").collect(Collectors.toSet()), r.ids);
                    return null;
                });
    }



    @Test
    public void testReturnTemperatureValueForWorkingDevices() {
        TestKit requester = new TestKit(system);
        TestKit device1 = new TestKit(system);
        TestKit device2 = new TestKit(system);

        Map<ActorRef, String> actorToDeviceId = Maps.newHashMap();
        actorToDeviceId.put(device1.getRef(), "device1");
        actorToDeviceId.put(device2.getRef(), "device2");

        ActorRef queryActor =system.actorOf(
                        DeviceGroupQuery.props(actorToDeviceId, 1L, requester.getRef(), new FiniteDuration(3, TimeUnit.SECONDS)));

        assertEquals(0L, device1.expectMsgClass(Device.ReadTemperature.class).requestId);
        assertEquals(0L, device2.expectMsgClass(Device.ReadTemperature.class).requestId);

        queryActor.tell(Device.RespondTemperature.of(0L, Optional.of(1.0)), device1.getRef());
        queryActor.tell(Device.RespondTemperature.of(0L, Optional.of(2.0)), device2.getRef());

        DeviceGroupQuery.RespondAllTemperatures response =
                requester.expectMsgClass(DeviceGroupQuery.RespondAllTemperatures.class);
        assertEquals(1L, response.requestId);

        Map<String, DeviceGroupQuery.TemperatureReading> expectedTemperatures = Maps.newHashMap();
        expectedTemperatures.put("device1",  DeviceGroupQuery.Temperature.of(1.0));
        expectedTemperatures.put("device2",  DeviceGroupQuery.Temperature.of(2.0));

        assertEquals(expectedTemperatures, response.temperatures);
    }
}
