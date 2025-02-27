package org.acme.exception

import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class TransactionRequiredExceptionHandler : ExceptionMapper<jakarta.persistence.TransactionRequiredException> {

    override fun toResponse(exception: jakarta.persistence.TransactionRequiredException?): Response =
        Response.status(500).entity(ExceptionPayload(
            ErrorType.TRANSACTION_NEEDED,
            exception?.message?.let { listOf(it) } ?: listOf()
        )).build()
}