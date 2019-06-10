package com.lhh.my.jedis;

import lombok.RequiredArgsConstructor;

/**
 * Copyright (C), 2019-2019
 * FileName: Client
 * Author:   s·D·bs
 * Date:     2019/6/10 9:59
 * Description: 对外提供API
 * Motto: 0.45%
 */
public class Client {
    private Connection connection;

    public Client(String host, int port){
        connection = new Connection(host,port);
    }

    public String set(String key, String value) {
        connection.sendCommand(Protocol.Command.SET, key.getBytes(), value.getBytes());
        return connection.getStatus();
    }

    public String get(String key) {
        connection.sendCommand(Protocol.Command.GET, key.getBytes());
        return connection.getStatus();
    }


    public void set(String key, String value, String nx, String ex, int i) {
        connection.sendCommand(Protocol.Command.SET, key.getBytes(), value.getBytes(), nx.getBytes(), ex.getBytes(), String.valueOf(i).getBytes());
    }
    public void append(String key, String value){
        connection.sendCommand(Protocol.Command.APPEND, key.getBytes(),value.getBytes());
    }


}
