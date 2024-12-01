package org.acme.rest

data class Greeting(val message: String = "") {
    override fun toString(): String = message
}
