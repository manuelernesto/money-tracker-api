package dev.manuelernesto.util

import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  17/10/24 10:21 PM
 * @version 1.0
 */

fun validateUUIDAndGet(uuid: String): UUID {
    return try {
        UUID.fromString(uuid)
    } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException("Invalid UUID format: $uuid")
    }
}
