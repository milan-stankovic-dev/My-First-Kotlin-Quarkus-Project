package org.acme.dto.author

import jakarta.validation.constraints.Size

data class AuthorSaveDTO(
    @Size(min = 2, max = 255, message = "Author name must be between 2 and 255 characters long.")
    val name: String,
    @Size(min = 2, max = 255, message = "Author last name must be between 2 and 255 characters long.")
    val lastName: String)