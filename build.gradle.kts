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

    extensions.configure<JavaPluginExtension> {
        withSourcesJar()
        withJavadocJar()
    }

    publishing {

        publications {
            create<MavenPublication>("mavenJava") {
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

        // 🔥 BURASI KRİTİK
        repositories {
            maven {
                name = "central"
                url = uri("https://central.sonatype.com/api/v1/publisher")

                credentials {
                    username = System.getenv("OSSRH_USERNAME")
                    password = System.getenv("OSSRH_PASSWORD")
                }
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