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
    archivesBaseName = "json-factory-cli"
}

repositories {
    maven(url = "http://server.bbkr.space:8081/artifactory/libs-release")
    maven(url = "http://server.bbkr.space:8081/artifactory/libs-snapshot")
}

dependencies {
    implementation(project(":"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.0")
    implementation("info.picocli:picocli:3.9.6")

    val arrow = "0.9.0"
    fun arrow(module: String) = "io.arrow-kt:arrow-$module:$arrow"

    implementation(arrow("core-data"))
//    implementation(arrow("core-extensions"))
//    implementation(arrow("typeclasses"))

    runtimeOnly(project(":example-plugin"))
}

val mainClass = "io.github.cottonmc.jsonfactory.cli.MainKt"

application {
    mainClassName = mainClass
    applicationName = "json-factory-cli"
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = mainClass
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "json-factory-cli"
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
