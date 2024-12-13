package org.acme.repository

import io.quarkus.hibernate.orm.panache.kotlin.PanacheRepository
import jakarta.enterprise.context.ApplicationScoped
import org.acme.model.Genre

@ApplicationScoped
class GenreRepository : PanacheRepository<Genre>