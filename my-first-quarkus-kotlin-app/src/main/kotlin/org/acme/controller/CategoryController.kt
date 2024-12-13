package org.acme.controller

import jakarta.inject.Inject
import jakarta.ws.rs.PATCH
import jakarta.ws.rs.Path
import jakarta.ws.rs.PathParam
import jakarta.ws.rs.core.Response
import org.acme.service.CategoryService

@Path("/category")
class CategoryController {
    @Inject
    lateinit var service : CategoryService
    
    @PATCH
    @Path("{id}/assign-to-book/{bookId}")
    fun assignCategoryToBook(@PathParam("id") id : Long,
                             @PathParam("bookId") bookId : Long) : Response  =
            Response.ok(service.assignCategoryToBook(id, bookId)).build()
}