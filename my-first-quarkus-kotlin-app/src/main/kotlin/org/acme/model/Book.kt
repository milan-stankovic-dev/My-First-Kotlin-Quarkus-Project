package org.acme.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.CascadeType
import jakarta.persistence.CascadeType.ALL
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.math.BigDecimal
import java.math.BigDecimal.ZERO


@Entity
class Book (
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null,
    var title: String,
    var price: BigDecimal,
    var isbn: String,
    @ManyToOne(cascade = [ALL])
    @JoinColumn(name= "author_id", nullable = false)
    var author: Author?,
    @ManyToOne(cascade = [ALL])
    @JoinColumn(name = "category_id")
    var category: Category?,
    @ManyToOne(cascade = [ALL])
    @JoinColumn(name = "genre_id")
    var genre: Genre?
    ) : PanacheEntityBase {
    
    constructor() : this(
        null, "", ZERO, "",
        null, null, null)
}