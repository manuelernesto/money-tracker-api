package dev.manuelernesto.routes

import dev.manuelernesto.model.request.AccountUpdateRequest
import dev.manuelernesto.service.AccountService
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.delete
import io.ktor.server.routing.get
import io.ktor.server.routing.put
import io.ktor.server.routing.route
import java.util.UUID

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  19/10/24 8:42 PM
 * @version 1.0
 */

fun Route.accountRoute(accountService: AccountService) {
    route("/api/v1/accounts") {

        get("/{id}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            call.respond(accountService.getAccount(UUID.fromString(id)) as Any)
        }

        put("/{id}") {
            val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest)
            val account = call.receive<AccountUpdateRequest>()
            call.respond(HttpStatusCode.OK, accountService.updateAccount(UUID.fromString(id), account) as Any)
        }

        delete("/{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            accountService.deleteAccount(UUID.fromString(id)!!)
            call.respond(HttpStatusCode.NoContent)
        }
    }
}