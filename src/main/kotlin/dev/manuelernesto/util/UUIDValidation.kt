package dev.manuelernesto.util

import dev.manuelernesto.exceptions.InvalidUUIDException
import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  17/10/24 10:21 PM
 * @version 1.0
 */

fun UUID.isValidUUID(): Boolean {
    try {
        UUID.fromString(this.toString())
        return true
    } catch (e: InvalidUUIDException) {
        return false
    }
}
