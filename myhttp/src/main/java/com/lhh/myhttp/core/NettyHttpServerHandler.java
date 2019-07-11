package com.lhh.myhttp.core;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import lombok.extern.slf4j.Slf4j;

/**
 * Copyright (C), 2019-2019
 * FileName: NettyHttpServerHandler
 * Author:   s·D·bs
 * Date:     2019/6/14 13:17
 * Description: server处理器
 * Motto: 0.45%
 */
@Slf4j
public class NettyHttpServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, FullHttpRequest fullHttpRequest) throws Exception {

    }
}
