package org.acme.exception

import jakarta.json.JsonException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class JsonExceptionHandler : ExceptionMapper<JsonException> {
    override fun toResponse(exception: JsonException?): Response =
        Response.status(404).entity(ExceptionPayload(
            ErrorType.JSON_ERROR, listOf(exception?.message)
        )).build()
}