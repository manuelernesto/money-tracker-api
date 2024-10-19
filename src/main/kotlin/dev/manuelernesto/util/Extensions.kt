package dev.manuelernesto.util

import dev.manuelernesto.model.Account
import dev.manuelernesto.model.User
import dev.manuelernesto.model.request.AccountRequest
import dev.manuelernesto.model.response.UserResponse

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  16/10/24 8:20 PM
 * @version 1.0
 */


fun User.toUserResponse() = UserResponse(this.userId, this.username, this.email)

fun AccountRequest.toAccount() = Account(
    userId = this.userId,
    name = this.name,
    balance = this.balance,
    type = this.type,
    description = this.description,
    institution = this.institution
)