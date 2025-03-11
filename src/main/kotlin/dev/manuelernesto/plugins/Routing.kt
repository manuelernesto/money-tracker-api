package dev.manuelernesto.plugins

import dev.manuelernesto.exceptions.statusPageErrorConfig
import dev.manuelernesto.repository.AccountRepository
import dev.manuelernesto.repository.TransactionRepository
import dev.manuelernesto.repository.UserRepository
import dev.manuelernesto.routes.*
import dev.manuelernesto.service.AccountService
import dev.manuelernesto.service.TransactionManagerService
import dev.manuelernesto.service.UserService
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        statusPageErrorConfig()
    }
    routing {
        val userRepository = UserRepository()
        val userService = UserService(userRepository)

        val accountRepository = AccountRepository()
        val accountService = AccountService(accountRepository, userRepository)

        val transactionRepository = TransactionRepository()
        val transactionManagerService = TransactionManagerService(transactionRepository, accountService, userService)

        userRoute(userService, accountService)
        accountRoute(accountService)
        transactionRoute(transactionManagerService)
        
        authRoute()
        categoryRoute()
    }
}
