import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.11"
    id("com.github.johnrengelman.shadow") version "4.0.3"
    id("org.jetbrains.dokka") version "0.9.17"
}

group = "io.github.cottonmc"
version = "0.1.0"

repositories {
    jcenter()
}

dependencies {
    api(kotlin("stdlib-jdk8"))
    api("com.google.code.gson:gson:2.8.5")
    implementation("com.miglayout:miglayout-swing:5.2")
    implementation(kotlin("reflect"))
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<DokkaTask> {
    outputFormat = "html"
    outputDirectory = "$buildDir/dokka"
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "io.github.cottonmc.jsonfactory.MainKt"
    }
}
