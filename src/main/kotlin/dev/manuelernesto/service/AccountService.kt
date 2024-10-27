package dev.manuelernesto.service

import dev.manuelernesto.exceptions.AccountBalanceNotEmptyException
import dev.manuelernesto.exceptions.AccountNotFoundException
import dev.manuelernesto.exceptions.UserNotFoundException
import dev.manuelernesto.model.Account
import dev.manuelernesto.model.request.AccountRequest
import dev.manuelernesto.repository.AccountRepository
import dev.manuelernesto.repository.UserRepository
import dev.manuelernesto.util.toAccount
import java.math.BigDecimal
import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  19/10/24 8:57 PM
 * @version 1.0
 */
class AccountService(private val accountRepository: AccountRepository, private val userRepository: UserRepository) {

    suspend fun createAccount(userId: UUID, account: AccountRequest): Account? {
        userRepository.getUserById(userId) ?: throw UserNotFoundException("User with ID $userId does not exist!")
        return accountRepository.createAccount(userId, account.toAccount())
    }

    suspend fun getUserAccounts(userId: UUID): List<Account> {
        userRepository.getUserById(userId) ?: throw UserNotFoundException("User with ID $userId does not exist!")
        return accountRepository.getAccountsByUserId(userId)
    }

    suspend fun getAccount(accountId: UUID): Account? {
        return accountRepository.getAccountById(accountId)
            ?: throw AccountNotFoundException("Account with ID $accountId does not exist!")
    }

    suspend fun deleteAccount(accountId: UUID) {
        //TODO validate if this account is pending transaction

        val account = accountRepository.getAccountById(accountId)
            ?: throw AccountNotFoundException("Account with ID $accountId does not exist!")

        if (account.balance.toDouble() != BigDecimal.ZERO.toDouble()) {
            throw AccountBalanceNotEmptyException("Cannot delete account with non-zero balance.")
        }
        accountRepository.deleteAccount(accountId)
    }

    suspend fun updateAccount(accountId: UUID, account: Account) {
        //TODO
    }

}