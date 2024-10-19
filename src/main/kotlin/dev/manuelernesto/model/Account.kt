package dev.manuelernesto.model

import dev.manuelernesto.model.enums.AccountType
import dev.manuelernesto.model.schemas.Accounts
import dev.manuelernesto.util.UUIDSerializer
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  19/10/24 9:05 PM
 * @version 1.0
 */
data class Account(
    @Serializable(with = UUIDSerializer::class) val accountId: UUID? = null,
    val userId: User,
    val name: String,
    val balance: BigDecimal = BigDecimal.ZERO,
    val type: AccountType = AccountType.CURRENT,
    val institution: String? = null,
    val description: String? = null,
    val isClosed: Boolean? = false,
    val createdAt: Instant? = null,
    val updatedAt: Instant? = null,
) {

    companion object {
        fun fromResultRow(row: ResultRow) = Account(
            accountId = row[Accounts.id],
            userId = row[Accounts.id]
        )
    }
}
