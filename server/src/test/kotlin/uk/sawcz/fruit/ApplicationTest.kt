package uk.sawcz.fruit

import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.install
import io.ktor.server.testing.testApplication
import kotlinx.serialization.json.Json
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun canListFruit() = testApplication {
        val expectedFruitList = listOf(
            Fruit("Apple", 120),
            Fruit("Orange", 240),
            Fruit("Banana", 90),
        ).shuffled()


        application {
            install(io.ktor.server.plugins.contentnegotiation.ContentNegotiation) {
                json(Json {})
            }
            install(Koin) {
                modules(
                    module {
                        single<FruitService> { FakeFruitRepository(expectedFruitList) }
                    }
                )
            }

            fruitModule()
        }

        client = createClient {
            install(ContentNegotiation) {
                json(Json {})
            }
        }

        val response = client.get("/fruit")

        assertEquals(HttpStatusCode.OK, response.status)

        assertEquals(
            expectedFruitList,
            response.body()
        )
    }
}

class FakeFruitRepository(private val fruitList: List<Fruit>) : FruitService {
    override suspend fun fetchFruits(): List<Fruit> = fruitList
}