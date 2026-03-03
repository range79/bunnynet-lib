import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.plugins.signing.SigningExtension
import org.gradle.api.plugins.JavaPluginExtension

plugins {
    id("com.palantir.git-version") version "3.0.0"
    `maven-publish`
    signing
}

group = "io.github.range79"

val gitVersion: groovy.lang.Closure<String> by extra
val rawVersion = gitVersion()

val semverRegex = Regex("^v?\\d+\\.\\d+\\.\\d+(?:-[0-9A-Za-z.-]+)?$")

if (!rawVersion.matches(semverRegex)) {
    throw GradleException(
        "Build must be triggered from a git tag like v1.2.3 or v1.2.3-m1. Current version: $rawVersion"
    )
}

version = rawVersion.removePrefix("v")

subprojects {

    apply(plugin = "java-library")
    apply(plugin = "maven-publish")
    apply(plugin = "signing")

    group = rootProject.group
    version = rootProject.version

    extensions.configure<JavaPluginExtension> {
        withSourcesJar()
        withJavadocJar()
    }

    extensions.configure<PublishingExtension> {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
            }
        }
    }

    extensions.configure<SigningExtension> {

        val signingKey: String? by project
        val signingPassword: String? by project

        useInMemoryPgpKeys(signingKey, signingPassword)

        sign(extensions.getByType<PublishingExtension>().publications)
    }
}