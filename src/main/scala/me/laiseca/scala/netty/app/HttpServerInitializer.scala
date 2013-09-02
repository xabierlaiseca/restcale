package me.laiseca.scala.netty.app

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import io.netty.channel.ChannelPipeline
import javax.net.ssl.SSLEngine
import io.netty.handler.codec.http.HttpResponseEncoder
import io.netty.handler.codec.http.HttpRequestDecoder
import io.netty.handler.ssl.SslHandler
import io.netty.handler.codec.http.HttpObjectAggregator

class HttpServerInitializer extends ChannelInitializer[SocketChannel] {
  override def initChannel(channel: SocketChannel): Unit = {
    val p:ChannelPipeline = channel.pipeline()

//    val engine:SSLEngine = SecureChatSslContextFactory.getServerContext().createSSLEngine()
//    engine.setUseClientMode(false)
//    p.addLast("ssl", new SslHandler(engine))
    p.addLast("decoder", new HttpRequestDecoder())
    p.addLast("aggregator", new HttpObjectAggregator(1048576))
    p.addLast("encoder", new HttpResponseEncoder())
    p.addLast("handler", new HttpServerHandler())
  }
}