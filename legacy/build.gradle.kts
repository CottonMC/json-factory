plugins {
    `java-library`
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

group = rootProject.group
version = rootProject.version

base {
    archivesBaseName = "jf2-legacy"
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11

    withSourcesJar()
}

repositories {
    mavenCentral()

    maven {
        name = "Cotton"
        url = uri("https://server.bbkr.space/artifactory/libs-release/")
    }
}

dependencies {
    api(project(":"))
    api("io.github.cottonmc", "json-factory", "0.5.0-beta.4")
    implementation("org.organicdesign", "Paguro", "3.5.9")
    implementation("info.picocli", "picocli", "4.6.1")
}

tasks {
    jar {
        archiveClassifier.set("slim")

        manifest {
            attributes("Main-Class" to "io.github.cottonmc.jf2.legacy.Dumper")
        }
    }

    shadowJar {
        archiveClassifier.set(null as String?)
    }

    withType<JavaCompile> {
        options.release.set(11)
    }
}
