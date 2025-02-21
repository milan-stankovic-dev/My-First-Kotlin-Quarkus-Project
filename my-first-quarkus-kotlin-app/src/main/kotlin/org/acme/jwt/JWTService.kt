package org.acme.jwt

import io.smallrye.jwt.build.Jwt
import jakarta.inject.Singleton
import java.lang.System.currentTimeMillis

@Singleton
class JWTService {
    fun generateJWT() : String {
        return Jwt.issuer("bookstore-jwt")
            .subject("bookstore-jwt")
            .groups(UserRoles.allRolesString)
            .expiresAt(
                currentTimeMillis() + 36000)
            .sign()
    }
}