package me.laiseca.restcale.http.server

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.Channel
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel

class HttpServer extends Runnable {
  
  def run():Unit = {
    val bossGroup = new NioEventLoopGroup()
    val workerGroup = new NioEventLoopGroup()
    
    try {
      val bs = new ServerBootstrap()
      bs.group(bossGroup, workerGroup)
        .channel(classOf[NioServerSocketChannel])
        .childHandler(new HttpServerInitializer());
      
      val ch:Channel = bs.bind(8080).sync().channel()
      ch.closeFuture().sync()
    }
  }
}