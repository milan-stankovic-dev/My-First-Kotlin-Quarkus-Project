package org.acme.controller

import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import jakarta.ws.rs.core.Response
import org.acme.dto.book.BookSaveDTO
import org.acme.dto.book.BookTitleDTO
import org.acme.service.BookService
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody

@Path("/book")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
class BookController {
    @Inject
    lateinit var bookService: BookService

    @GET
    fun getAllBooks(): Response =
        Response.ok(
            bookService.getAllBooks()).build()

    @POST
    @Transactional
    fun saveBook(bookToSave: BookSaveDTO) : Response {
        return Response.ok(
            bookService.saveABook(bookToSave)).build()
    }

    @DELETE
    @Path("{id}")
    fun deleteById(@PathParam("id") id: Long) : Response {
        bookService.deleteById(id)
        return Response.ok().build()
    }

    @PATCH
    @Path("{id}")
    fun updateTitle(@RequestBody newTitle: BookTitleDTO,
                    @PathParam("id") id: Long): Response =
        Response.ok(
            bookService.updateTitle(id, newTitle)).build();
}