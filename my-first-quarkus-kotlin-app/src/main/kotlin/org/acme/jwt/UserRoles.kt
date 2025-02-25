package org.acme.jwt

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

enum class UserRoles(@JsonValue val value: String) {
    ADMIN("admin"), USER("user");

    companion object {
        val allRolesString: Set<String>
            @JsonCreator
            @JvmStatic
            get() = entries
                .map { e -> e.value }
                .toSet()
    }
}