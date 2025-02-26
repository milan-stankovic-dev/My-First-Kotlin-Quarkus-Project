package org.acme.validator

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.annotation.AnnotationTarget.PROPERTY
import kotlin.annotation.AnnotationTarget.VALUE_PARAMETER
import kotlin.reflect.KClass

@Retention(RUNTIME)
@Target(FIELD, PROPERTY, VALUE_PARAMETER)
@Constraint(validatedBy = [NameValidator::class])
annotation class ValidName(
    val message: String = "Name format invalid",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [])
