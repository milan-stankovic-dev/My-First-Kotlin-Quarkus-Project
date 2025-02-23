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
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import org.acme.audit.AuditListener
import org.acme.constants.METADATA
import org.acme.dto.AuditMetadata
import java.time.LocalDate

@Entity(name = "tbl_order")
@EntityListeners(value = [AuditListener::class])
class Order(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null,
    var date: LocalDate? = null,
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User?,
    @OneToMany(mappedBy = "order", cascade = [ALL])
    var items: MutableList<OrderItem> = mutableListOf(),
    metadata: AuditMetadata?) : AuditableEntityBase(metadata) {

        init {
            this.metadata = metadata
        }

    constructor() : this(
        null, LocalDate.now(), null,
         mutableListOf(), METADATA)

    override fun getAllIds(): List<Long?> {
        return listOf(id, user?.id)
    }
}