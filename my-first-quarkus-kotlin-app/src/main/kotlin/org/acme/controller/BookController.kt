package org.acme.controller

import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.validation.Valid
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
    fun getAllBooks(@QueryParam("page") page: Int?, 
                    @QueryParam("size") size: Int?): Response =
        Response.ok(
            bookService.getAllBooks(page, size)).build()

    @POST
    @Transactional
    fun saveBook(@Valid bookToSave: BookSaveDTO) : Response {
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
    fun updateTitle(@Valid @RequestBody newTitle: BookTitleDTO,
                    @PathParam("id") id: Long): Response =
        Response.ok(
            bookService.updateTitle(id, newTitle)).build();
}