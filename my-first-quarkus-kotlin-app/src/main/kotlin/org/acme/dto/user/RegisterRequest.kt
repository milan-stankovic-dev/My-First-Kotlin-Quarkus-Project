package org.acme.dto.user

import jakarta.validation.constraints.Size
import org.acme.jwt.UserRoles

data class RegisterRequest(
    @field:Size(min = 2, max = 255, message = "Name must be between 2 and 255 characters long.")
    val name: String,
    @field:Size(min = 2, max = 255, message = "Last name must be between 2 and 255 characters long.")
    val lastName: String,
    @field:Size(min = 2, max = 255, message = "Email must be between 2 and 255 characters long.")
    val email: String,
    @field:Size(min = 2, max = 255, message = "Password must be between 2 and 255 characters long.")
    val password: String,
    val role: UserRoles)