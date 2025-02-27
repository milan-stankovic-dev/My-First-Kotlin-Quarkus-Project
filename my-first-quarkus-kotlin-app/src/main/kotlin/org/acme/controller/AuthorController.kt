package org.acme.controller

import jakarta.inject.Inject
import jakarta.validation.Valid
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import jakarta.ws.rs.core.Response
import org.acme.dto.author.AuthorSaveDTO
import org.acme.service.AuthorService

@Path("/author")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
class AuthorController {
    @Inject
    lateinit var service: AuthorService
    
    @GET
    fun getByID(@QueryParam("id") id: Long) : Response = 
        Response.ok(service.getById(id)).build()
    
    @POST
    fun saveAuthor(@Valid author: AuthorSaveDTO) : Response =
        Response.ok(service.save(author)).build()
    
    @DELETE
    @Path("/{id}")
    fun deleteAuthorByID(@PathParam("id") id: Long) : Response {
        service.deleteById(id)
        return Response.noContent().build()
    }
    
    @PUT
    @Path("/{id}")
    fun changeEntireAuthor(@PathParam("id") id: Long,
                           @Valid authorData: AuthorSaveDTO) : Response =
        Response.ok(service.update(id, authorData)).build()
}