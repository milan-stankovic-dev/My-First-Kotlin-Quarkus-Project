package org.acme.exception

import jakarta.validation.ConstraintViolationException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class ConstraintViolationExceptionHandler : ExceptionMapper<ConstraintViolationException> {
    override fun toResponse(exception: ConstraintViolationException?) : Response =
        Response.status(400).entity(ExceptionPayload(
            ErrorType.CONSTRAINT_VIOLATION,
            exception?.constraintViolations?.map { it.message }?.toList()
                ?: emptyList()
        )).build()
}