package org.acme.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import jakarta.ws.rs.NotFoundException
import org.acme.constants.METADATA
import org.acme.constants.NOT_FOUND_BY_ID_ERROR
import org.acme.dto.book.BookFullDTO
import org.acme.dto.book.BookResponseDTO
import org.acme.dto.book.BookSaveDTO
import org.acme.dto.book.BookTitleDTO
import org.acme.mapper.toBook
import org.acme.mapper.toFullDTO
import org.acme.model.Author
import org.acme.model.Book
import org.acme.model.Category
import org.acme.model.Genre
import org.acme.repository.AuthorRepository
import org.acme.repository.BookRepository
import org.acme.repository.GenreRepository
import kotlin.math.ceil
import kotlin.streams.toList

@ApplicationScoped
class BookService {
    @Inject
    lateinit var repository: BookRepository
    @Inject
    lateinit var authorRepository: AuthorRepository
    @Inject
    lateinit var genreService: GenreService
    @Inject
    lateinit var categoryService: CategoryService

    fun getAllBooks(page : Int?, size: Int?) : BookResponseDTO {
        val safePage : Int = page ?: 0
        val safeSize : Int = size ?: 10
        
        val pageOfBooks : List<BookFullDTO> =
            repository.findAll()
                .page(safePage, safeSize)
                .stream().map { it.toFullDTO() }.toList()
        val totalEntryCount = repository.count()
        
        val numOfPages : Int =
            ceil(totalEntryCount.toDouble() / safeSize.toDouble()).toInt()
        
        return BookResponseDTO(pageOfBooks, numOfPages)
    }
//        repository
//        .streamAll().map {
//            it.toFullDTO()
//        }.toList()

    @Transactional
    fun saveABook(bookSaveDTO: BookSaveDTO): BookFullDTO {
        val authorId : Long = bookSaveDTO.authorId
        val genreId : Long = bookSaveDTO.genreId
        val categoryId : Long = bookSaveDTO.categoryId
        
        val authorFromDB : Author = 
            authorRepository.findById(authorId) ?: 
            throw EntityNotFoundException("Author with said id does not exist.")
        
        val genreFromDB : Genre = genreService.findById(genreId)
        val categoryFromDB : Category = categoryService.findById(categoryId)
            
        val book = bookSaveDTO.toBook()
        book.author = authorFromDB
        book.genre = genreFromDB
        book.category = categoryFromDB
        book.metadata = METADATA
        
        repository.persist(book)
        return book.toFullDTO()
    }

//    @Transactional
//    fun deleteById(id: Long) {
//        val successful = repository.deleteById(id)
//        
//        if(!successful) 
//            throw EntityNotFoundException("Entity with given id does not exist.")
//    }

    @Transactional
    fun deleteById(id: Long) { 
        val bookByIdFromDB : Book = repository.findById(id) 
            ?: throw EntityNotFoundException(NOT_FOUND_BY_ID_ERROR)
        
        bookByIdFromDB.setMetadata(METADATA)
        
        repository.delete(bookByIdFromDB)
    }

    fun getById(id: Long) : BookFullDTO? =
        repository.findById(id)?.toFullDTO()

    @Transactional
    fun updateTitle(id: Long, newTitle: BookTitleDTO): BookFullDTO {
        val bookByIdFromDB: Book = repository.findById(id)
            
            ?: throw NotFoundException(
                "Book with said id does not exist."
            )

        bookByIdFromDB.title = newTitle.title
        bookByIdFromDB.setMetadata(METADATA)
        repository.persist(bookByIdFromDB)

        return bookByIdFromDB.toFullDTO()
    }
}
