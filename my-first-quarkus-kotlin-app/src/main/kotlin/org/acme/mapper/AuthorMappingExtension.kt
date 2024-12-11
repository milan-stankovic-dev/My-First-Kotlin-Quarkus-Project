package org.acme.mapper

import org.acme.dto.AuthorBookDisplayDTO
import org.acme.dto.AuthorSaveDTO
import org.acme.model.Author

fun Author.toFullDTO() = AuthorBookDisplayDTO(
    id ?: throw IllegalStateException("Found null ID for author in mapper."),
    name, lastName)

fun AuthorSaveDTO.toAuthor() = Author(null, name, lastName, mutableListOf())

fun Author.toSaveDTO() = AuthorSaveDTO(name, lastName)

fun AuthorBookDisplayDTO.toAuthor() = Author(id, name, lastName)