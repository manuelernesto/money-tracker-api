package dev.manuelernesto.repository

import dev.manuelernesto.model.Users
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  14/10/24 7:33 PM
 * @version 1.0
 */

object DBFactory {

    fun init() {
        Database.connect(
            url = "jdbc:postgresql://localhost:5432/money-tracker-db",
            driver = "org.postgresql.Driver",
            user = "postgres",
            password = "password"
        )

        transaction {
            SchemaUtils.create(Users)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
}