package dev.manuelernesto.routes

import dev.manuelernesto.service.AccountService
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
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

        }

        put("/{id}") {

        }
    }
}