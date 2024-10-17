package dev.manuelernesto.config

import dev.manuelernesto.model.schemas.Categories
import dev.manuelernesto.model.schemas.Users
import io.ktor.server.application.Application
import io.ktor.server.config.ApplicationConfigurationException
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  16/10/24 8:42 PM
 * @version 1.0
 */

fun Application.configureDB() {
    val dbUser =
        environment.config.propertyOrNull("ktor.database.username")?.getString()
            ?: throw ApplicationConfigurationException("Database User not configured")
    val dbPassword =
        environment.config.propertyOrNull("ktor.database.password")?.getString()
            ?: throw ApplicationConfigurationException("Database Password not configured")

    val dbUrl =
        environment.config.propertyOrNull("ktor.database.url")?.getString()
            ?: throw ApplicationConfigurationException("Database URL not configured")

    val dbDriver =
        environment.config.propertyOrNull("ktor.database.driver")?.getString()
            ?: throw ApplicationConfigurationException("Database Driver not configured")

    Database.connect(
        url = dbUrl,
        driver = dbDriver,
        user = dbUser,
        password = dbPassword
    )

    transaction {
        SchemaUtils.create(Users, Categories)
    }
}

suspend fun <T> dbQuery(block: suspend () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }