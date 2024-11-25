package dev.manuelernesto.routes

import dev.manuelernesto.model.request.LoginRequest
import dev.manuelernesto.model.request.LoginResponse
import dev.manuelernesto.plugins.generateJwtToken
import dev.manuelernesto.service.UserService
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * @author  Manuel Ernesto (manuelernest0)
 * @date  25/11/24 10:01 PM
 * @version 1.0
 */

fun Route.authRoute(userService: UserService) {

    route("/api/v1/") {
        post("login") {
            val loginRequest = call.receive<LoginRequest>()
            val response = userService.login(loginRequest)
            val token = generateJwtToken(response?.userId!!, response.email!!)
            call.respond(LoginResponse(token) as Any)
        }
    }
}