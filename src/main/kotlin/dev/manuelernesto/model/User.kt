package dev.manuelernesto.model

import dev.manuelernesto.model.schemas.Users
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow
import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  11/10/24 8:53 PM
 * @version 1.0
 */
@Serializable
data class User(
    @Contextual val userId: UUID? = null,
    val username: String,
    var password: String,
    val email: String? = null,
) {
    companion object {
        fun fromResultRow(row: ResultRow) = User(
            userId = row[Users.id],
            username = row[Users.username],
            password = row[Users.password],
            email = row[Users.email]
        )
    }
}

