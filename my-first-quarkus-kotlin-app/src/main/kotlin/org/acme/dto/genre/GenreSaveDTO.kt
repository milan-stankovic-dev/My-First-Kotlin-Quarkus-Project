package org.acme.dto.genre

import jakarta.validation.constraints.Size

data class GenreSaveDTO(
    @Size(min = 2, max = 255, message = "Genre name must be between 2 and 255 characters long.")
    val name: String,
    @Size(min = 2, max = 500, message = "Genre description must be between 2 and 500 characters long.")
    val description: String?)