plugins {
    id("com.palantir.git-version") version "3.0.0"
    id("org.jreleaser") version "1.13.0"
    `maven-publish`
}

group = "io.github.range79"

// -------- TAG BASED VERSION --------
val gitVersion: groovy.lang.Closure<String> by extra
val rawVersion = gitVersion()

val semverRegex = Regex("^v?\\d+\\.\\d+\\.\\d+(?:-[0-9A-Za-z.-]+)?$")
version = if (rawVersion.matches(semverRegex)) {
    rawVersion.removePrefix("v")
} else {
    "0.0.0-SNAPSHOT"
}

// -------- SUBPROJECTS --------
subprojects {
    apply(plugin = "java-library")
    apply(plugin = "maven-publish")

    group = rootProject.group
    version = rootProject.version

    extensions.configure<org.gradle.api.plugins.JavaPluginExtension> {
        withSourcesJar()
        withJavadocJar()
    }

    extensions.configure<org.gradle.api.publish.PublishingExtension> {
        publications {
            create<org.gradle.api.publish.maven.MavenPublication>("mavenJava") {
                from(components["java"])

                pom {
                    name.set(project.name)
                    description.set("Unofficial Bunny.net Java SDK")
                    url.set("https://github.com/range79/bunnynet-lib")

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
                        connection.set("scm:git:git://github.com/range79/bunnynet-lib.git")
                        developerConnection.set("scm:git:ssh://github.com/range79/bunnynet-lib.git")
                        url.set("https://github.com/range79/bunnynet-lib")
                    }
                }
            }
        }
    }
}

// -------- JRELEASER --------
jreleaser {

    signing {
        active.set(org.jreleaser.model.Active.ALWAYS)
        armored.set(true)
    }

    deploy {
        maven {
            mavenCentral {
                create("sonatype") {
                    active.set(org.jreleaser.model.Active.ALWAYS)
                    url.set("https://central.sonatype.com/api/v1/publisher")

                    username.set(System.getenv("OSSRH_USERNAME"))
                    password.set(System.getenv("OSSRH_PASSWORD"))
                }
            }
        }
    }
}