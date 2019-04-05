plugins {
    java
    kotlin("jvm")
    application
    id("com.jfrog.artifactory")
    `maven-publish`
}

base {
    archivesBaseName = "json-factory-gui"
}

// This should be false for releasing, true for developing
val local = true

repositories {
    maven(url = "http://server.bbkr.space:8081/artifactory/libs-release")
    maven(url = "http://server.bbkr.space:8081/artifactory/libs-snapshot")
}

dependencies {
    if (local) {
        implementation(project(":"))
    } else {
        implementation("io.github.cottonmc:json-factory:$version")
    }
    implementation("com.miglayout:miglayout-swing:5.2")
    implementation("com.github.insubstantial:substance:7.3")
    implementation("com.github.insubstantial:substance-swingx:7.3") {
        exclude("org.swinglabs.swingx", "swingx-action")
        exclude("org.swinglabs.swingx", "swingx-autocomplete")
        exclude("org.swinglabs.swingx", "swingx-common")
        exclude("org.swinglabs.swingx", "swingx-core")
        exclude("org.swinglabs.swingx", "swingx-painters")
        exclude("org.swinglabs.swingx", "swingx-plaf")
    }
    implementation("org.swinglabs.swingx:swingx-all:1.6.5-1")
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
