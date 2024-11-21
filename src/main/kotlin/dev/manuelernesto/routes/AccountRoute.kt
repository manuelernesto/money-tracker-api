package dev.manuelernesto.routes

import dev.manuelernesto.model.Transaction
import dev.manuelernesto.model.request.AccountBalanceRequest
import dev.manuelernesto.model.request.AccountUpdateRequest
import dev.manuelernesto.model.request.TransactionRequest
import dev.manuelernesto.service.AccountService
import dev.manuelernesto.service.TransactionService
import dev.manuelernesto.util.validateUUIDAndGet
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.put
import io.ktor.server.routing.route

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  19/10/24 8:42 PM
 * @version 1.0
 */

fun Route.accountRoute(accountService: AccountService, transactionService: TransactionService) {
    route("/api/v1/accounts") {

        get("/{id}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            call.respond(accountService.getAccount(validateUUIDAndGet(id)) as Any)
        }

        put("/{id}") {
            val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest)
            val account = call.receive<AccountUpdateRequest>()
            call.respond(HttpStatusCode.OK, accountService.updateAccount(validateUUIDAndGet(id), account) as Any)
        }

        delete("/{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            accountService.deleteAccount(validateUUIDAndGet(id))
            call.respond(HttpStatusCode.NoContent)
        }

        post("/{id}/close") {
            val id = call.parameters["id"] ?: return@post call.respond(HttpStatusCode.BadRequest)
            accountService.closeAccount(validateUUIDAndGet(id))
            call.respond(HttpStatusCode.OK)
        }

//        put("/{id}/deposit") {
//            val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest)
//            val balance = call.receive<AccountBalanceRequest>()
//
//            accountService.addMoneyToAccount(validateUUIDAndGet(id), balance.balance)
//            call.respond(HttpStatusCode.OK)
//        }

//        put("/{id}/withdraw") {
//            val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest)
//            val balance = call.receive<AccountBalanceRequest>()
//
//            accountService.withdrawMoneyToAccount(validateUUIDAndGet(id), balance.balance)
//
//            call.respond(HttpStatusCode.OK)
//        }


        //TODO refactor for support module
        route("/{accountId}/transactions") {
            post {
                val accountId = call.parameters["accountId"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                val transactionRequest = call.receive<TransactionRequest>()
                transactionRequest.accountId = validateUUIDAndGet(accountId)
                call.respond(HttpStatusCode.Created, transactionService.createTransaction(transactionRequest) as Any)
            }
        }


    }
}