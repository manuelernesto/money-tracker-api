package dev.manuelernesto.service

import dev.manuelernesto.exceptions.NotExistsException
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
        userRepository.getUserById(userId) ?: throw NotExistsException("User with ID $userId does not exist!")
        return accountRepository.createAccount(userId, account.toAccount())
    }

    suspend fun getUserAccounts(userId: UUID): List<Account> {
        userRepository.getUserById(userId) ?: throw NotExistsException("User with ID $userId does not exist!")
        return accountRepository.getAccountsByUserId(userId)
    }

    suspend fun getAccount(accountId: UUID): Account? {
        return accountRepository.getAccountById(accountId)
            ?: throw NotExistsException("Account with ID $accountId does not exist!")
    }

    suspend fun deleteAccount(accountId: UUID) {
        val account = accountRepository.deleteAccount(accountId)

        if (account <= 0) {
            throw NotExistsException("Account with ID $accountId does not exist!")
        }
    }


}