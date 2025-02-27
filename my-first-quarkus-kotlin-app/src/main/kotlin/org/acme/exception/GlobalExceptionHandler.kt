package org.acme.exception

import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class GlobalExceptionHandler : ExceptionMapper<Exception> {
    override fun toResponse(exception: Exception?): Response =
        Response.serverError().entity(ExceptionPayload(
            ErrorType.SERVER_ERROR, listOf(exception?.message)
        )).build()

}