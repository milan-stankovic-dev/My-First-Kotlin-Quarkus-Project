package org.acme.dto.user

import jakarta.validation.constraints.Size

data class LoginRequest(
    @field:Size(min = 2, max = 255, message = "Email must be between 2 and 255 characters long.")
    val email: String,
    @field:Size(min = 2, max = 255, message = "Password must be between 2 and 255 characters long.")
    val password: String)
