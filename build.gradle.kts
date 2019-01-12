import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.11"
    application
    id("com.jfrog.artifactory") version "4.9.0"
    `maven-publish`
    id("org.jetbrains.dokka") version "0.9.17"
}

group = "io.github.cottonmc"
version = "0.2.0"

repositories {
    jcenter()
}

application {
    mainClassName = "io.github.cottonmc.jsonfactory.Mainkt"
}

dependencies {
    compile("com.beust:klaxon:5.0.1")
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


tasks.create<Zip>("sourcesJar") {
    extension = "jar"
    classifier = "sources"
    from("src/main/kotlin")
    from("src/main/resources")

}
//the artifactory block is written in the groovy dsl
apply(from="artifactory.gradle")


publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifact(tasks["distZip"])
            artifact(tasks["sourcesJar"])
        }
    }
}