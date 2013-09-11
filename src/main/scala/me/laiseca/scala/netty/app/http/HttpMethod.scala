package me.laiseca.scala.netty.app.http

sealed trait HttpMethod

case object GET_METHOD extends HttpMethod
case object POST_METHOD extends HttpMethod
case object PUT_METHOD extends HttpMethod
case object DELETE_METHOD extends HttpMethod