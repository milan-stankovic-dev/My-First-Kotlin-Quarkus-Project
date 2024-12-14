package org.acme.mapper

import org.acme.constants.NULL_ID_ERROR
import org.acme.dto.genre.GenreFullDTO
import org.acme.dto.genre.GenreSaveDTO
import org.acme.model.Genre

fun Genre.toFullDTO() = 
    GenreFullDTO(id ?: throw IllegalStateException(NULL_ID_ERROR),
        name, 
        description)

fun GenreSaveDTO.toGenre() = 
    Genre(null, name, description, null)