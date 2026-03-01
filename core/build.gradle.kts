group = "com.range"

plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(platform("com.squareup.okhttp3:okhttp-bom:5.3.2"))
    implementation("com.squareup.okhttp3:okhttp")
    // Source: https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api
    testImplementation("org.junit.jupiter:junit-jupiter-api:6.0.3")
    // Source: https://mvnrepository.com/artifact/com.squareup.okhttp3/mockwebserver
    testImplementation("com.squareup.okhttp3:mockwebserver:5.3.2")
    // Source: https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine
    testImplementation("org.junit.jupiter:junit-jupiter-engine:6.0.3")
    testImplementation("org.junit.platform:junit-platform-launcher:6.0.3")
    testImplementation("org.mockito:mockito-core:5.12.0")

}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
    withSourcesJar()
    withJavadocJar()
}

tasks.test {
    useJUnitPlatform()
}
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}
