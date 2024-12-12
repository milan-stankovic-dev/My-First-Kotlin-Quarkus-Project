package org.acme.dto.book

import org.acme.dto.author.AuthorBookDisplayDTO
import org.acme.dto.category.CategoryFullDTO
import org.acme.dto.genre.GenreFullDTO
import java.math.BigDecimal

data class BookFullDTO(
    val id: Long,
    val title: String,
    val price: BigDecimal,
    val isbn: String,
    val author: AuthorBookDisplayDTO,
    val category: CategoryFullDTO,
    val genre: GenreFullDTO)