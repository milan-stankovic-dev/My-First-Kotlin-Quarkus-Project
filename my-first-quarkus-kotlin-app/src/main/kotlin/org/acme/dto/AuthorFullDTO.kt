package org.acme.dto

data class AuthorFullDTO(
    val id: Long, 
    val name: String, 
    val lastName: String, 
    val books: List<BookAuthorDisplayDTO>)