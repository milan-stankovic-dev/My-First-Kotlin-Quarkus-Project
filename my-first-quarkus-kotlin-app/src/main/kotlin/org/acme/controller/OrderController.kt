package org.acme.controller

import jakarta.annotation.security.RolesAllowed
import jakarta.inject.Inject
import jakarta.validation.Valid
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType.APPLICATION_JSON
import jakarta.ws.rs.core.Response
import org.acme.dto.order.OrderSaveDTO
import org.acme.service.OrderService
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody

@Path("/order")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
class OrderController {
    @Inject
    lateinit var service: OrderService

    @POST
    @RolesAllowed(value=["admin", "user"])
    fun saveOrder(@Valid orderForSave: OrderSaveDTO): Response =
        Response.ok(service.saveOrder(orderForSave)).build()

}