package dev.manuelernesto.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  11/10/24 8:53 PM
 * @version 1.0
 */
@Serializable
data class User(val userId: Long? = null, val username: String, var password: String, val email: String?) {

    companion object {
        fun fromResultRow(row: ResultRow) = User(
            userId = row[Users.id],
            username = row[Users.username],
            password = row[Users.password],
            email = row[Users.email]
        )
    }
}

object Users : Table() {
    val id = long("userId").autoIncrement()
    val username = varchar("username", 255).uniqueIndex()
    val password = varchar("password", 255)
    val email = varchar("password", 255).nullable()

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}
