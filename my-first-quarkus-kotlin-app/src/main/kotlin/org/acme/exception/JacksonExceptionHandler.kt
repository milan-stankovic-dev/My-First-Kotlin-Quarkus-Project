package org.acme.exception

import com.fasterxml.jackson.core.JacksonException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class JacksonExceptionHandler : ExceptionMapper<JacksonException> {
    override fun toResponse(exception: JacksonException?): Response =
        Response.status(404).entity(ExceptionPayload(
            ErrorType.JSON_ERROR, listOf(exception?.message)
        )).build()
}