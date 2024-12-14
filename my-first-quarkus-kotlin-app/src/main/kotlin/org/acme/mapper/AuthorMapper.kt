package org.acme.mapper

import org.acme.constants.NULL_ID_ERROR
import org.acme.dto.author.AuthorBookDisplayDTO
import org.acme.dto.author.AuthorFullDTO
import org.acme.dto.author.AuthorFullSaveDTO
import org.acme.dto.author.AuthorSaveDTO
import org.acme.model.Author



fun Author.toDisplayDTO() = AuthorBookDisplayDTO(
    id ?: throw IllegalStateException(NULL_ID_ERROR),
    name, lastName)

fun AuthorSaveDTO.toAuthor() = Author(null, name, lastName, mutableListOf(), null)

fun Author.toSaveDTO() = AuthorSaveDTO(name, lastName)

fun AuthorBookDisplayDTO.toAuthor() = Author(id, name, lastName, mutableListOf(), null)

fun Author.toFullDTO() = AuthorFullDTO(
    getIdOrThrow(id),
    name, lastName, books.map { it.toBookAuthorDisplayDTO() }.toList())

fun Author.toFullSaveDTO() = AuthorFullSaveDTO(
    getIdOrThrow(id), name, lastName)

fun getIdOrThrow(id: Long?) = 
    id ?: throw IllegalStateException(NULL_ID_ERROR)