package org.acme.dto.user

import org.acme.jwt.UserRoles

data class RegisterRequest(
    val name: String,
    val lastName: String,
    val email: String,
    val password: String,
    val role: UserRoles
)