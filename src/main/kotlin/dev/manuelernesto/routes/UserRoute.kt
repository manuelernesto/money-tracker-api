package dev.manuelernesto.routes

import dev.manuelernesto.model.PasswordUpdate
import dev.manuelernesto.model.request.AccountRequest
import dev.manuelernesto.plugins.JWT_CONFIG_NAME
import dev.manuelernesto.service.AccountService
import dev.manuelernesto.service.UserService
import dev.manuelernesto.util.toUserResponse
import dev.manuelernesto.util.validateUUIDAndGet
import io.ktor.http.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  11/10/24 8:56 PM
 * @version 1.0
 */

fun Route.userRoute(userService: UserService, accountService: AccountService) {
    route("/api/v1/users") {
        authenticate(JWT_CONFIG_NAME) {
            get("/details") {
                val principal = call.principal<JWTPrincipal>()
                val userId = principal?.payload?.getClaim("userId")?.asString()
                call.respond(userService.getUserById(validateUUIDAndGet(userId.toString()))?.toUserResponse() as Any)
            }

            get("/{id}") {
                val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
                call.respond(userService.getUserById(validateUUIDAndGet(id))?.toUserResponse() as Any)
            }

            delete("/{id}") {
                val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
                userService.deleteUserById(validateUUIDAndGet(id))
                call.respond(HttpStatusCode.NoContent)
            }

            post("/{id}/accounts") {
                val id = call.parameters["id"] ?: return@post call.respond(HttpStatusCode.BadRequest)
                val account = call.receive<AccountRequest>()
                val createdAccount = accountService.createAccount(validateUUIDAndGet(id), account)
                call.respond(status = HttpStatusCode.Created, createdAccount as Any)
            }

            get("/{id}/accounts") {
                val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
                call.respond(status = HttpStatusCode.OK, accountService.getUserAccounts(validateUUIDAndGet(id)) as Any)
            }

        }

        put("/{id}/new-password") {
            val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest)
            val password = call.receive<PasswordUpdate>()
            userService.updatePassword(validateUUIDAndGet(id), password)
            call.respond(HttpStatusCode.OK)
        }

    }
}