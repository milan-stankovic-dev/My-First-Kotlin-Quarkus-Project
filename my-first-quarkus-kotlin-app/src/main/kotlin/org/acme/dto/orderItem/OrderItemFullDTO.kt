package org.acme.dto.orderItem

data class OrderItemFullDTO(
    val id: Long,
    val bookId: Long,
    val amount: Int)
