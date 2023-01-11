import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"

    // SpringBoot Plugin Configuration
    java
    id("org.springframework.boot") version "2.7.3"

    id("org.jetbrains.kotlin.plugin.jpa") version "1.6.21" // Add this to integrate JPA with Kotlin
}

apply(plugin = "io.spring.dependency-management")

group = "org.imtsoft"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Spring Boot
    implementation("org.springframework.boot:spring-boot-gradle-plugin:2.7.3") // Spring Boot Gradle Plugin
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.3") // Spring Boot Web
    implementation("org.springframework.boot:spring-boot-starter-test:2.7.3") // Spring Boot Test
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:2.7.3") // Spring Boot JPA
    implementation("org.springframework.boot:spring-boot-starter-aop:2.7.3") // Spring Boot AOP

    // Jackson Serializer
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.3") // Add this if Serializer not work on kotlin!
    // Jackson Serializer dataformat library (You can install more if you need!)
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.13.3") // XML
    implementation("com.fasterxml.jackson.dataformat:jackson-dataformat-properties:2.13.3") // Properties

    // H2 Database
    implementation("com.h2database:h2:2.1.214")

    // Kotlin
    implementation("org.jetbrains.kotlin:kotlin-noarg") // Noarg For fixing JPA Problems

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}