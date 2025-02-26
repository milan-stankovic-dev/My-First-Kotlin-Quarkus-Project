package org.acme.validator

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class NameValidator : ConstraintValidator<ValidName, String> {
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        if(value.isNullOrBlank()) return false

        if(value.length < 2 || value.length > 255) return false

        return value.matches(Regex("^[A-Z][a-zA-Z\\s'-]*\$"))
    }

}