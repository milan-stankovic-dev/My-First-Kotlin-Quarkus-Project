package org.acme.exception

data class ExceptionPayload(val errorType : ErrorType,
                            val errors: List<String?>)

