package dev.manuelernesto

import dev.manuelernesto.plugins.configureRouting
import dev.manuelernesto.plugins.configureSerialization
import dev.manuelernesto.repository.DBFactory
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    DBFactory.init()
    configureSerialization()
    configureRouting()
}
