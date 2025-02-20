package org.acme.jwt

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType.TEXT_PLAIN
import jakarta.ws.rs.core.Response

@Path("/jwt")
@ApplicationScoped
class BookstoreJWTController {
    @Inject
    lateinit var service: BookstoreJWTService

    @GET
    @Produces(TEXT_PLAIN)
    fun getJWT() : Response {
        return Response.ok(service.generateJWT()).build()
    }
}