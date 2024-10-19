package dev.manuelernesto.routes

import dev.manuelernesto.service.AccountService
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.put
import io.ktor.server.routing.route

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  19/10/24 8:42 PM
 * @version 1.0
 */

fun Route.accountRoute(accountService: AccountService) {
    route("/api/v1/accounts") {

        get("/{id}") {

        }

        put("/{id}") {

        }

        put("/{id}") {

        }
    }
}