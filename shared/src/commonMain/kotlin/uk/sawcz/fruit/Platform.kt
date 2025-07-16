package uk.sawcz.fruit

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform