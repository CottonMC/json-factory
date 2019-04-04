plugins {
    java
    kotlin("jvm")
    application
    id("com.jfrog.artifactory")
    `maven-publish`
}

dependencies {
    implementation(project(":"))
    implementation("com.miglayout:miglayout-swing:5.2")
}

application {
    mainClassName = "io.github.cottonmc.jsonfactory.main.MainKt"
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "io.github.cottonmc.jsonfactory.main.MainKt"
    }
}
