# BunnyNet Spring Boot Starter (Single)

⬅ Back to [Main README](../README.md)

Spring Boot integration for the **single-region storage client**.

This starter automatically configures the **SingleBunnyStorage** client and integrates it with the Spring Boot dependency injection system.

Internally uses:

* [`single`](../single/README.md)
* [`core`](../core/README.md)

---

# Installation

[![Version](https://img.shields.io/maven-central/v/io.github.range79/bunnynetunofficial-spring-single)](https://search.maven.org/artifact/io.github.range79/bunnynetunofficial-spring-single)

### Gradle (Kotlin DSL)

```kotlin id="1v1l2o"
implementation("io.github.range79:bunnynetunofficial-spring-single:VERSION")
```

### Gradle (Groovy)

```groovy id="eowkfe"
implementation "io.github.range79:bunnynetunofficial-spring-single:VERSION"
```

### Maven

```xml id="w8t7m6"
<dependency>
  <groupId>io.github.range79</groupId>
  <artifactId>bunnynetunofficial-spring-single</artifactId>
  <version>VERSION</version>
</dependency>
```

---

# Configuration

Configure the client using **Spring Boot configuration properties**.

Add the following to your `application.yml`.

```yaml id="ctj3k4"
single:
  bunny:
    apiKey: YOUR_API_KEY
    region: LONDON_UK
    storageZone: my-storage
```

---

# Usage

Once configured, the `SingleBunnyStorage` client is automatically available via Spring dependency injection.

```java id="ydcs5v"
@Autowired
private SingleBunnyStorage storage;
```

Spring Boot will automatically create and configure the client.

---

# Example

```java id="8rhe0l"
storage.uploadFile(request);

GetObjectResponse response =
        storage.downloadFile("images/photo.png");

storage.deleteFile("images/photo.png");
```

---

# Features

* Spring Boot auto-configuration
* configuration properties binding
* dependency injection support
* minimal setup for single-region storage

---

# Module Architecture

```text id="5t8zqp"
Spring Boot Application
        ↓
Spring Starter (Single)
        ↓
SingleBunnyStorage Client
        ↓
BunnyNet Core
        ↓
Bunny Storage API
```

---

# When Should You Use This Module?

Use this starter if your application:

* uses **Spring Boot**
* stores files in **a single Bunny Storage region**
* needs **automatic configuration and dependency injection**

If your application requires **multi-region storage**, consider using:

* [`spring-multi`](../spring-multi/README.md)

---

# Requirements

* Java 17 or newer
* Spring Boot 3+

---

# License

This project is licensed under the [Apache License 2.0](../LICENSE).
