package org.acme.mapper

import org.acme.dto.BookFullDTO
import org.acme.dto.BookSaveDTO
import org.acme.model.Book

fun Book.toFullDTO() : BookFullDTO = BookFullDTO(
        id ?: throw IllegalArgumentException("Book ID found to be null in mapper"),
        title, price, isbn, 
    author?.toFullDTO() ?: throw IllegalStateException("Book author found to be null in mapper") )

fun Book.toSaveDTO() = BookSaveDTO(title, price, isbn,
    author?.toSaveDTO() 
        ?: throw IllegalStateException("Book's author found to be null in mapper"))

fun BookSaveDTO.toBook() : Book = Book(null, title, price, isbn, null)

fun BookFullDTO.toBook() : Book = Book(id, title, price, isbn, author.toAuthor())