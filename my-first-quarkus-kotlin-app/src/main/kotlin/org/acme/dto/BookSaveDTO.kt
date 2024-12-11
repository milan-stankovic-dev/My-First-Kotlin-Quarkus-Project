package org.acme.dto

import java.math.BigDecimal

data class BookSaveDTO(
    val title: String,
    val price: BigDecimal,
    val isbn: String,
    val author: AuthorSaveDTO)