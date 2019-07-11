package com.lhh.myhttp.core;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright (C), 2019-2019
 * FileName: NettyHttpServer
 * Author:   s·D·bs
 * Date:     2019/6/14 9:54
 * Description:  NettyHttpServer 源码编写
 * Motto: 0.45%
 */
@RequiredArgsConstructor(staticName = "of")
@Getter
@Slf4j
public class NettyHttpServer {

    @NonNull
    private int inetPort;

    /**
     * @return void
     * @Author s·D·bs
     * @Description 起上一个netty server服务
     * @Date 13:15 2019/6/14
     * @Param []
     */
    public void init() throws Exception {
        // Configure the server.
        // Create two EventLoopGroup Object ?
        // Create a boss threadGroup for the server to accept client connections
        //使用两个 EventLoopGroup 对象( 当然这个对象可以引用同一个对象 )：第一个用于处理它本地 Socket 连接的 IO 事件处理，
        // 而第二个责负责处理远程客户端的 IO 事件处理。
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //The worker threadGroup is created to read and write data on the Socketchannel
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        // 1. 绑定两个线程组分别用来处理客户端通道的accept和读写时间
        try {
            ServerBootstrap server = new ServerBootstrap();
            server.group(bossGroup, workerGroup)
                    // 2. 绑定服务端通道NioServerSocketChannel
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel socketChannel) {
                            ChannelPipeline p = socketChannel.pipeline();
                            p .addLast("http-decoder", new HttpRequestDecoder());
                            // 将HTTP消息的多个部分合成一条完整的HTTP消息
                            p .addLast("http-aggregator", new HttpObjectAggregator(65535));
                            // 响应转码器
                            p .addLast("http-encoder", new HttpResponseEncoder());
                            // 解决大码流的问题，ChunkedWriteHandler：向客户端发送HTML5文件
                            p .addLast("http-chunked", new ChunkedWriteHandler());
                            // 自定义处理handler
                            p .addLast("http-server", new NettyHttpServerHandler());
                        }
                    });

            // Start the server. bind a port ：sync
            ChannelFuture f = server.bind(this.inetPort).sync();
            log.info("[NettyHttpServer] opening in：[{}]",this.inetPort);

            f.channel().closeFuture().sync(); //没有关闭服务器，只是关闭了监听
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

}
