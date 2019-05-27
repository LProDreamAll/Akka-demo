package com.standard.akka.sample.current;

import akka.actor.AbstractActor;
import akka.actor.ActorIdentity;
import akka.actor.ActorRef;
import akka.actor.ActorSelection;
import akka.actor.Identify;
import akka.actor.Terminated;

/**
 * Copyright (C), 2019-2019
 * FileName: Follower
 * Author:   s·D·bs
 * Date:     2019/5/27 13:56
 * Description: actor的引用路径
 * Motto: 0.45%
 */
public class Follower extends AbstractActor {

    final Integer identifyId = 1;

    public Follower() {
        ActorSelection selection = getContext().actorSelection("/user/another");
        selection.tell(new Identify(identifyId), getSelf());
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(ActorIdentity.class,
                        id -> id.getActorRef().isPresent(),
                        id -> {
                            ActorRef ref = id.getActorRef().get();
                            getContext().watch(ref);
                            getContext().become(active(ref));
                        }).match(ActorIdentity.class,
                        id -> id.getActorRef().isPresent(),
                        id -> {
                            getContext().stop(getSelf());
                        })
                .build();
    }

    private Receive active(ActorRef another) {
        return receiveBuilder()
                .match(
                        Terminated.class, t -> t.actor().equals(another), t -> getContext().stop(getSelf()))
                .build();
    }

}
