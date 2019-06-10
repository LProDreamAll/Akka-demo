package com.lhh.my.jedis;

import io.netty.handler.ssl.ApplicationProtocolConfig;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Copyright (C), 2019-2019
 * FileName: Connection
 * Author:   s·D·bs
 * Date:     2019/6/10 9:35
 * Description: 连接类
 * 在这里进行创建连接并处理IO请求，用inputStream进行数据回显，提供OutputStream给协议层，以便让其给服务端发送命令
 * Motto: 0.45%
 */
@Slf4j
public class Connection {

    private String host = "localhost";
    private int port = 6379;
    private Socket socket;
    private OutputStream outputStream;
    private InputStream inputStream;

    public Connection() {
    }

    public Connection(String host) {
        this.host = host;
    }

    public Connection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Connection sendCommand(Protocol.Command cmd, byte[]... args) {
        connect();
        Protocol.sendCommand(outputStream, cmd, args);
        return this;
    }

    private void connect() {
        try {
            if (socket == null) {
                socket = new Socket(host, port);
                inputStream = socket.getInputStream();
                outputStream = socket.getOutputStream();
            }
        } catch (IOException e) {
            log.error("connect error:[{}]", e);
        }
    }

    /**
     * 操作状态的返回
     * 比如：SET 操作成功返回 +OK
     */
    public String getStatus() {
        byte[] bytes = new byte[1024];
        try {
            socket.getInputStream().read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes);
    }
}
