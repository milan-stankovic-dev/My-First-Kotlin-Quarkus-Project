package org.acme.dto.orderItem

data class OrderItemSaveDTO(
    val bookId: Long,
    val amount: Int)