package uk.sawcz.fruit

interface FruitService {
    suspend fun fetchFruits(): List<Fruit>
}