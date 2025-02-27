package org.acme.exception

import jakarta.ws.rs.NotAuthorizedException
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.ext.ExceptionMapper
import jakarta.ws.rs.ext.Provider

@Provider
class NotAuthorizedExceptionHandler : ExceptionMapper<NotAuthorizedException> {
    override fun toResponse(exception: NotAuthorizedException?): Response {
        val message: String? = exception?.message
        val challengesAsStrings : MutableList<String?> = exception?.challenges?.map { it.toString() }?.toMutableList() ?: mutableListOf()
        if(message != null) { challengesAsStrings.add(message) }

        return Response.status(401).entity(
            ExceptionPayload(ErrorType.UNAUTHORIZED, challengesAsStrings)
        ).build()
    }
}