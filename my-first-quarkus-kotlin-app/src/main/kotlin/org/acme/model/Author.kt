package org.acme.model

import io.quarkus.hibernate.orm.panache.kotlin.PanacheEntityBase
import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType.IDENTITY
import jakarta.persistence.Id
import jakarta.persistence.OneToMany

@Entity
class Author(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null,
    @Column(name = "first_name", nullable = false)
    var name: String,
    @Column(name = "last_name", nullable = false)
    var lastName:String,
    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "author")
    var books : MutableList<Book> = mutableListOf()
) : PanacheEntityBase {
    
    constructor() : this(
        null, "", "", mutableListOf<Book>())
}