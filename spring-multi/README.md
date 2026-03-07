# BunnyNet Spring Boot Starter (Multi)

⬅ Back to [Main README](../README.md)

Spring Boot integration for the **multi-region storage client**.

This starter automatically configures the **MultiBunnyStorage** client and integrates it with the Spring Boot dependency injection system.

Internally uses:

* [`multi`](../multi/README.md)
* [`core`](../core/README.md)

---

# Installation

[![Version](https://img.shields.io/maven-central/v/io.github.range79/bunnynetunofficial-spring-multi)](https://search.maven.org/artifact/io.github.range79/bunnynetunofficial-spring-multi)

### Gradle (Kotlin DSL)

```kotlin id="85mpgg"
implementation("io.github.range79:bunnynetunofficial-spring-multi:VERSION")
```

### Gradle (Groovy)

```groovy id="6i8szl"
implementation "io.github.range79:bunnynetunofficial-spring-multi:VERSION"
```

### Maven

```xml id="v7yhrh"
<dependency>
  <groupId>io.github.range79</groupId>
  <artifactId>bunnynetunofficial-spring-multi</artifactId>
  <version>VERSION</version>
</dependency>
```

---

# Configuration

Configure the client using **Spring Boot configuration properties**.

```yaml id="1t6kt3"
multi:
  bunny:
    apiKey: YOUR_API_KEY
```

---

# Usage

Once configured, the `MultiBunnyStorage` client is automatically available through Spring's dependency injection.

```java id="m31h2n"
@Autowired
private MultiBunnyStorage storage;
```

Spring Boot will automatically create and configure the client.

---

# Example

```java id="g1ggdo"
storage.uploadFile(
        request,
        "storage-zone",
        Region.LONDON_UK
);
```

---

# Features

* Spring Boot auto-configuration
* configuration properties binding
* dependency injection support
* seamless integration with Spring applications

---

# Module Architecture

```text id="tkpd0v"
Spring Boot Application
        ↓
Spring Starter (Multi)
        ↓
MultiBunnyStorage Client
        ↓
BunnyNet Core
        ↓
Bunny Storage API
```

---

# When Should You Use This Module?

Use this starter if your application:

* uses **Spring Boot**
* requires **multi-region storage**
* needs **automatic configuration and dependency injection**

If you only need **single-region storage**, consider using:

* [`spring-single`](../spring-single/README.md)

---

# Requirements

* Java 17 or newer
* Spring Boot 3+

---

# License

This project is licensed under the [Apache License 2.0](../LICENSE).
