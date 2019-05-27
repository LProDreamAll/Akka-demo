package com.standard.akka.sample.current;
import akka.actor.Props;

/**
 * Copyright (C), 2019-2019
 * FileName: MyActorTest
 * Author:   s·D·bs
 * Date:     2019/5/27 11:38
 * Description:
 * Motto: 0.45%
 */
public class MyActorTest {

    public static void main(String[] args) {
        Props props1 = Props.create(MyActor.class);
        Props props2 =Props.create(ActorWithArgs.class, () -> new ActorWithArgs("arg")); // careful, see below
        Props props3 = Props.create(ActorWithArgs.class, "arg");
    }
}
