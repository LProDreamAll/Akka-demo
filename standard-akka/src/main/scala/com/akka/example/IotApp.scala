package com.akka.example

import akka.actor.ActorSystem

import scala.io.StdIn

object IotApp {
  def main(args: Array[String]): Unit = {
    val system = ActorSystem("scala-iot-system")
    try {
      // Create top level supervisor
      val superVisor = system.actorOf(IotSupervisor.props(), "iot-supervisor")
      // Exit the system after ENTER is pressed
      StdIn.readLine()
    } finally {
      system.terminate()
    }
  }
}
