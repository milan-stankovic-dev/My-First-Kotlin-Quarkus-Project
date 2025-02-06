package org.acme.dto.order

import org.acme.dto.book.BookFullDTO
import java.time.LocalDate

data class OrderSaveDTO(
    val date: LocalDate,
    val books: List<BookFullDTO>
)
