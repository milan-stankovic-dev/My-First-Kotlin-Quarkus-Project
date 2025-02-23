package org.acme.dto.order

import org.acme.dto.orderItem.OrderItemFullDTO
import java.time.LocalDate

data class OrderFullDTO(
    val id: Long,
    val userId: Long,
    val date: LocalDate,
    val items: List<OrderItemFullDTO>)