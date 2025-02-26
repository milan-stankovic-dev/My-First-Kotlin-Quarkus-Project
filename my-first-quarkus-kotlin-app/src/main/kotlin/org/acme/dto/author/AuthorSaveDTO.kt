package org.acme.dto.author

import jakarta.validation.constraints.Size

data class AuthorSaveDTO(
    @field:Size(min = 2, max = 255, message = "Author name must be between 2 and 255 characters long.")
    val name: String,
    @field:Size(min = 2, max = 255, message = "Author last name must be between 2 and 255 characters long.")
    val lastName: String)