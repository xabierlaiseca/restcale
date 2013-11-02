package me.laiseca.restcale.methodcall

import me.laiseca.restcale.RestcaleException

class IllegalParameterTypeException(typeName:String, cause:Throwable) 
		extends RestcaleException(String.format("Cannot transform the parameter with value %s", typeName), cause, 500) {
	def this(typeName:String) = this(typeName, null)
}