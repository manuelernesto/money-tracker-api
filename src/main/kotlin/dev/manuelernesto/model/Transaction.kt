package dev.manuelernesto.model

import dev.manuelernesto.model.enums.TransactionType
import dev.manuelernesto.util.serializer.BigDecimalSerializer
import dev.manuelernesto.util.serializer.LocalDateTimeSerializer
import dev.manuelernesto.util.serializer.UUIDSerializer
import kotlinx.serialization.Serializable
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
    @Serializable(with = UUIDSerializer::class) val id: UUID? = null,
    @Serializable(with = UUIDSerializer::class) var accountId: UUID,
    @Serializable(with = BigDecimalSerializer::class) val amount: BigDecimal,
    @Serializable(with = UUIDSerializer::class) var categoryId: UUID,
    val type: TransactionType,
    val note: String? = null,
    @Serializable(with = LocalDateTimeSerializer::class) val date: LocalDateTime? = null,
)