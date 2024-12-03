package dev.manuelernesto.routes

import dev.manuelernesto.model.request.TransactionRequest
import dev.manuelernesto.plugins.JWT_CONFIG_NAME
import dev.manuelernesto.service.TransactionManagerService
import dev.manuelernesto.util.validateUUIDAndGet
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.request.*
import io.ktor.server.response.respond
import io.ktor.server.routing.*

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  22/11/24 9:47 PM
 * @version 1.0
 */

fun Route.transactionRoute(transactionManagerService: TransactionManagerService) {

    route("/api/v1/transactions") {
        authenticate(JWT_CONFIG_NAME) {
            get("/{id}") {
                val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
                call.respond(transactionManagerService.getTransaction(validateUUIDAndGet(id)) as Any)
            }
        }
    }

    route("/api/v1/accounts/{accountId}/transactions") {
        authenticate(JWT_CONFIG_NAME) {

            post {
                val accountId = call.parameters["accountId"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                val transactionRequest = call.receive<TransactionRequest>()
                call.respond(
                    HttpStatusCode.Created,
                    transactionManagerService.createTransaction(
                        validateUUIDAndGet(accountId),
                        transactionRequest
                    ) as Any
                )
            }

            get {
                val accountId = call.parameters["accountId"] ?: return@get call.respond(HttpStatusCode.BadRequest)
                call.respond(transactionManagerService.getTransactionsByAccount(validateUUIDAndGet(accountId)) as Any)
            }

        }
    }


}