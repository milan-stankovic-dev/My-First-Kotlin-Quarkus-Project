package org.acme.dto.category

import jakarta.validation.constraints.Size

data class CategorySaveDTO(
    @field:Size(min = 2, max = 255, message = "Category name must be between 2 and 255 characters long.")
    val name: String,
    @field:Size(min = 2, max = 500, message = "Category description must be between 2 and 500 characters long.")
    val description: String)