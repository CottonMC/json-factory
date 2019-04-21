plugins {
    java
    kotlin("jvm")
    id("com.jfrog.artifactory")
    `maven-publish`
}

base {
    archivesBaseName = "json-factory-frontend"
}

dependencies {
    api(project(":"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.0")
    implementation("com.vladsch.flexmark:flexmark:0.42.4")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            artifactId = "json-factory-frontend"
            from(components["java"])
            artifact(tasks["sourcesJar"])
        }
    }
}
