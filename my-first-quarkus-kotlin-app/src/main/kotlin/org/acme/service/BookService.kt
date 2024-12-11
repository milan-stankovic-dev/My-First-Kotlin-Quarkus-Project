package org.acme.service

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
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
import org.acme.model.Author
import org.acme.model.Book
import org.acme.repository.AuthorRepository
import org.acme.repository.BookRepository

@ApplicationScoped
class BookService {
    @Inject
    lateinit var repository: BookRepository
    @Inject
    lateinit var authorRepository: AuthorRepository

    fun getAllBooks() : List<BookFullDTO> =
        repository
        .streamAll().map {
            it.toFullDTO()
        }.toList()

    @Transactional
    fun saveABook(bookSaveDTO: BookSaveDTO): BookFullDTO {
        val authorId : Long = bookSaveDTO.authorId
        val authorFromDB : Author = 
            authorRepository.findById(authorId) ?: 
            throw EntityNotFoundException("Author with said id does not exist.")
        
        val book = bookSaveDTO.toBook()
        book.author = authorFromDB
        
        repository.persist(book)
        return book.toFullDTO()
    }

    @Transactional
    fun deleteById(id: Long) {
        val successful = repository.deleteById(id)
        
        if(!successful) 
            throw EntityNotFoundException("Entity with given id does not exist.")
    }


    @Transactional
    fun updateTitle(id: Long, newTitle: BookTitleDTO): BookFullDTO {
        val bookByIdFromDB: Book = repository.findById(id)
            
            ?: throw NotFoundException(
                "Book with said id does not exist."
            )

        bookByIdFromDB.title = newTitle.title
        repository.persist(bookByIdFromDB)

        return bookByIdFromDB.toFullDTO()
    }
}
