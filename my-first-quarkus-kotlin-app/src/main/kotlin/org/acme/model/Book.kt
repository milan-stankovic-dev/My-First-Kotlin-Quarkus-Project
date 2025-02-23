package org.acme.model

import jakarta.persistence.*
import jakarta.persistence.GenerationType.IDENTITY
import org.acme.audit.AuditListener
import org.acme.constants.METADATA
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
    @Column(nullable = true, length = 500)
    var imageUrl : String?,
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
        null, "", ZERO, "", null,
        null, null, null, METADATA)

    override fun getAllIds(): List<Long?> {
        return listOf(id)
    }
}