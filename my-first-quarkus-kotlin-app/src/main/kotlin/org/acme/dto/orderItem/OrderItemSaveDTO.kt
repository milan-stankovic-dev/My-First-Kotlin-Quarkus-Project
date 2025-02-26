package org.acme.dto.orderItem

import jakarta.validation.constraints.Min

data class OrderItemSaveDTO(
    val bookId: Long,
    @Min(value = 1, message = "You must order at least one book.")
    val amount: Int)