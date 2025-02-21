package org.acme.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped
import org.acme.model.User

@ApplicationScoped
class UserRepository : PanacheRepository<User> {
    fun findByEmail(email: String) : User? {
        return find("email",email).firstResult()
    }
}