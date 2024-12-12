package org.acme.dto.author

import org.acme.dto.book.BookAuthorDisplayDTO

data class AuthorFullDTO(
    val id: Long, 
    val name: String, 
    val lastName: String, 
    val books: List<BookAuthorDisplayDTO>)