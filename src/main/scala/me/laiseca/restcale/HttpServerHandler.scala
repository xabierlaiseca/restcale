package me.laiseca.restcale

import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.http.HttpRequest
import io.netty.handler.codec.http.HttpHeaders
import io.netty.handler.codec.http.DefaultFullHttpResponse
import io.netty.handler.codec.http.HttpVersion
import io.netty.buffer.Unpooled
import io.netty.handler.codec.http.HttpResponseStatus
import io.netty.buffer.ByteBuf
import io.netty.util.CharsetUtil
import io.netty.channel.ChannelFutureListener

class HttpServerHandler extends SimpleChannelInboundHandler[AnyRef] {
	val CONTENT:ByteBuf = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(
                "<html><head><title>Hello Netty & Scala World</title></head><body>Hello Netty & Scala World!!</body></html>",
                CharsetUtil.UTF_8))
  
	override def channelReadComplete(ctx:ChannelHandlerContext):Unit = {
		ctx.flush();
	}

	override protected def channelRead0(ctx:ChannelHandlerContext, msg:AnyRef): Unit = {
		val request:HttpRequest = msg.asInstanceOf[HttpRequest]

		val keepAlive = HttpHeaders.isKeepAlive(request)
		val response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, CONTENT.duplicate())

		response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/html; charset=UTF-8");
		response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, response.content().readableBytes());

		if (keepAlive) {
			response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
			ctx.write(response)
		} else {
			ctx.write(response).addListener(ChannelFutureListener.CLOSE)
		}
	}


	override def exceptionCaught(ctx:ChannelHandlerContext, cause:Throwable):Unit = {
		cause.printStackTrace()
		ctx.close()
	}
}