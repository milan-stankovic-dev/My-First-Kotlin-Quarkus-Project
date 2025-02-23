package org.acme.model

import jakarta.persistence.*
import org.acme.dto.AuditMetadata
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.validation.constraints.Min
import org.acme.constants.METADATA

@Entity
@Table(name = "order_item")
class OrderItem(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id : Long?,
    @ManyToOne
    @JoinColumn(name = "book_id")
    var book : Book?,
    @ManyToOne
    @JoinColumn(name = "order_id")
    var order: Order?,
    @Column
    @Min(value = 1)
    var amount: Int,
    metadata: AuditMetadata?
) : AuditableEntityBase(metadata){
    override fun getAllIds(): List<Long?> {
        return listOf(id, order?.id, order?.user?.id)
    }

    constructor() : this(null,
        null, null, 0, METADATA) {
    }
}