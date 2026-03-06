# BunnyNet Spring Boot Starter (Multi)

⬅ Back to [Main README](../README.md)

Spring Boot integration for the **multi storage client**.

Internally uses:

* [`multi`](../multi/README.md)
* [`core`](../core/README.md)

---

# Installation
[![Version](https://img.shields.io/maven-central/v/io.github.range79/bunnynetunofficial-multi)](https://search.maven.org/artifact/io.github.range79/bunnynetunofficial-multi)

### Gradle

```kotlin
implementation("io.github.range79:bunnynetunofficial-spring-multi:VERSION")
```

### Maven

```xml
<dependency>
  <groupId>io.github.range79</groupId>
  <artifactId>bunnynetunofficial-spring-multi</artifactId>
  <version>VERSION</version>
</dependency>
```

---

# Configuration

```yaml
multi:
  bunny:
    apiKey: YOUR_API_KEY
```

---

# Usage

```java
@Autowired
MultiBunnyStorage storage;
```

Spring Boot automatically configures the client.

---

# Features

* Spring Boot auto configuration
* property binding
* dependency injection
* easy integration

---

# License

This project is licensed under the [Apache License 2.0](../LICENSE).
