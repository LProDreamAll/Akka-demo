package com.akka.example

import akka.actor.{Actor, ActorLogging, Props}
import com.akka.example.Device._

/**
  * Copyright (C), 2019-2019
  * FileName: Device
  * Author:   s·D·bs 
  * Date:     2019/5/25 13:56
  * Description:
  * 收集温度测量值
  * 当被问到时，报告最后测量的温度
  * Motto: 0.45%
  *
  */

object Device {
  def props(groupId: String, deviceId: String): Props = Props(new Device(groupId, deviceId))
  final case class ReadTemperature(requestId: Long)
  final case class RespondTemperature(requestId: Long, value: Option[Double])
}


class Device(groupId: String, deviceId: String) extends Actor with ActorLogging {
  var lastTemperatureReading: Option[Double] = None
  override def preStart(): Unit = log.info("Device actor {}-{} started", groupId, deviceId)
  override def postStop(): Unit = log.info("Device actor {}-{} stopped", groupId, deviceId)
  override def receive: Receive = {
    case ReadTemperature(id) => sender() ! RespondTemperature(id, lastTemperatureReading)
  }
}
