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
