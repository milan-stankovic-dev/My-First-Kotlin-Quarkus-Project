package org.acme.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.acme.constants.METADATA
import org.acme.constants.NOT_FOUND_BY_ID_ERROR
import org.acme.dto.AuditMetadata
import org.acme.dto.author.AuthorBookDisplayDTO
import org.acme.dto.author.AuthorFullDTO
import org.acme.dto.author.AuthorSaveDTO
import org.acme.mapper.toAuthor
import org.acme.mapper.toDisplayDTO
import org.acme.mapper.toFullDTO
import org.acme.model.Author
import org.acme.repository.AuthorRepository

@ApplicationScoped
class AuthorService {
    @Inject
    lateinit var repository: AuthorRepository
    final val AUTHOR_NOT_FOUND_ERROR = "Cannot find author with said id."
    
    
    fun getById(id: Long): AuthorFullDTO =
        repository.findById(id)?.toFullDTO()
            ?: throw EntityNotFoundException(
                "Author not found for said id: $id")

    @Transactional
    fun save(authorSaveDTO: AuthorSaveDTO): AuthorFullDTO {
        val convertedAuthor = authorSaveDTO.toAuthor()
        convertedAuthor.metadata = METADATA
        repository.persist(convertedAuthor)
        
        return convertedAuthor.toFullDTO()
    }

//    @Transactional
//    fun deleteById(id: Long) {
//        if (!repository.deleteById(id)) {
//            throw EntityNotFoundException(AUTHOR_NOT_FOUND_ERROR)
//        }
//    }

    @Transactional
    fun deleteById(id: Long) {
        val authorByIdFromDB: Author = repository.findById(id)
            ?: throw EntityNotFoundException(NOT_FOUND_BY_ID_ERROR)
        
        authorByIdFromDB.metadata = METADATA
        authorByIdFromDB.books.forEach { it.metadata = METADATA }
        repository.delete(authorByIdFromDB)  
    }
    
    @Transactional
    fun update(id: Long, authorData: AuthorSaveDTO): AuthorBookDisplayDTO {
        val authorFromDB: Author = 
            repository.findById(id) 
                ?: throw EntityNotFoundException(AUTHOR_NOT_FOUND_ERROR)
        
        authorFromDB.name = authorData.name
        authorFromDB.lastName = authorData.lastName
        authorFromDB.setMetadata(METADATA)
        
        repository.persist(authorFromDB)
        
        return authorFromDB.toDisplayDTO()
    }

}