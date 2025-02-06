package org.acme.mapper

import org.acme.constants.METADATA
import org.acme.constants.NULL_ID_ERROR
import org.acme.dto.category.CategoryFullDTO
import org.acme.dto.category.CategorySaveDTO
import org.acme.model.Category

fun Category.toFullDTO() =
    CategoryFullDTO(
        id ?: throw IllegalStateException(NULL_ID_ERROR),
        name, description)

fun CategorySaveDTO.toCategory() =
    Category(null, name, description, null)

fun CategoryFullDTO.toCategory() =
    Category(id, name,description, metadata = METADATA)
