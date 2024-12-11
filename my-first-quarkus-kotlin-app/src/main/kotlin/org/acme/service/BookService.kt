package org.acme.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import jakarta.ws.rs.NotFoundException
import org.acme.dto.BookFullDTO
import org.acme.dto.BookSaveDTO
import org.acme.dto.BookTitleDTO
import org.acme.mapper.toBook
import org.acme.mapper.toFullDTO
import org.acme.model.Book
import org.acme.repository.BookRepository

@ApplicationScoped
class BookService {
    @Inject
    lateinit var bookRepository: BookRepository

    fun getAllBooks() : List<BookFullDTO> =
        bookRepository
        .streamAll().map {
            it.toFullDTO()
        }.toList()

    @Transactional
    fun saveABook(bookSaveDTO: BookSaveDTO): BookFullDTO {
        val book = bookSaveDTO.toBook()
        bookRepository.persist(book)

        return book.toFullDTO()
    }

    @Transactional
    fun deleteById(id: Long) {
        val successful = bookRepository.deleteById(id)
        
        if(!successful) 
            throw EntityNotFoundException("Entity with given id does not exist.")
    }


    @Transactional
    fun updateTitle(id: Long, newTitle: BookTitleDTO): BookFullDTO {
        val bookByIdFromDB: Book = bookRepository.findById(id)
            ?: throw NotFoundException(
                "Book with said id does not exist."
            )

        bookByIdFromDB.title = newTitle.title
        bookRepository.persist(bookByIdFromDB)

        return bookByIdFromDB.toFullDTO()
    }
}
