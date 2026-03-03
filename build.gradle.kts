import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.plugins.JavaPluginExtension

plugins {
    id("com.palantir.git-version") version "3.0.0"
    `maven-publish`
    signing
    base
}

group = "io.github.range79"

// -------- VERSION (TAG BASED) --------
val gitVersion: groovy.lang.Closure<String> by extra
val rawVersion = gitVersion()

val semverRegex = Regex("^v?\\d+\\.\\d+\\.\\d+(?:-[0-9A-Za-z.-]+)?$")
version = if (rawVersion.matches(semverRegex)) {
    rawVersion.removePrefix("v")
} else {
    "0.0.0-SNAPSHOT"
}

// -------- SUBPROJECT CONFIG --------
subprojects {

    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "signing")

    group = rootProject.group
    version = rootProject.version

    // 🔥 Java extension doğru şekilde configure
    extensions.configure<JavaPluginExtension> {
        withSourcesJar()
        withJavadocJar()
    }

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
            }
        }
    }

    signing {
        useInMemoryPgpKeys(
            System.getenv("GPG_PRIVATE_KEY"),
            System.getenv("GPG_PASSPHRASE")
        )
        sign(publishing.publications)
    }
}