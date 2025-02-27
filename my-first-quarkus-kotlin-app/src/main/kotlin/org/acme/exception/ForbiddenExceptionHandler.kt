package org.acme.exception

import io.quarkus.security.ForbiddenException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class ForbiddenExceptionHandler : ExceptionMapper<ForbiddenException>{
    override fun toResponse(exception: ForbiddenException?): Response =
        Response.status(403).entity(
            ExceptionPayload(ErrorType.FORBIDDEN,
                listOf(exception?.message))
        ).build()
}