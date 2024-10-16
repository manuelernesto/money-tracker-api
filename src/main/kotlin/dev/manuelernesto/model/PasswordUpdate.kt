package dev.manuelernesto.model

import kotlinx.serialization.Serializable

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  16/10/24 8:23 PM
 * @version 1.0
 */

@Serializable
data class PasswordUpdate(val oldPassword: String, val newPassword: String)