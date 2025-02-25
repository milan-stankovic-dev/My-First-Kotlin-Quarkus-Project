package org.acme.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.acme.model.User
import org.acme.repository.UserRepository

@ApplicationScoped
class UserService {
    @Inject
    lateinit var repository: UserRepository

    fun getById(id: Long) : User? {
        return repository.findById(id)
    }
}