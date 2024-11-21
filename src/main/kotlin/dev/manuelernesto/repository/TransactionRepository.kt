package dev.manuelernesto.repository

import dev.manuelernesto.config.dbQuery
import dev.manuelernesto.model.Transaction
import dev.manuelernesto.model.schemas.Transactions
import org.jetbrains.exposed.sql.insert
import java.util.*

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  21/11/24 10:02 PM
 * @version 1.0
 */

class TransactionRepository {

    suspend fun createTransaction(transaction: Transaction): Transaction? = dbQuery {
        val inserted = Transactions.insert {
            it[id] = UUID.randomUUID()
            it[accountId] = transaction.accountId
            it[amount] = transaction.amount
            it[categoryId] = transaction.categoryId
            it[type] = transaction.type
            it[note] = transaction.note
            it[date] = transaction.date!!
        }

        inserted.resultedValues?.singleOrNull()?.let { Transaction.fromResultRow(it) }
    }
}