package uk.sawcz.fruit

import kotlinx.serialization.Serializable

@Serializable
data class Fruit(val name: String, val price: Int)