package dev.manuelernesto.repository

import dev.manuelernesto.config.dbQuery
import dev.manuelernesto.model.Account
import dev.manuelernesto.model.schemas.Accounts
import dev.manuelernesto.model.schemas.Users
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  19/10/24 8:58 PM
 * @version 1.0
 */
class AccountRepository {
    suspend fun createAccount(user: UUID, account: Account): Account? = dbQuery {
        val inserted = Accounts.insert {
            it[id] = UUID.randomUUID()
            it[userId] = user
            it[name] = account.name
            it[balance] = account.balance
            it[type] = account.type
            it[currency] = account.currency
            it[institution] = account.institution
            it[description] = account.description
            it[isClosed] = account.isClosed
            it[createdAt] = account.createdAt!!
            it[updatedAt] = account.updatedAt

        }
        inserted.resultedValues?.singleOrNull()?.let { Account.fromResultRow(it) }
    }

    suspend fun getAccountsByUserId(userId: UUID): List<Account> = dbQuery {
        (Accounts innerJoin Users).selectAll().where { Accounts.userId eq userId }.map {
            Account.fromResultRow(it)
        }
    }
}