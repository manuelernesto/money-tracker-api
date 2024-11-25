package dev.manuelernesto.routes

import dev.manuelernesto.model.request.LoginRequest
import dev.manuelernesto.plugins.generateJwtToken
import dev.manuelernesto.service.UserService
import io.ktor.http.*
import io.ktor.server.request.receive
import io.ktor.server.routing.*

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  25/11/24 10:01 PM
 * @version 1.0
 */

fun Route.authRoute(userService: UserService) {
    route("/") {
        post("/login") {
            val loginRequest = call.receive<LoginRequest>()

            userService.login(loginRequest)
            val token = generateJwtToken()
            // Generate JWT token
            call.respond(HttpStatusCode.OK, LoginResponse(token))
        }

        post("/logout") {


            // Generate JWT token
            generateJwtToken()
            call.respond(HttpStatusCode.OK, LoginResponse(token))
        }
    }
}