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

// ---------- TAG BASED VERSION ----------
val gitVersion: groovy.lang.Closure<String> by extra
val rawVersion = gitVersion()

val semverRegex = Regex("^v?\\d+\\.\\d+\\.\\d+(?:-[0-9A-Za-z.-]+)?$")

if (!rawVersion.matches(semverRegex)) {
    throw GradleException(
        "Build must be triggered from a git tag like v1.2.3 or v1.2.3-m1. Current version: $rawVersion"
    )
}

version = rawVersion.removePrefix("v")

// ---------- SUBMODULE CONFIG ----------
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

                pom {
                    name.set(project.name)
                    description.set("Unofficial Bunny.net Java SDK")
                    url.set("https://github.com/range79/Bunny-net-Unofficial")

                    licenses {
                        license {
                            name.set("Apache License 2.0")
                            url.set("https://www.apache.org/licenses/LICENSE-2.0")
                        }
                    }

                    developers {
                        developer {
                            id.set("range79")
                            name.set("Azad Dadasov")
                            email.set("darkrange6@gmail.com")
                        }
                    }

                    scm {
                        connection.set("scm:git:git://github.com/range79/Bunny-net-Unofficial.git")
                        developerConnection.set("scm:git:ssh://github.com/range79/Bunny-net-Unofficial.git")
                        url.set("https://github.com/range79/Bunny-net-Unofficial")
                    }
                }
            }
        }

        repositories {
            maven {
                name = "central"
                url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = System.getenv("OSSRH_USERNAME")
                    password = System.getenv("OSSRH_PASSWORD")
                }
            }
        }
    }

    extensions.configure<SigningExtension> {

        val signingKey: String? = System.getenv("GPG_PRIVATE_KEY")
        val signingPassword: String? = System.getenv("GPG_PASSPHRASE")

        useInMemoryPgpKeys(signingKey, signingPassword)
        sign(extensions.getByType<PublishingExtension>().publications)
    }
}