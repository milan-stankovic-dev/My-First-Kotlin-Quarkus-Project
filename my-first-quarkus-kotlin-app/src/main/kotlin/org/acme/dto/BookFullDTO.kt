package org.acme.dto

import java.math.BigDecimal

data class BookFullDTO(
    val id: Long,
    val title: String,
    val price: BigDecimal,
    val isbn: String,
    val author: AuthorBookDisplayDTO)