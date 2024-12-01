package org.acme.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import java.math.BigDecimal

@Entity
class Book {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null
    lateinit var title: String
    lateinit var price: BigDecimal
    lateinit var isbn: String
}