import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.11"
    id("com.github.johnrengelman.shadow") version "4.0.3"
}

group = "io.github.cottonmc"
version = "0.1.0"

repositories {
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.beust:klaxon:5.0.1")
    implementation("com.miglayout:miglayout-swing:5.2")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
