package org.acme.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import jakarta.ws.rs.NotFoundException
import org.acme.BookTitleDTO
import org.acme.model.Book
import org.acme.repository.BookRepository

@ApplicationScoped
class BookService {
    @Inject
    lateinit var bookRepository: BookRepository
    fun getAllBooks() : List<Book> = bookRepository.listAll()

    fun saveABook(book: Book): Book {
        bookRepository.persist(book)
        return book
    }

    @Transactional
    fun deleteById(id: Long) {
        bookRepository.deleteById(id)
    }

    @Transactional
    fun updateTitle(id: Long, newTitle: BookTitleDTO): Book {
        val bookByIdFromDB: Book = bookRepository.findById(id)
            ?: throw NotFoundException(
                "Book with said id does not exist.")

        bookByIdFromDB.title = newTitle.title
        bookRepository.persist(bookByIdFromDB)

        return bookByIdFromDB
    }
}
