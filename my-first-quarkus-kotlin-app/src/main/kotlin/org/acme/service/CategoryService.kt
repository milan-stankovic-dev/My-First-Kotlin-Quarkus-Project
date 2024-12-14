package org.acme.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.acme.constants.METADATA
import org.acme.constants.NOT_FOUND_BY_ID_ERROR
import org.acme.dto.book.BookFullDTO
import org.acme.mapper.toFullDTO
import org.acme.model.Book
import org.acme.model.Category
import org.acme.model.Genre
import org.acme.repository.BookRepository
import org.acme.repository.CategoryRepository

@ApplicationScoped
class CategoryService {
    @Inject
    lateinit var repository : CategoryRepository
    @Inject
    lateinit var bookRepository: BookRepository
    
    @Transactional
    fun assignCategoryToBook(categoryId: Long, bookId: Long) : BookFullDTO {
        val categoryFromDB : Category = 
            repository.findById(categoryId) ?: 
            throw EntityNotFoundException("Could not find category with id $categoryId")
        val bookFromDB : Book = 
            bookRepository.findById(bookId) ?: 
            throw EntityNotFoundException("Could not find book with id $bookId")
        
        bookFromDB.category = categoryFromDB
        bookFromDB.setMetadata(METADATA)
        bookRepository.persist(bookFromDB)
        
        return bookFromDB.toFullDTO() 
    }

    fun findById(id: Long) : Category =
        repository.findById(id) ?:
        throw EntityNotFoundException(NOT_FOUND_BY_ID_ERROR)
}