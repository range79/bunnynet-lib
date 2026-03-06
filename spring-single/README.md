# BunnyNet Spring Boot Starter (Single)

⬅ Back to [Main README](../README.md)

Spring Boot integration for the **single storage client**.

Internally uses:

* [`single`](../single/README.md)
* [`core`](../core/README.md)

---

# Installation

[![Version](https://img.shields.io/maven-central/v/io.github.range79/bunnynetunofficial-single)](https://search.maven.org/artifact/io.github.range79/bunnynetunofficial-single)
### Gradle

```kotlin
implementation("io.github.range79:bunnynetunofficial-spring-single:VERSION")
```

### Maven

```xml
<dependency>
  <groupId>io.github.range79</groupId>
  <artifactId>bunnynetunofficial-spring-single</artifactId>
  <version>VERSION</version>
</dependency>
```

---

# Configuration

Add to `application.yml`.

```yaml
single:
  bunny:
    apiKey: YOUR_API_KEY
    region: LONDON_UK
    storageZone: my-storage
```

---

# Usage

```java
@Autowired
SingleBunnyStorage storage;
```

Spring Boot automatically configures the client.

---

# Features

* Spring Boot auto configuration
* configuration properties
* dependency injection
* minimal setup

---

# License

This project is licensed under the [Apache License 2.0](../LICENSE).
