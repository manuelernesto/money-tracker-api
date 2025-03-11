package dev.manuelernesto.model.request

import dev.manuelernesto.model.enums.TransactionType
import dev.manuelernesto.util.serializer.BigDecimalSerializer
import dev.manuelernesto.util.serializer.UUIDSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.util.*

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  21/11/24 10:37 PM
 * @version 1.0
 */
@Serializable
class TransactionRequest(
    @Serializable(with = BigDecimalSerializer::class)
    val amount: BigDecimal,
    @Serializable(with = UUIDSerializer::class)
    var categoryId: UUID,
    val type: TransactionType,
    val note: String? = null
)