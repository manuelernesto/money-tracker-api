package dev.manuelernesto.util

import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  17/10/24 10:21 PM
 * @version 1.0
 */

fun String.validateAndGet(): UUID {
    return try {
        UUID.fromString(this.toString())
    } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException("Invalid UUID format: $this")
    }
}
