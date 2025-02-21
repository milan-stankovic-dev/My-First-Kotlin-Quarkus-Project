package org.acme.model

import jakarta.persistence.*
import org.acme.dto.AuditMetadata
import org.acme.jwt.UserRoles
import org.acme.jwt.UserRoles.USER
import jakarta.persistence.GenerationType.IDENTITY
import org.acme.constants.METADATA
import org.mindrot.jbcrypt.BCrypt

@Entity
@Table(name = "app_user")
class User(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null,
    @Column(nullable = false, length = 500)
    var name: String,
    @Column(name = "last_name",
        nullable = false, length = 500)
    var lastName : String,
    @Column(unique = true, nullable = false)
    var email: String,
    @Column(nullable = false, length = 1000)
    var passwordHash: String,
    @Column(name = "user_role")
    var role: UserRoles = USER,
    metadata: AuditMetadata?) : AuditableEntityBase(metadata) {

    init {
        this.metadata = metadata
    }

    fun verifyPassword(password: String) : Boolean {
        return BCrypt.checkpw(password, this.passwordHash)
    }

    override fun getAllIds(): List<Long?> {
        return mutableListOf(id)
    }

    constructor() : this(
        null, "", "",
        "", "", USER, METADATA)
}