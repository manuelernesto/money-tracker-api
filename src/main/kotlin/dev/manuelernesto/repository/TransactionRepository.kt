package dev.manuelernesto.repository

import dev.manuelernesto.config.dbQuery
import dev.manuelernesto.model.Transaction
import dev.manuelernesto.model.request.TransactionRequest
import dev.manuelernesto.model.schemas.Transactions
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import java.time.LocalDateTime
import java.util.*

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  21/11/24 10:02 PM
 * @version 1.0
 */

class TransactionRepository {

    suspend fun createTransaction(accountId: UUID, transaction: TransactionRequest): Transaction? = dbQuery {
        val inserted = Transactions.insert {
            it[id] = UUID.randomUUID()
            it[Transactions.accountId] = accountId
            it[amount] = transaction.amount
            it[categoryId] = transaction.categoryId
            it[type] = transaction.type
            it[note] = transaction.note
            it[date] = LocalDateTime.now()
        }

        inserted.resultedValues?.singleOrNull()?.let { Transaction.fromResultRow(it) }
    }

    suspend fun getTransactionById(transactionId: UUID): Transaction? = dbQuery {
        Transactions.selectAll().where { Transactions.id eq transactionId }.map { Transaction.fromResultRow(it) }
            .singleOrNull()
    }


    suspend fun getTransactionsByAccountId(accountId: UUID) {
        //TODO
    }

    suspend fun getTransactionsByUserId(userId: UUID) {
        //TODO
    }

    suspend fun getTransactionsByCategoryId(categoryId: UUID) {
        //TODO
    }

    suspend fun updateTransaction(transactionId: UUID, transaction: Transaction) {
        //TODO
    }

    suspend fun deleteTransaction(transactionId: UUID) {
        //TODO
    }
}