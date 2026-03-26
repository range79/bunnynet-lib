import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.plugins.JavaPluginExtension
import org.jreleaser.model.Active

plugins {
    id("com.palantir.git-version") version "5.0.0"
    id("org.jreleaser") version "1.23.0"
    `maven-publish`
    signing
    base

}

configurations.all {
    resolutionStrategy {
        force("org.apache.tika:tika-core:3.3.0")
        force("org.apache.tika:tika-parsers:3.3.0")
    }
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

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(components["java"])

                artifactId = "bunnynetunofficial-${project.name}"

                pom {
                    name.set("bunnynetunofficial-${project.name}")
                    description.set("Unofficial Bunny.net Java SDK (${project.name} module)")
                    url.set("https://github.com/range79/bunnynet-lib")

                    licenses {
                        license {
                            name.set("Apache License 2.0")
                            url.set("https://www.apache.org/licenses/LICENSE-2.0")
                            distribution.set("repo")
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
                        url.set("https://github.com/range79/bunnynet-lib")
                        connection.set("scm:git:https://github.com/range79/bunnynet-lib.git")
                        developerConnection.set("scm:git:ssh://git@github.com/range79/bunnynet-lib.git")
                        tag.set(rawVersion)
                    }
                }
            }
        }

        repositories {
            maven {
                name = "staging"
                url = uri(stagingDir.get().asFile)
            }
        }
    }

    signing {
        val key = System.getenv("JRELEASER_GPG_SECRET_KEY")
        val pass = System.getenv("JRELEASER_GPG_PASSPHRASE")

        if (key != null && pass != null) {
            useInMemoryPgpKeys(key, pass)
            sign(publishing.publications)
        }
    }
}

jreleaser {
    gitRootSearch.set(false)

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

                    username.set(System.getenv("JRELEASER_MAVENCENTRAL_USERNAME"))
                    password.set(System.getenv("JRELEASER_MAVENCENTRAL_PASSWORD"))

                    stagingRepositories.add(stagingDir.get().asFile.absolutePath)
                }
            }
        }
    }

    release {
        github {
            enabled.set(true)
        }
    }
}