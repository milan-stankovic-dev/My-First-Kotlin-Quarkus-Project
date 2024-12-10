package org.acme.mapper

import org.acme.dto.BookFullDTO
import org.acme.dto.BookSaveDTO
import org.acme.model.Book

fun Book.toFullDTO() = BookFullDTO(
    id = this.id ?: 
    throw IllegalArgumentException("Book ID found to be null in mapper"),
    title = this.title,
    price = this.price, 
    isbn = this.isbn)

fun Book.toSaveDTO() = BookSaveDTO(title, price, isbn)

fun BookSaveDTO.toBook() = Book(null, title, price, isbn)

fun BookFullDTO.toBook() = Book(id, title, price, isbn)