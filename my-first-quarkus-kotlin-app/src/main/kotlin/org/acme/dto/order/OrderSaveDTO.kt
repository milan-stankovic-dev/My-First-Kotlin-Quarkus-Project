package org.acme.dto.order

import org.acme.dto.orderItem.OrderItemFullDTO
import org.acme.model.OrderItem
import java.time.LocalDate

data class OrderSaveDTO(
    val userId: Long,
    val date: LocalDate,
    val items: List<OrderItemFullDTO>)