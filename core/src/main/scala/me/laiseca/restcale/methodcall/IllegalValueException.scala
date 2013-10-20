package me.laiseca.restcale.methodcall

import me.laiseca.restcale.RestcaleException

class IllegalValueException(value:String, cause: Throwable) 
    extends RestcaleException(String.format("Illegal value: %s", value), cause, 422) {
}