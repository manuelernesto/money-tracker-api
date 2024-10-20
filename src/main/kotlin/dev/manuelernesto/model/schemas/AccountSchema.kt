package dev.manuelernesto.model.schemas

import dev.manuelernesto.model.enums.AccountType
import dev.manuelernesto.model.enums.Currency
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  19/10/24 9:30 PM
 * @version 1.0
 */
object Accounts : Table("account") {
    val id = uuid("id")
    val userId = (uuid("user_id") references Users.id)
    val name = varchar("name", 255)
    val balance = decimal("balance", 10, 2)
    val type = enumerationByName("type", 255, AccountType::class)
    val currency = enumerationByName("currency", 15, Currency::class)
    val institution = varchar("institution", 255).nullable()
    val description = text("description").nullable()
    val isClosed = bool("is_closed")
    val createdAt = datetime("created_at")
    val updatedAt = datetime("updated_at").nullable()

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}