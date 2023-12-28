plugins {
    kotlin("jvm") version "1.9.22"
    id("application")
    id("com.github.johnrengelman.shadow").version("8.1.1")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("org.http4k:http4k-bom:5.12.0.0"))
    implementation("org.http4k:http4k-core")
    implementation("org.http4k:http4k-server-jetty")
    implementation("org.http4k:http4k-format-jackson")
    testImplementation("org.http4k:http4k-testing-hamkrest")

    testImplementation("org.junit.jupiter:junit-jupiter:5.10.1")
    testImplementation("com.natpryce:hamkrest:1.8.0.1")
}

tasks.test {
    useJUnitPlatform()
}

val jdkVersion = "21"

kotlin {
    jvmToolchain(JavaLanguageVersion.of(jdkVersion).asInt())
}

tasks.compileJava {
    sourceCompatibility = jdkVersion
    targetCompatibility = jdkVersion
}

tasks.compileKotlin {
    kotlinOptions.jvmTarget = jdkVersion
}

application {
    mainClass = "org.example.app.MainKt"
}
