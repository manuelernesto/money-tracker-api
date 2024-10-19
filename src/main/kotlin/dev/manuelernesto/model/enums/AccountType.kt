package dev.manuelernesto.model.enums

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  19/10/24 9:15 PM
 * @version 1.0
 */
enum class AccountType(private val description: String) {
    CURRENT("Current Account"),
    CHECKING("Checking Account"),
    SAVING_("Savings Account"),
    CREDIT_CARD("Credit Card"),
    INVESTMENT("Investment"),
    CASH("Cash Account"),
}