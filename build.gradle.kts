plugins {
    kotlin("jvm") version "1.9.0"
    id("io.ktor.plugin") version "2.3.2"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.0"
    application
}

group = "com.example"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("com.example.ApplicationKt")
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("io.ktor:ktor-server-netty:2.3.2")
    implementation("io.ktor:ktor-server-core:2.3.2")
    implementation("io.ktor:ktor-server-status-pages:2.3.2")
    implementation("io.ktor:ktor-server-content-negotiation:2.3.2")
    testImplementation("io.ktor:ktor-server-tests:2.3.2")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5:1.5.21")
    implementation("ch.qos.logback:logback-classic:1.4.6")
}






