package org.acme.exception

import jakarta.transaction.RollbackException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class RollbackExceptionHandler : ExceptionMapper<RollbackException>{
    override fun toResponse(exception: RollbackException?): Response {
        return Response.status(409).entity(ExceptionPayload(
            ErrorType.ROLLBACK, listOf(exception?.message)
        )).build()
    }
}