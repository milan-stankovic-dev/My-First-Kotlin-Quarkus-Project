package org.acme.exception

import com.fasterxml.jackson.annotation.JsonValue

enum class ErrorType(@JsonValue val type: String) {
    SERVER_ERROR("server error"),
    JSON_ERROR("json error"),
    CONSTRAINT_VIOLATION("constraint violation"),
    ILLEGAL_ARGUMENT("illegal argument"),
    NOT_FOUND("entity not found"),
    UNAUTHORIZED("unauthorized"),
    FORBIDDEN("forbidden"),
    ROLLBACK("rollback occurred"),
    TRANSACTION_NEEDED("transaction not active"),
    DB_VIOLATION("database exception violation")
}