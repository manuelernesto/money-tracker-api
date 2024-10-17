package dev.manuelernesto.plugins

import dev.manuelernesto.exceptions.statusPageErrorConfig
import dev.manuelernesto.repository.CategoryRepository
import dev.manuelernesto.repository.UserRepository
import dev.manuelernesto.routes.categoryRoute
import dev.manuelernesto.routes.userRoute
import dev.manuelernesto.service.CategoryService
import dev.manuelernesto.service.UserService
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    install(StatusPages) {
        statusPageErrorConfig()
    }
    routing {
        val userRepository = UserRepository()
        val userService = UserService(userRepository)

        val categoryRepository = CategoryRepository()
        val categoryService = CategoryService(categoryRepository)

        userRoute(userService)
        categoryRoute(categoryService)
    }
}
