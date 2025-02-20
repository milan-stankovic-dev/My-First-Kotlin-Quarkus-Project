package org.acme.jwt

enum class UserRoles(val value: String) {
    ADMIN("admin"), USER("user");

    companion object {
        val allRolesString: Set<String>
            get() = entries
                .map { e -> e.value }
                .toSet()
    }
}