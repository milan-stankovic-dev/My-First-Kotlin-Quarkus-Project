package org.acme.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.acme.dto.book.BookFullDTO
import org.acme.mapper.toFullDTO
import org.acme.model.Book
import org.acme.model.Genre
import org.acme.repository.BookRepository
import org.acme.repository.GenreRepository

@ApplicationScoped
class GenreService {
    @Inject
    lateinit var repository: GenreRepository
    @Inject
    lateinit var bookRepository: BookRepository

    @Transactional
    fun assignGenreToBook(genreId: Long, bookId: Long): BookFullDTO {
        val genreFromDB : Genre = 
            repository.findById(genreId) ?: 
            throw EntityNotFoundException("Could not find genre with id $genreId")
        val bookFromDB : Book = 
            bookRepository.findById(bookId) ?: 
            throw EntityNotFoundException("Could not find book with id $bookId")
        
        bookFromDB.genre = genreFromDB
        bookRepository.persist(bookFromDB)
        
        return bookFromDB.toFullDTO()
    }
}