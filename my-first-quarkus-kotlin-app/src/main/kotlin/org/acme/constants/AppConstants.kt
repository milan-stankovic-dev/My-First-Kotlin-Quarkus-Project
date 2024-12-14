package org.acme.constants

import org.acme.dto.AuditMetadata

const val NULL_ID_ERROR: String = "Found null ID for author in mapper."

const val NOT_FOUND_BY_ID_ERROR : String = "Could not found said entity by id."

val METADATA : AuditMetadata = AuditMetadata("userRef",
    "uiAction","serviceName")