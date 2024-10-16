package dev.manuelernesto.model.response

import kotlinx.serialization.Serializable

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  16/10/24 8:22 PM
 * @version 1.0
 */

@Serializable
data class UserResponse(val userId: Long? = null, val username: String, val email: String? = null)