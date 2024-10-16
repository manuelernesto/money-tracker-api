package dev.manuelernesto.routes

import dev.manuelernesto.model.PasswordUpdate
import dev.manuelernesto.model.User
import dev.manuelernesto.service.UserService
import dev.manuelernesto.util.toUserResponse
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
 * @date  11/10/24 8:56 PM
 * @version 1.0
 */

fun Route.userRoute(userService: UserService) {
    route("/api/users") {

        get("/{id}") {
            val id = call.parameters["id"] ?: return@get call.respond(HttpStatusCode.BadRequest)
            call.respond(userService.getUserById(id.toLong())?.toUserResponse() as Any)
        }
        post {
            val user = call.receive<User>()
            val createdUser = userService.createUser(user)?.toUserResponse()
            call.respond(status = HttpStatusCode.Created, createdUser as Any)
        }

        put("/{id}/new-password") {
            val id = call.parameters["id"] ?: return@put call.respond(HttpStatusCode.BadRequest)
            val password = call.receive<PasswordUpdate>()
            userService.updatePassword(id.toLong(), password)
            call.respond(HttpStatusCode.OK)
        }

        delete("/{id}") {
            val id = call.parameters["id"] ?: return@delete call.respond(HttpStatusCode.BadRequest)
            userService.deleteUserById(id.toLong())
            call.respond(HttpStatusCode.NoContent)
        }
    }
}