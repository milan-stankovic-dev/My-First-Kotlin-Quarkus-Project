package org.acme.dto.book

import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.Size
import java.math.BigDecimal

data class BookSaveDTO(
    @field:Size(min = 2, max = 255, message = "Book title must be between 2 and 255 characters long.")
    val title: String,
    @field:DecimalMin(value = "0.0", inclusive = false)
    @field:Digits(integer = 38, fraction = 2, message = "Price format may not be valid.")
    val price: BigDecimal,
    @field:Size(min = 2, max = 255, message = "ISBN must be between 2 and 255 characters long.")
    val isbn: String,
    @field:Size(min = 2, max = 500, message="Image url must be between 2 and 500 characters long.")
    val imageUrl: String?,
    val authorId: Long,
    val categoryId: Long,
    val genreId: Long)