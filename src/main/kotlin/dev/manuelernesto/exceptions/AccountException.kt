package dev.manuelernesto.exceptions

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  27/10/24 4:25 PM
 * @version 1.0
 */


class AccountBalanceNotEmptyException(message: String) : Exception(message)
class AccountNotFoundException(message: String) : Exception(message)