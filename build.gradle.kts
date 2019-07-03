import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.1.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    id("net.ltgt.apt") version "0.8"
    kotlin("jvm") version "1.3.31"
    kotlin("plugin.spring") version "1.3.31"
    kotlin("kapt") version "1.3.31"
}

group = "ch.jb"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    // Spring dependencies
    implementation("org.springframework.boot:spring-boot-starter-security") // Spring security
    implementation("org.springframework.boot:spring-boot-starter-web") // Spring web
    implementation("org.springframework.boot:spring-boot-starter-data-jpa") // Spring data JPA

    // Jackson extension for Kotlin support
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // API documentation dependency
    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")

    // JSON web token dependencies
    compileOnly("io.jsonwebtoken:jjwt-api:0.10.5")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.10.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.10.5")

    // Map struct dependencies - for DTO <-> Model conversion
    implementation("org.mapstruct:mapstruct:1.3.0.Final")
    kapt("org.mapstruct:mapstruct-processor:1.3.0.Final")

    // Kotlin dependencies
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    // PostgreSQL JPA driver
    runtimeOnly("org.postgresql:postgresql")

    // Testing dependencies
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
