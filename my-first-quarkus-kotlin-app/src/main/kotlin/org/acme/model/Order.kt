package org.acme.model

import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.OneToMany
import org.acme.audit.AuditListener
import org.acme.dto.AuditMetadata
import java.time.LocalDate

@Entity(name = "tbl_order")
@EntityListeners(value = [AuditListener::class])
class Order(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null,
    var date: LocalDate? = null,
    @ManyToMany
    @JoinTable(
        name="book_order",
        joinColumns = [JoinColumn(name = "order_id")],
        inverseJoinColumns = [JoinColumn(name = "book_id")])
    var books: MutableList<Book> = mutableListOf(),
    metadata: AuditMetadata?) : AuditableEntityBase(metadata) {

        init {
            this.metadata = metadata
        }

    constructor() : this(
        null, LocalDate.now(), mutableListOf(), null
    )

    override fun getAllIds(): List<Long?> {
        return listOf(id)
    }
}