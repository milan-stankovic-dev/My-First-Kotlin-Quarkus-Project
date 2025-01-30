package org.acme.dto.book

data class BookResponseDTO(
    val books: List<BookFullDTO>,
    val numOfPages: Int
)
