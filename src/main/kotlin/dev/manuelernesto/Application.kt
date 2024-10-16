package dev.manuelernesto

import dev.manuelernesto.config.configureDB
import dev.manuelernesto.plugins.configureRouting
import dev.manuelernesto.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureDB()
    configureSerialization()
    configureRouting()
}
