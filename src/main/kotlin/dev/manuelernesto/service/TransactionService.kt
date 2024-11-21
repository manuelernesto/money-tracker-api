package dev.manuelernesto.service

import dev.manuelernesto.exceptions.AccountNotFoundException
import dev.manuelernesto.model.Transaction
import dev.manuelernesto.model.enums.TransactionType.EXPENSE
import dev.manuelernesto.model.enums.TransactionType.INCOME
import dev.manuelernesto.model.request.TransactionRequest
import dev.manuelernesto.repository.AccountRepository
import dev.manuelernesto.repository.TransactionRepository
import dev.manuelernesto.util.toTransaction
import java.math.BigDecimal
import java.util.*

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  21/11/24 10:23 PM
 * @version 1.0
 */
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository
) {

    suspend fun createTransaction(accountId: UUID, transactionRequest: TransactionRequest): Transaction? {
        validateAccount(accountId)

        when (transactionRequest.type) {
            EXPENSE -> handleExpenseTransaction(accountId, transactionRequest.amount)
            INCOME -> handleIncomeTransaction(accountId, transactionRequest.amount)
        }

        return transactionRepository.createTransaction(transactionRequest.toTransaction())
    }

    private suspend fun validateAccount(accountId: UUID) {
        accountRepository.getAccountById(accountId)
            ?: throw AccountNotFoundException("Account with ID $accountId does not exist!")
    }

    private fun handleExpenseTransaction(accountId: UUID, amount: BigDecimal) {
        accountRepository.withdrawMoneyToAccount(accountId, amount)
    }

    private fun handleIncomeTransaction(accountId: UUID, amount: BigDecimal) {
        accountRepository.addMoneyToAccount(accountId, amount)
    }
}