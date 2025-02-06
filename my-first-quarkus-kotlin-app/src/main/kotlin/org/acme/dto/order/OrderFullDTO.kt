package org.acme.dto.order

import org.acme.dto.book.BookFullDTO
import java.time.LocalDate

data class OrderFullDTO(
    val id: Long,
    val date: LocalDate,
    val books: List<BookFullDTO>
)
