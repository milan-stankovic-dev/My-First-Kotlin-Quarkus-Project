package org.acme.dto.book

import jakarta.validation.constraints.Size

data class BookTitleDTO(
    @field:Size(min = 2, max = 255, message = "Book title must be between 2 and 255 characters long.")
    val title: String)