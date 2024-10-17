package dev.manuelernesto.model.response

import dev.manuelernesto.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  16/10/24 8:22 PM
 * @version 1.0
 */

@Serializable
data class UserResponse(
    @Serializable(with = UUIDSerializer::class) val userId: UUID? = null,
    val username: String,
    val email: String? = null,
)