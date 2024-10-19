package dev.manuelernesto.model.request

import dev.manuelernesto.model.enums.AccountType
import dev.manuelernesto.util.BigDecimalSerializer
import dev.manuelernesto.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  19/10/24 10:24 PM
 * @version 1.0
 */

@Serializable
data class AccountRequest(
    @Serializable(with = UUIDSerializer::class)var userId: UUID,
    val name: String,
    @Serializable(with = BigDecimalSerializer::class) val balance: BigDecimal = BigDecimal.ZERO,
    val type: AccountType = AccountType.CURRENT,
    val institution: String? = null,
    val description: String? = null
)