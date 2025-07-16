plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
}

group = "uk.sawcz.fruit"
version = "1.0.0"
application {
    mainClass.set("uk.sawcz.fruit.ApplicationKt")
    
    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

dependencies {
    implementation(projects.shared)

    implementation(libs.logback)

    implementation(libs.ktor.serverCore)
    implementation(libs.ktor.serverNetty)
    implementation(libs.ktor.server.plugins.content.negotiation)
    implementation(libs.ktor.server.plugins.serialization.json)

    implementation(platform(libs.koin.bom))
    implementation(libs.koin.ktor)

    testImplementation(libs.ktor.serverTestHost)
    testImplementation(libs.kotlin.testJunit)
    testImplementation(libs.ktor.client.plugins.content.negotiation)
}