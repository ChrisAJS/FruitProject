package uk.sawcz.fruit

import kotlinx.coroutines.delay

class RealFruitService : FruitService {
    override suspend fun fetchFruits(): List<Fruit> {
        delay(3000)
        return listOf(
            Fruit("Apple", 120),
            Fruit("Orange", 240),
            Fruit("Banana", 90),
        )
    }
}