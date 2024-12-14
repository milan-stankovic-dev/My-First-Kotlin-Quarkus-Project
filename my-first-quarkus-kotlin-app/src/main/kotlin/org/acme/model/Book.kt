package org.acme.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.CascadeType
import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import org.acme.audit.AuditListener
import org.acme.dto.AuditMetadata
import java.math.BigDecimal
import java.math.BigDecimal.ZERO


@Entity
@EntityListeners(value = [AuditListener::class])
class Book (
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null,
    var title: String,
    var price: BigDecimal,
    var isbn: String,
    @ManyToOne
    @JoinColumn(name= "author_id", nullable = false)
    var author: Author?,
    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: Category?,
    @ManyToOne
    @JoinColumn(name = "genre_id")
    var genre: Genre?,
    metadata: AuditMetadata?) : AuditableEntityBase(metadata) {

    init {
        this.metadata = metadata
    }
    
    constructor() : this(
        null, "", ZERO, "",
        null, null, null, null)

    override fun getAllIds(): List<Long?> {
        return listOf(id)
    }
}