package com.akka.manager
import akka.actor.{Actor, ActorLogging, Props,ActorRef}

/**
  * Copyright (C), 2019-2019
  * FileName: DeviceGroup
  * Author:   s·D·bs 
  * Date:     2019/5/25 16:25
  * Description: ${DESCRIPTION}
  * Motto: 0.45%
  *
  */
object DeviceGroup {
  def props(groupId: String): Props = Props(new DeviceGroup(groupId))
}
class DeviceGroup(groupId: String) extends Actor with ActorLogging {
  var deviceIdToActor = Map.empty[String, ActorRef]

  override def preStart(): Unit = log.info("DeviceGroup {} started", groupId)

  override def postStop(): Unit = log.info("DeviceGroup {} stopped", groupId)

  override def receive: Receive = {
    case trackMsg @RequestTrackDevice(`groupId`, _) =>
      deviceIdToActor.get(trackMsg.deviceId) match {
        case Some(deviceActor) =>
          deviceActor.forward(trackMsg)
        case None =>
          log.info("Creating device actor for {}", trackMsg.deviceId)
          val deviceActor = context.actorOf(Device.props(groupId, trackMsg.deviceId), s"device-${trackMsg.deviceId}")
          deviceIdToActor += trackMsg.deviceId -> deviceActor
          deviceActor.forward(trackMsg)
      }

    case RequestTrackDevice(groupId, deviceId) =>
      log.warning("Ignoring TrackDevice request for {}. This actor is responsible for {}.", groupId, this.groupId)
  }
}
