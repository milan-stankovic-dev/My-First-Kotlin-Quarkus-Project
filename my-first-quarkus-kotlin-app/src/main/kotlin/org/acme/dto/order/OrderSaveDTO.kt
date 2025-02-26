package org.acme.dto.order

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.PastOrPresent
import org.acme.dto.orderItem.OrderItemFullDTO
import java.time.LocalDate

data class OrderSaveDTO(
    val userId: Long,
    @PastOrPresent
    val date: LocalDate,
    @NotEmpty(message = "You must order at least one item.")
    val items: List<OrderItemFullDTO>)