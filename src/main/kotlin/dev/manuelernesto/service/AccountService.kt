package dev.manuelernesto.service

import dev.manuelernesto.exceptions.AccountBalanceNotEmptyException
import dev.manuelernesto.exceptions.AccountIsCloseException
import dev.manuelernesto.exceptions.AccountNotFoundException
import dev.manuelernesto.exceptions.NegativeAmountException
import dev.manuelernesto.exceptions.NoEnoughMoneyInAccountException
import dev.manuelernesto.exceptions.UserNotFoundException
import dev.manuelernesto.model.Account
import dev.manuelernesto.model.request.AccountRequest
import dev.manuelernesto.model.request.AccountUpdateRequest
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

        if (account.balance.compareTo(BigDecimal.ZERO) != 0) {
            throw AccountBalanceNotEmptyException("Cannot delete account with non-zero balance.")
        }
        accountRepository.deleteAccount(accountId)
    }

    fun updateAccount(accountId: UUID, account: AccountUpdateRequest): Account? {
        return accountRepository.updateAccountDetails(accountId, account)
            ?: throw AccountNotFoundException("Account with ID $accountId does not exist!")
    }

    suspend fun closeAccount(accountId: UUID) {
        val account = accountRepository.getAccountById(accountId)
            ?: throw AccountNotFoundException("Account with ID $accountId does not exist!")

        if (account.balance.compareTo(BigDecimal.ZERO) != 0) {
            throw AccountBalanceNotEmptyException("Account with non-zero balance can not be close.")
        }

        if (account.isClosed) {
            throw AccountIsCloseException("Account with ID $accountId is already closed!")
        }

        accountRepository.closeAccount(accountId)
    }

    suspend fun withdrawMoneyToAccount(accountId: UUID, amount: BigDecimal) {
        val account = accountRepository.getAccountById(accountId)
            ?: throw AccountNotFoundException("Account with ID $accountId does not exist!")

        if (amount < BigDecimal.ZERO) {
            throw NegativeAmountException("Amount must be more than zero!")
        }

        if (account.balance <= amount) {
            throw NoEnoughMoneyInAccountException("No enough money in account with ID $accountId!")
        }

        accountRepository.withdrawMoneyToAccount(accountId, amount)
    }

    suspend fun addMoneyToAccount(accountId: UUID, amount: BigDecimal) {
        val account = accountRepository.getAccountById(accountId)
            ?: throw AccountNotFoundException("Account with ID $accountId does not exist!")

        if (amount < BigDecimal.ZERO) {
            throw NegativeAmountException("Amount must be more than zero!")
        }

        if (account.isClosed) {
            throw AccountIsCloseException("Account with ID $accountId is closed!")
        }

        accountRepository.addMoneyToAccount(accountId, amount)
    }

}