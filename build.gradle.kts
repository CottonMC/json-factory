plugins {
    `java-library`
    `maven-publish`
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8

    withSourcesJar()
    withJavadocJar()
}

repositories {
    mavenCentral()
}

dependencies {
    api("com.samskivert", "jmustache", "1.15")
}

tasks {
    jar {
        from("LICENSE")
    }

    withType<JavaCompile> {
        if (JavaVersion.current().isJava9Compatible) {
            options.release.set(8)
        }
    }
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}
