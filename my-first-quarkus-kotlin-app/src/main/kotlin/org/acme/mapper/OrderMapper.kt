package org.acme.mapper

import org.acme.constants.METADATA
import org.acme.dto.order.OrderFullDTO
import org.acme.dto.order.OrderSaveDTO
import org.acme.model.Order

fun Order.toFullDTO() : OrderFullDTO = OrderFullDTO(
    id ?: throw IllegalArgumentException("Order ID found to be null in mapper."),
    date ?: throw IllegalArgumentException("Order date found to be null in mapper."),
    books.map { it.toFullDTO() })

fun OrderSaveDTO.toOrder() : Order = Order(
    null, date, books.map { it.toBook() }
        .toMutableList(), METADATA)

