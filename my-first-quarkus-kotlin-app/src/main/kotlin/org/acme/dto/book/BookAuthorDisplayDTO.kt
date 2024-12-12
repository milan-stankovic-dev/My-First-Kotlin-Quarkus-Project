package org.acme.dto.book

import java.math.BigDecimal

data class BookAuthorDisplayDTO(
    val id: Long,
    val title: String, 
    val price: BigDecimal,
    val isbn: String)
