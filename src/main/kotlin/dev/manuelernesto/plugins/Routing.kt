package dev.manuelernesto.plugins

import dev.manuelernesto.service.UserService
import dev.manuelernesto.exceptions.UserAlreadyExistsException
import dev.manuelernesto.exceptions.UserCredentialException
import dev.manuelernesto.exceptions.UserNotExistsException
import dev.manuelernesto.repository.UserRepository
import dev.manuelernesto.routes.userRoute
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }

        exception<UserAlreadyExistsException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, "Invalid username: ${cause.message}")
        }

        exception<UserCredentialException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, "Invalid password: ${cause.message}")
        }

        exception<UserNotExistsException> { call, cause ->
            call.respond(HttpStatusCode.BadRequest, "Invalid user: ${cause.message}")
        }
    }
    routing {
        val userRepository = UserRepository()
        val userService = UserService(userRepository)
        userRoute(userService)
    }
}
