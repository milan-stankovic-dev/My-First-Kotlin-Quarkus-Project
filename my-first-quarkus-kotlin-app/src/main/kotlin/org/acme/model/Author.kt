package org.acme.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import org.acme.audit.AuditListener
import org.acme.dto.AuditMetadata

@Entity
@EntityListeners(value = [AuditListener::class])
class Author(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null,
    @Column(name = "first_name", nullable = false)
    var name: String,
    @Column(name = "last_name", nullable = false)
    var lastName:String,
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "author")
    var books : MutableList<Book> = mutableListOf(),
    metadata: AuditMetadata?
) : AuditableEntityBase(metadata) {

    init {
        this.metadata = metadata
    }
    
    constructor() : this(
        null, "", "", mutableListOf<Book>(), null)

    override fun getAllIds(): List<Long?> {
        return listOf(id)
    }
}