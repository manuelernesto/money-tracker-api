package dev.manuelernesto.model.request

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  25/11/24 10:22 PM
 * @version 1.0
 */
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val username: String,
    val password: String
)

@Serializable
data class LoginResponse(
    val token: String
)