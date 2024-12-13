package org.acme.mapper

import org.acme.dto.book.BookAuthorDisplayDTO
import org.acme.dto.book.BookFullDTO
import org.acme.dto.book.BookSaveDTO
import org.acme.model.Book

fun Book.toFullDTO() : BookFullDTO = BookFullDTO(
        id ?: throw IllegalArgumentException("Book ID found to be null in mapper"),
        title, price, isbn, 
    author?.toDisplayDTO() 
        ?: throw IllegalStateException("Book author found to be null in mapper"),
    category?.toFullDTO() ?: throw IllegalStateException(NULL_ID_ERROR),
    genre?.toFullDTO() ?: throw IllegalStateException(NULL_ID_ERROR))

fun BookSaveDTO.toBook() : Book = Book(null, title, price, isbn, null, null, null)
fun Book.toBookAuthorDisplayDTO() : BookAuthorDisplayDTO = BookAuthorDisplayDTO(
    id ?: throw IllegalStateException("Book ID found to be null in mapper"),
    title,
    price,
    isbn)