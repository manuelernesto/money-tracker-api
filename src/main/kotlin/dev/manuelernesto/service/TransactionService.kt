package dev.manuelernesto.service

import dev.manuelernesto.exceptions.AccountNotFoundException
import dev.manuelernesto.model.Transaction
import dev.manuelernesto.model.enums.TransactionType
import dev.manuelernesto.model.request.TransactionRequest
import dev.manuelernesto.repository.AccountRepository
import dev.manuelernesto.repository.TransactionRepository
import dev.manuelernesto.util.toTransaction

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  21/11/24 10:23 PM
 * @version 1.0
 */
class TransactionService(
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository
) {

    suspend fun createTransaction(transactionRequest: TransactionRequest): Transaction? {
        accountRepository.getAccountById(transactionRequest.accountId)
            ?: throw AccountNotFoundException("Account with ID ${transactionRequest.accountId}  does not exist!")

        if (transactionRequest.type == TransactionType.EXPENSE) {
            accountRepository.withdrawMoneyToAccount(transactionRequest.accountId, transactionRequest.amount)
        }
        if (transactionRequest.type == TransactionType.INCOME) {
            accountRepository.addMoneyToAccount(transactionRequest.accountId, transactionRequest.amount)
        }

        return transactionRepository.createTransaction(transactionRequest.toTransaction())
    }
}