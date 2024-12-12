package org.acme.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id

@Entity
class Category(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long?,
    @Column(name = "name", nullable = false)
    var name: String,
    @Column(name = "description", length = 500)
    var description: String?) : PanacheEntityBase {
    constructor() : this(null, "", null)
}