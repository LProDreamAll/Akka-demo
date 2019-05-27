package example;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.testkit.javadsl.TestKit;
import com.akka.manager.DeviceRegistered;
import com.akka.manager.RequestTrackDevice;
import com.standard.akka.sample.example.part4.Device;
import com.standard.akka.sample.example.part4.RequestDeviceGroup;
import org.junit.Test;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

/**
 * Copyright (C), 2019-2019
 * FileName: DeviceTest
 * Author:   s·D·bs
 * Date:     2019/5/25 15:15
 * Description:
 * Motto: 0.45%
 */

public class DeviceTest {

    ActorSystem system = ActorSystem.create("system");

    @Test
    public void testReplyWithEmptyReadingIfNoTemperatureIsKnown() {
        TestKit probe = new TestKit(system);
        ActorRef deviceActor = system.actorOf(Device.props("group", "device"));
        deviceActor.tell(Device.ReadTemperature.of(42L), probe.getRef());
        Device.RespondTemperature response = probe.expectMsgClass(Device.RespondTemperature.class);
        assertEquals(42L, response.requestId);
        assertEquals(Optional.empty(), response.value);
    }

    /**
     * 成功注册
     * expectMsgClass 此断言将等待到定义的时间限制，如果在此期间收到任何消息，则会失败。如果在等待期间没有收到消息，则断言通过
     */
    @Test
    public void testReplyToRegistrationRequests() {
        TestKit probe = new TestKit(system);
        ActorRef deviceActor = system.actorOf(Device.props("group", "device"));
        deviceActor.tell(new RequestTrackDevice("group", "device"), probe.getRef());
        probe.expectMsgClass(DeviceRegistered.class);
        assertEquals(deviceActor, probe.getLastSender());
    }

    /**
     * ID不匹配
     */
    @Test
    public void testIgnoreWrongRegistrationRequests() {
        TestKit probe = new TestKit(system);
        ActorRef deviceActor = system.actorOf(Device.props("group", "device"));

        deviceActor.tell(new RequestTrackDevice("wrongGroup", "device"), probe.getRef());
        probe.expectNoMessage();

        deviceActor.tell(new RequestTrackDevice("group", "wrongDevice"), probe.getRef());
        probe.expectNoMessage();
    }


}
