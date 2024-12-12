package org.acme.dto.book

import java.math.BigDecimal

data class BookSaveDTO(
    val title: String,
    val price: BigDecimal,
    val isbn: String,
    val authorId: Long,
    val categoryId: Long,
    val genreId: Long)