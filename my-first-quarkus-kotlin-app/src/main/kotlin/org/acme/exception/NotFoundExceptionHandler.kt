package org.acme.exception

import jakarta.ws.rs.NotFoundException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class NotFoundExceptionHandler : ExceptionMapper<NotFoundException> {
    override fun toResponse(exception: NotFoundException?): Response =
        Response.status(404).entity(
            ExceptionPayload(ErrorType.NOT_FOUND,
                exception?.message?.let { listOf(it) }
                ?: emptyList<String>()))
            .build()
}