package org.acme.exception

import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class IllegalArgumentExceptionHandler : ExceptionMapper<IllegalArgumentException> {
    override fun toResponse(exception: IllegalArgumentException?): Response =
        Response.status(400).entity(ExceptionPayload(ErrorType.ILLEGAL_ARGUMENT,
            exception?.message?.let { listOf(it) } ?: emptyList())).build()
}