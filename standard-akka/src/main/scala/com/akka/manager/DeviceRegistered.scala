package com.akka.manager

import akka.actor.{Actor, ActorLogging, Props}

/**
  * Copyright (C), 2019-2019
  * FileName: DeviceRegistered
  * Author:   s·D·bs 
  * Date:     2019/5/25 16:03
  * Description:
  * 1当DeviceManager收到具有组和设备ID的请求时：
  * 如果经理已经拥有设备组的参与者，它会将请求转发给它。
  * 否则，它会创建一个新的设备组actor，然后转发该请求。
  *
  * 2DeviceGroup actor接收注册给定设备的actor的请求：
  * 如果组已经有设备的actor，则组actor将请求转发给设备actor。
  * 否则，DeviceGroup actor首先创建一个设备actor，然后转发该请求。
  *
  * 3设备参与者接收请求并向原始发送者发送确认。
  * 由于设备actor确认收到（而不是组actor），传感器现在ActorRef将直接向其actor发送消息。
  * Motto: 0.45%
  *
  */


object Device {
  def props(groupId: String, deviceId: String): Props = Props(new Device(groupId, deviceId))

  final case class RecordTemperature(requestId: Long, value: Double)

  final case class TemperatureRecorded(requestId: Long)

  final case class ReadTemperature(requestId: Long)

  final case class RespondTemperature(requestId: Long, value: Option[Double])

}

class Device(groupId: String, deviceId: String) extends Actor with ActorLogging {

  import Device._

  var lastTemperatureReading: Option[Double] = None

  override def preStart(): Unit = log.info("Device actor :{}-:{} started", groupId, deviceId)

  override def postStop(): Unit = log.info("Device actor :{}-:{} stopped", groupId, deviceId)

  override def receive: Receive = {
    case RequestTrackDevice(`groupId`, `deviceId`) => //this.groupId.equals(r.groupId) && this.deviceId.equals(r.deviceId
      sender() ! DeviceRegistered
    case RequestTrackDevice(groupId, deviceId) =>
      log.warning(
        "Ignoring TrackDevice request for {}-{}.This actor is responsible for {}-{}.",
        groupId,
        deviceId,
        this.groupId,
        this.deviceId)
    case RecordTemperature(id, value) =>
      log.info("Recorded temperature reading {} with {}", value, id)
      lastTemperatureReading = Some(value)
      sender() ! TemperatureRecorded(id)
    case ReadTemperature(id) =>
      sender() ! RespondTemperature(id, lastTemperatureReading)

  }


}
