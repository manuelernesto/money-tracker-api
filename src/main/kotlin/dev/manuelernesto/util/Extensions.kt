package dev.manuelernesto.util

import dev.manuelernesto.model.Account
import dev.manuelernesto.model.Transaction
import dev.manuelernesto.model.User
import dev.manuelernesto.model.enums.TransactionType
import dev.manuelernesto.model.request.AccountRequest
import dev.manuelernesto.model.request.TransactionRequest
import dev.manuelernesto.model.response.UserResponse
import dev.manuelernesto.util.serializer.BigDecimalSerializer
import dev.manuelernesto.util.serializer.UUIDSerializer
import kotlinx.serialization.Serializable
import java.math.BigDecimal
import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  16/10/24 8:20 PM
 * @version 1.0
 */


fun User.toUserResponse() = UserResponse(this.userId, this.username, this.email)

fun AccountRequest.toAccount() = Account(
    name = this.name,
    balance = this.balance,
    type = this.type,
    currency = this.currency,
    description = this.description,
    institution = this.institution
)


fun TransactionRequest.toTransaction() = Transaction(
    accountId = this.accountId,
    amount = this.amount,
    categoryId = this.categoryId,
    type = this.type,
    note = this.note
)