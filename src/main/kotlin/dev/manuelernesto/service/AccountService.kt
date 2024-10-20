package dev.manuelernesto.service

import dev.manuelernesto.exceptions.UserNotExistsException
import dev.manuelernesto.model.Account
import dev.manuelernesto.model.request.AccountRequest
import dev.manuelernesto.repository.AccountRepository
import dev.manuelernesto.repository.UserRepository
import dev.manuelernesto.util.toAccount
import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  19/10/24 8:57 PM
 * @version 1.0
 */
class AccountService(private val accountRepository: AccountRepository, private val userRepository: UserRepository) {

    suspend fun createAccount(userId: UUID, account: AccountRequest): Account? {
        userRepository.getUserById(userId) ?: throw UserNotExistsException("User with ID $userId does not exist!")
        return accountRepository.create(userId, account.toAccount())
    }


}