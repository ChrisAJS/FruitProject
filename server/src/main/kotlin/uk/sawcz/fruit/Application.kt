package uk.sawcz.fruit

import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.*
import io.ktor.server.application.install
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin

fun main() {
    embeddedServer(Netty, port = SERVER_PORT, host = "0.0.0.0", module = {
        install(ContentNegotiation) {
            json(Json {})
        }

        install(Koin) {
            modules(module {
                singleOf<FruitService>(::RealFruitService)
            })
        }
        fruitModule()
    })
        .start(wait = true)
}

fun Application.fruitModule() {
    val fruitService by inject<FruitService>()

    routing {
        get("/fruit") {
            call.respond(fruitService.fetchFruits())
        }
    }
}


