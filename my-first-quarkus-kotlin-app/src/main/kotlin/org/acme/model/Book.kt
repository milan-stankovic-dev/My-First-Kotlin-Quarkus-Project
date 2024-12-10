package org.acme.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import java.math.BigDecimal
import java.math.BigDecimal.ZERO

@Entity
class Book (
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null,
    var title: String,
    var price: BigDecimal,
    var isbn: String) {
    
    constructor() : this(
        null, "", ZERO, "")
}