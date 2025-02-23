package org.acme.mapper

import org.acme.constants.METADATA
import org.acme.dto.orderItem.OrderItemFullDTO
import org.acme.dto.orderItem.OrderItemSaveDTO
import org.acme.model.OrderItem

fun OrderItem.toFullDTO() =
    OrderItemFullDTO(
        id ?: throw IllegalArgumentException("Order item id found to be null in mapper."),
        book?.id ?: throw IllegalArgumentException("Book found to be null in mapper."),
        amount)

fun OrderItemFullDTO.toOrderItem() =
    OrderItem(
        id, null, null, amount, METADATA)

fun OrderItemSaveDTO.toOrderItem() =
    OrderItem(null, null, null, amount, METADATA)
