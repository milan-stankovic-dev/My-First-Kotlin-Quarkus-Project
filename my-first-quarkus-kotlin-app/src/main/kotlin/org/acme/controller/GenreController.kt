package org.acme.controller

import jakarta.inject.Inject
import jakarta.ws.rs.PATCH
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.core.Response
import org.acme.service.GenreService

@Path("/genre")
class GenreController {
    @Inject
    lateinit var service: GenreService

    @PATCH
    @Path("{id}/assign-to-book/{bookId}")
    fun assignGenreToBook(@PathParam("id") id : Long,
                             @PathParam("bookId") bookId : Long) : Response =
        Response.ok(service.assignGenreToBook(id, bookId)).build()
}