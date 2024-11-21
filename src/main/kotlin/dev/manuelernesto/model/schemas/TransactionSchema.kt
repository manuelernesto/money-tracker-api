package dev.manuelernesto.model.schemas

import dev.manuelernesto.model.enums.TransactionType
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  21/11/24 9:48 PM
 * @version 1.0
 */

object Transactions : Table("transaction") {
    val id = uuid("id")
    val userId = (uuid("account_id") references Accounts.id)
    val categoryId = (uuid("category_id") references Categories.id)
    val amount = decimal("amount", 10, 2)
    val type = enumerationByName("type", 20, TransactionType::class)
    val note = varchar("note", 255).nullable()
    val date = datetime("created_at")

    override val primaryKey: PrimaryKey = PrimaryKey(id)
}
