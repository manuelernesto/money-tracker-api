package dev.manuelernesto.repository

import dev.manuelernesto.config.dbQuery
import dev.manuelernesto.model.Account
import dev.manuelernesto.model.enums.AccountType
import dev.manuelernesto.model.enums.Currency
import dev.manuelernesto.model.request.AccountUpdateRequest
import dev.manuelernesto.model.schemas.Accounts
import dev.manuelernesto.model.schemas.Users
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.math.BigDecimal
import java.time.LocalDateTime
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

    suspend fun getAccountById(accountId: UUID): Account? = dbQuery {
        Accounts.selectAll().where { Accounts.id eq accountId }.singleOrNull()?.let { Account.fromResultRow(it) }
    }

    suspend fun deleteAccount(accountId: UUID) = dbQuery {
        Accounts.deleteWhere() { id eq accountId }
    }

    fun updateAccountDetails(accountId: UUID, account: AccountUpdateRequest): Account? = transaction {
        val existingAccount =
            Accounts.selectAll().where { Accounts.id eq accountId }.singleOrNull()?.let { Account.fromResultRow(it) }
                ?: return@transaction null

        val account = Accounts.update({ Accounts.id eq accountId}) {
            it[name] = account.name.takeIf { !it.isNullOrBlank() } ?: existingAccount.name
            it[type] = account.type.takeIf { it != AccountType.CURRENT } ?: existingAccount.type
            it[currency] = account.currency.takeIf { it != Currency.USD } ?: existingAccount.currency
            it[institution] = account.institution.takeIf { !it.isNullOrBlank() } ?: existingAccount.institution
            it[description] = account.description.takeIf { !it.isNullOrBlank() } ?: existingAccount.description
            it[updatedAt] = LocalDateTime.now()
        }

        if (account > 0) {
            Accounts.selectAll().where { Accounts.id eq accountId }.singleOrNull()?.let { Account.fromResultRow(it) }
        } else
            null
    }

    suspend fun closeAccount(accountId: UUID) = dbQuery {
        //TODO update closed account to true
    }

    suspend fun increaseAndDecreaseBalance(accountId: UUID, balance: BigDecimal) = dbQuery {
        //TODO
    }


}