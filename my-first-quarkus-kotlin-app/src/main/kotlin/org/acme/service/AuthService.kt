package org.acme.service

import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.transaction.Transactional
import org.acme.constants.METADATA
import org.acme.dto.user.LoginRequest
import org.acme.dto.user.RegisterRequest
import org.acme.model.User
import org.acme.repository.UserRepository
import org.mindrot.jbcrypt.BCrypt

@ApplicationScoped
class AuthService {
    @Inject
    lateinit var repository: UserRepository

    @Transactional
    fun registerUser(request: RegisterRequest): User {
        val requestEmail = request.email
        val hashedPwd = BCrypt.hashpw(request.password, BCrypt.gensalt())
        if(repository.findByEmail(requestEmail) != null) {
            throw IllegalArgumentException("Email already in use. Try new email.")
        }

        val userToSave = User(
            id = null,
            name = request.name,
            lastName = request.lastName,
            email = requestEmail,
            passwordHash = hashedPwd,
            role = request.role,
            METADATA)

        repository.persist(userToSave)
        return userToSave
    }

    fun authenticate(request: LoginRequest): User? {
        val user = repository.findByEmail(request.email)
        return if(user!= null && user.verifyPassword(request.password))
            user else null
    }
}