package me.laiseca.restcale.http

sealed trait HttpMethod

case object GET_METHOD extends HttpMethod
case object POST_METHOD extends HttpMethod
case object PUT_METHOD extends HttpMethod
case object DELETE_METHOD extends HttpMethod