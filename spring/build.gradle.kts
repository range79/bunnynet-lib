plugins {
    `java-library`
}

repositories {
    mavenCentral()
}

dependencies {

    api(project(":core"))
    compileOnly("org.springframework:spring-context:7.0.6")
    compileOnly("org.springframework.boot:spring-boot-autoconfigure:4.0.5")
    compileOnly("org.springframework.boot:spring-boot-configuration-processor:4.0.5")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor:4.0.5")

}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}
