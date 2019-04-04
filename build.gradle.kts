import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.21"
    id("com.jfrog.artifactory") version "4.9.0"
    `maven-publish`
    id("org.jetbrains.dokka") version "0.9.17"
}

allprojects {
    group = "io.github.cottonmc"
    version = "0.4.0"

    repositories {
        jcenter()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    //the artifactory block is written in the groovy dsl
    apply(from = rootProject.file("artifactory.gradle"))

    tasks.create<Zip>("sourcesJar") {
        extension = "jar"
        classifier = "sources"
        from("src/main/kotlin")
        from("src/main/resources")
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
            artifact(tasks["sourcesJar"])
        }
    }
}

dependencies {
    api(kotlin("stdlib-jdk8"))
    api("com.google.code.gson:gson:2.8.5")
    api(kotlin("reflect"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testImplementation("io.strikt:strikt-core:0.17.1")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.2.0")
}

tasks.withType<DokkaTask> {
    outputFormat = "html"
    outputDirectory = "$buildDir/dokka"
    includes = listOf("src/docs/docs.md")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Also private.properties?
if (rootProject.file("private.gradle").exists()) {
    apply(from = "private.gradle")
}
