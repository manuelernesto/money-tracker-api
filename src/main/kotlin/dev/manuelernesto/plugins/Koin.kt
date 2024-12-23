package dev.manuelernesto.plugins

import dev.manuelernesto.repository.CategoryRepository
import dev.manuelernesto.repository.UserRepository
import dev.manuelernesto.service.CategoryService
import dev.manuelernesto.service.UserService
import io.ktor.server.application.*
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(appModule)
    }
}

val appModule = module {
    singleOf(::CategoryRepository)
    singleOf(::CategoryService)
    singleOf(::UserRepository)
    singleOf(::UserService)
}

