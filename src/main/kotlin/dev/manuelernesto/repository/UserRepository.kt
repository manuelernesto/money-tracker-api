package dev.manuelernesto.repository

import dev.manuelernesto.config.dbQuery
import dev.manuelernesto.model.User
import dev.manuelernesto.model.schemas.Users
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.update
import org.jetbrains.exposed.sql.upperCase
import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  14/10/24 7:26 PM
 * @version 1.0
 */

class UserRepository() {

    suspend fun save(user: User): User? = dbQuery {
        val inserted = Users.insert {
            it[username] = user.username
            it[password] = user.password
            it[email] = user.email
        }
        inserted.resultedValues?.singleOrNull()?.let { User.fromResultRow(it) }
    }

    suspend fun getUserById(userId: UUID): User? = dbQuery {
        Users.selectAll().where { Users.id eq userId }.map { User.fromResultRow(it) }.singleOrNull()
    }


    suspend fun getUserByUsername(username: String): User? = dbQuery {
        Users.selectAll().where { Users.username.upperCase() like username.uppercase() }.map { User.fromResultRow(it) }
            .singleOrNull()
    }

    suspend fun updatePassword(userId: UUID, pwd: String) = dbQuery {
        Users.update({ Users.id eq userId }) {
            it[password] = pwd
        }
    }

    suspend fun delete(userId: UUID) = dbQuery {
        Users.deleteWhere() { id eq userId }
    }

}