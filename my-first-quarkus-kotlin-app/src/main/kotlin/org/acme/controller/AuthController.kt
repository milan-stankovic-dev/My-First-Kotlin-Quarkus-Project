package org.acme.controller

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.validation.Valid
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import jakarta.ws.rs.core.Response
import jakarta.ws.rs.core.Response.Status.UNAUTHORIZED
import jakarta.ws.rs.core.Response.Status.CREATED
import org.acme.dto.user.LoginRequest
import org.acme.dto.user.RegisterRequest
import org.acme.jwt.JWTService
import org.acme.model.User
import org.acme.service.AuthService

@ApplicationScoped
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
@Path("/auth")
class AuthController {
    @Inject
    lateinit var service: AuthService
    @Inject
    lateinit var jwtService: JWTService

    @POST
    @Path("/register")
    fun register(@Valid request: RegisterRequest) : Response {
        val user : User = service.registerUser(request)
        return Response.status(CREATED)
            .entity(user).build()
    }

    @POST
    @Path("/login")
    fun login(@Valid request: LoginRequest) : Response {
        val user: User = service.authenticate(request)
            ?: return Response.status(UNAUTHORIZED).build()
        val token: String = jwtService.generateJWT()

        return Response.ok(TokenDTO(token, user.id!!)).build()
    }

    private data class TokenDTO(val token: String, val userID: Long)
}