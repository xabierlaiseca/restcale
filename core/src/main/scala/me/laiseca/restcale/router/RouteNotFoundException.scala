package me.laiseca.restcale.router

case class RouteNotFoundException(message:String) extends Exception(message)