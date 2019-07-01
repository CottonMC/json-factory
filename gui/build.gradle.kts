import org.ajoberstar.grgit.Grgit

plugins {
    java
    kotlin("jvm")
    application
    id("com.jfrog.artifactory")
    `maven-publish`
    id("org.ajoberstar.grgit") version "3.1.1"
}

base {
    archivesBaseName = "json-factory-gui"
}

repositories {
    maven(url = "http://server.bbkr.space:8081/artifactory/libs-release")
    maven(url = "http://server.bbkr.space:8081/artifactory/libs-snapshot")
}

dependencies {
    api(project(":"))
    implementation("com.miglayout:miglayout-swing:5.2")
    implementation("io.github.cottonmc.insubstantial:substance:7.3.1-SNAPSHOT")
    implementation("io.github.cottonmc.insubstantial:substance-swingx:7.3.1-SNAPSHOT") {
        exclude(group = "org.swinglabs.swingx")
    }
    implementation("org.swinglabs.swingx:swingx-all:1.6.5-1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.2.0")
    implementation("com.vladsch.flexmark:flexmark:0.42.4")
    implementation("info.picocli:picocli:3.9.6")
    implementation("org.pushing-pixels:radiance-meteor:2.0.1")
    implementation("com.google.flogger:flogger:0.4")
    runtimeOnly("com.google.flogger:flogger-system-backend:0.4")
    runtimeOnly(project(":example-plugin"))
}

application {
    mainClassName = "io.github.cottonmc.jsonfactory.gui.MainKt"
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "io.github.cottonmc.jsonfactory.gui.MainKt"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "json-factory-gui"
            from(components["java"])
            artifact(tasks["distZip"])
            artifact(tasks["sourcesJar"])
        }
    }
}

val commitHash: String by lazy {
    (ext["grgit"] as Grgit?)?.let { grgit ->
        grgit.log(mapOf("paths" to listOf("gui"), "maxCommits" to 1))[0].id.substring(0, 8)
    } ?: "unknown"
}

tasks.getByName<ProcessResources>("processResources") {
    inputs.property("version", version)
    inputs.property("commitHash", commitHash)
    filesMatching("json-factory/version-info.properties") {
        expand("version" to version, "commitHash" to commitHash)
    }
}
