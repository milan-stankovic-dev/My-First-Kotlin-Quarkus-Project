package org.acme.exception

import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider
import org.hibernate.HibernateException

@Provider
class HibernateExceptionHandler : ExceptionMapper<HibernateException> {
    override fun toResponse(exception: HibernateException?): Response =
        Response.status(400).entity(ExceptionPayload(
            ErrorType.DB_VIOLATION, listOf(exception?.message)
        )).build()
}