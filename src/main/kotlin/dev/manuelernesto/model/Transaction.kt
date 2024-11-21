package dev.manuelernesto.model

import dev.manuelernesto.model.enums.TransactionType
import dev.manuelernesto.model.schemas.Transactions
import dev.manuelernesto.util.serializer.BigDecimalSerializer
import dev.manuelernesto.util.serializer.LocalDateTimeSerializer
import dev.manuelernesto.util.serializer.UUIDSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  21/11/24 9:37 PM
 * @version 1.0
 */
@Serializable
data class Transaction(
    @Serializable(with = UUIDSerializer::class) val transactionId: UUID? = null,
    @Serializable(with = UUIDSerializer::class) var accountId: UUID,
    @Serializable(with = BigDecimalSerializer::class) val amount: BigDecimal,
    @Serializable(with = UUIDSerializer::class) var categoryId: UUID,
    val type: TransactionType,
    val note: String? = null,
    @Serializable(with = LocalDateTimeSerializer::class) val date: LocalDateTime? = LocalDateTime.now(),
) {
    companion object {
        fun fromResultRow(row: ResultRow) = Transaction(
            transactionId = row[Transactions.id],
            accountId = row[Transactions.accountId],
            amount = row[Transactions.amount],
            categoryId = row[Transactions.categoryId],
            type = row[Transactions.type],
            note = row[Transactions.note],
            date = row[Transactions.date]
        )
    }
}