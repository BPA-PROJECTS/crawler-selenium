import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.spring") version "1.9.22"
}

group = "ru.isin-smart-soft"
version = "1.0.0"

val klog = "5.1.0"
val swagger = "2.4.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {
    // SPRING
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    // KAFKA
    implementation("org.springframework.kafka:spring-kafka")

    // SWAGGER
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:$swagger")

    // JACKSON
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    // KOTLIN
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")

    // TEST
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // LOGGING
    implementation("io.github.oshai:kotlin-logging-jvm:$klog")

    // SELENIUM
    implementation("org.seleniumhq.selenium:selenium-java:4.14.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
