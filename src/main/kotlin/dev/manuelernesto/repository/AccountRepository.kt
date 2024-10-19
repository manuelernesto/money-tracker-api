package dev.manuelernesto.repository

import dev.manuelernesto.config.dbQuery
import dev.manuelernesto.model.Account
import dev.manuelernesto.model.schemas.Accounts
import org.jetbrains.exposed.sql.insert
import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  19/10/24 8:58 PM
 * @version 1.0
 */
class AccountRepository {
    suspend fun create(account: Account): Account? = dbQuery {
        val inserted = Accounts.insert {
            it[id] = UUID.randomUUID()
            it[userId] = account.userId
            it[name] = account.name
            it[balance] = account.balance
            it[type] = account.type
            it[institution] = account.institution
            it[description] = account.description
            it[isClosed] = account.isClosed
            it[createdAt] = account.createdAt
            it[updatedAt] = account.updatedAt

        }
        inserted.resultedValues?.singleOrNull()?.let { Account.fromResultRow(it) }
    }
}