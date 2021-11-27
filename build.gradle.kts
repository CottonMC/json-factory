plugins {
    `java-library`
    `maven-publish`
}

group = "io.github.cottonmc"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11

    withSourcesJar()
    withJavadocJar()

    toolchain {
        if (!JavaVersion.current().isJava11Compatible) {
            languageVersion.set(JavaLanguageVersion.of(11))
        }
    }
}

repositories {
    mavenCentral()
}

dependencies {
    api("com.samskivert", "jmustache", "1.15")
    implementation("org.organicdesign", "Paguro", "3.5.9")
    compileOnly("org.jetbrains", "annotations", "20.1.0")

    testImplementation("blue.endless", "jankson", "1.2.0")

    // Testing
    testImplementation(platform("org.junit:junit-bom:5.7.0"))
    testImplementation("org.junit.jupiter", "junit-jupiter")
    testImplementation("org.assertj", "assertj-core", "3.18.1")
}

tasks {
    jar {
        from("LICENSE")
    }

    test {
        useJUnitPlatform()
    }

    withType<JavaCompile> {
        options.release.set(11)
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}
