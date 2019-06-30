import org.jetbrains.dokka.gradle.DokkaTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.3.30"
    id("com.jfrog.artifactory") version "4.9.0"
    `maven-publish`
    id("org.jetbrains.dokka") version "0.9.17"
}

allprojects {
    group = "io.github.cottonmc"
    version = "0.5.0-beta.3+local.gui-plugin.3-SNAPSHOT"

    repositories {
        jcenter()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    if (rootProject.file("private.gradle").exists()) {
        apply(from = rootProject.file("private.gradle"))
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
    api("io.arrow-kt:arrow-core-data:0.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testImplementation("io.strikt:strikt-core:0.17.1")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.4")
    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:2.0.4")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.2.0")
}

tasks.withType<DokkaTask> {
    outputFormat = "html"
    outputDirectory = "$buildDir/dokka"
    includes = listOf("src/docs/docs.md")
}

tasks.withType<Test> {
    useJUnitPlatform {
        includeEngines("spek2")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xuse-experimental=kotlin.Experimental"
    }
}
