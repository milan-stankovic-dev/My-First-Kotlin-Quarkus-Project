package org.acme.mapper

import org.acme.dto.BookAuthorDisplayDTO
import org.acme.dto.BookFullDTO
import org.acme.dto.BookSaveDTO
import org.acme.model.Book

fun Book.toDisplayDTO() : BookFullDTO = BookFullDTO(
        id ?: throw IllegalArgumentException("Book ID found to be null in mapper"),
        title, price, isbn, 
    author?.toDisplayDTO() 
        ?: throw IllegalStateException("Book author found to be null in mapper"))

fun BookSaveDTO.toBook() : Book = Book(null, title, price, isbn, null)
fun BookFullDTO.toBook() : Book = Book(id, title, price, isbn, author.toAuthor())
fun Book.toBookAuthorDisplayDTO() : BookAuthorDisplayDTO = BookAuthorDisplayDTO(
    id ?: throw IllegalStateException("Book ID found to be null in mapper"),
    title,
    price,
    isbn)