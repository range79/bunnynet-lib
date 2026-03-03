import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.plugins.JavaPluginExtension
import org.jreleaser.model.Active

plugins {
    id("com.palantir.git-version") version "3.0.0"
    id("org.jreleaser") version "1.13.0"
    `maven-publish`
    signing
    base
}

group = "io.github.range79"

val gitVersion: groovy.lang.Closure<String> by extra
val rawVersion = gitVersion()
val semverRegex = Regex("^v?\\d+\\.\\d+\\.\\d+(?:-[0-9A-Za-z.-]+)?$")
version = if (rawVersion.matches(semverRegex)) rawVersion.removePrefix("v") else "0.0.0-SNAPSHOT"

val stagingDir = layout.buildDirectory.dir("staging-deploy")

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

    extensions.configure<org.gradle.api.publish.PublishingExtension> {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])
            }
        }

        repositories {
            maven {
                name = "staging"
                url = uri(stagingDir.get().asFile.toURI())
            }
        }
    }

    extensions.configure<org.gradle.plugins.signing.SigningExtension> {
        useInMemoryPgpKeys(
            System.getenv("JRELEASER_GPG_SECRET_KEY") ?: System.getenv("GPG_PRIVATE_KEY"),
            System.getenv("JRELEASER_GPG_PASSPHRASE") ?: System.getenv("GPG_PASSPHRASE")
        )
        sign(extensions.getByType<org.gradle.api.publish.PublishingExtension>().publications)
    }
}

jreleaser {
    signing {
        active.set(Active.ALWAYS)
        armored.set(true)

    }

    deploy {
        maven {
            mavenCentral {
                create("sonatype") {
                    active.set(Active.ALWAYS)
                    url.set("https://central.sonatype.com/api/v1/publisher")
                    username.set(System.getenv("OSSRH_USERNAME"))
                    password.set(System.getenv("OSSRH_PASSWORD"))

                    stagingRepositories.add(stagingDir.get().asFile.absolutePath)
                }
            }
        }
    }

    release {
        github {
            token.set(System.getenv("JRELEASER_GITHUB_TOKEN"))
        }
    }
}