package org.acme.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.persistence.EntityNotFoundException
import jakarta.transaction.Transactional
import org.acme.dto.AuthorFullDTO
import org.acme.dto.AuthorSaveDTO
import org.acme.mapper.toAuthor
import org.acme.mapper.toFullDTO
import org.acme.repository.AuthorRepository

@ApplicationScoped
class AuthorService {
    @Inject
    lateinit var repository: AuthorRepository
    
    fun getById(id: Long): AuthorFullDTO =
        repository.findById(id)?.toFullDTO()
            ?: throw EntityNotFoundException(
                "Author not found for said id: $id")

    @Transactional
    fun save(authorSaveDTO: AuthorSaveDTO): AuthorFullDTO {
        val convertedAuthor = authorSaveDTO.toAuthor()
        repository.persist(convertedAuthor)
        
        return convertedAuthor.toFullDTO()
    }

    @Transactional
    fun deleteById(id: Long) {
        if (!repository.deleteById(id)) {
            throw EntityNotFoundException(
                "Cannot find author with id $id")
        }
    }

}