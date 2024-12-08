package dev.manuelernesto.routes

import dev.manuelernesto.config.generateJwtToken
import dev.manuelernesto.model.User
import dev.manuelernesto.model.request.LoginRequest
import dev.manuelernesto.model.request.LoginResponse
import dev.manuelernesto.plugins.JWT_CONFIG_NAME
import dev.manuelernesto.service.UserService
import dev.manuelernesto.util.toUserResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  25/11/24 10:01 PM
 * @version 1.0
 */

fun Route.authRoute(userService: UserService) {

    route("/api/v1/auth/") {

        post("register") {
            val user = call.receive<User>()
            val createdUser = userService.createUser(user)?.toUserResponse()
            call.respond(status = HttpStatusCode.Created, createdUser as Any)
        }

        post("login") {
            val loginRequest = call.receive<LoginRequest>()
            userService.login(loginRequest)?.let {
                val jwtToken = generateJwtToken(it.userId!!, it.email!!)
                call.respond(LoginResponse(jwtToken) as Any)
            }
        }

    }
}