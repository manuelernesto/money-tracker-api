package dev.manuelernesto.service

import dev.manuelernesto.exceptions.AccountNotFoundException
import dev.manuelernesto.model.Transaction
import dev.manuelernesto.model.enums.TransactionType.EXPENSE
import dev.manuelernesto.model.enums.TransactionType.INCOME
import dev.manuelernesto.model.request.TransactionRequest
import dev.manuelernesto.repository.TransactionRepository
import java.math.BigDecimal
import java.util.*

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  21/11/24 10:23 PM
 * @version 1.0
 */
class TransactionManagerService(
    private val transactionRepository: TransactionRepository,
    private val accountService: AccountService,
    private val userService: UserService
) {

    suspend fun createTransaction(accountId: UUID, transactionRequest: TransactionRequest): Transaction? {
        validateAccount(accountId)

        when (transactionRequest.type) {
            EXPENSE -> handleExpenseTransaction(accountId, transactionRequest.amount)
            INCOME -> handleIncomeTransaction(accountId, transactionRequest.amount)
        }

        return transactionRepository.createTransaction(accountId, transactionRequest)
    }

    suspend fun getTransactionsByAccount(accountId: UUID) =
        transactionRepository.getTransactionsByAccountId(accountId)

    suspend fun getTransaction(transactionId: UUID): Transaction? =
        transactionRepository.getTransactionById(transactionId)
            ?: throw AccountNotFoundException("Transaction with ID $transactionId does not exist!")


    suspend fun getTransactionByUser(userId: UUID): List<Transaction>? {
        return userService.getUserById(userId)?.let { _ ->
            transactionRepository.getTransactionsByUserId(userId)
        }
    }

    private suspend fun validateAccount(accountId: UUID) = accountService.getAccount(accountId)
        ?: throw AccountNotFoundException("Account with ID $accountId does not exist!")


    private suspend fun handleExpenseTransaction(accountId: UUID, amount: BigDecimal) =
        accountService.withdrawMoneyToAccount(accountId, amount)


    private suspend fun handleIncomeTransaction(accountId: UUID, amount: BigDecimal) =
        accountService.addMoneyToAccount(accountId, amount)


}