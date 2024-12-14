package org.acme.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import org.acme.audit.AuditListener
import org.acme.dto.AuditMetadata

@Entity
@EntityListeners(value = [AuditListener::class])
class Genre (
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null,
    @Column(nullable = false, unique = true)
    var name: String,
    @Column(name = "description", length = 500)
    var description: String? = null, metadata: AuditMetadata?
) : AuditableEntityBase(metadata) {

    init {
        this.metadata = metadata
    }
    
    constructor() : this(null, "", null, null)

    override fun getAllIds(): List<Long?> {
        return listOf(id)
    }
}