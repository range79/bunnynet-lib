# BunnyNet Spring Boot Integration

⬅ Back to [Main README](../README.md)

Spring Boot integration for the **BunnyNet Java client**.

This module provides **auto-configuration and property binding** for Bunny Storage clients, allowing applications to easily integrate with Bunny Storage using Spring Boot.

Both **single-region** and **multi-region** storage clients are supported.

---

# Installation

Add the Spring module dependency.

### Gradle (Kotlin DSL)

```kotlin
implementation("io.github.range79:bunnynetunofficial-spring:VERSION")
```

### Gradle (Groovy)

```groovy
implementation "io.github.range79:bunnynetunofficial-spring:VERSION"
```

### Maven

```xml
<dependency>
  <groupId>io.github.range79</groupId>
  <artifactId>bunnynetunofficial-spring</artifactId>
  <version>VERSION</version>
</dependency>
```

---

# Configuration

Spring Boot automatically binds configuration properties.

Example:

```yaml
bunnynet:
  api-key: YOUR_API_KEY
  storage-zone: your-storage-zone
  region: LONDON_UK
```

---

# Single Storage Client

For applications using **one storage zone and region**, the starter automatically creates a `SingleBunnyStorage` bean.

Example usage:

```java
@Service
public class StorageService {

    private final SingleBunnyStorage storage;

    public StorageService(SingleBunnyStorage storage) {
        this.storage = storage;
    }

    public void upload(PutObjectRequest request) {
        storage.uploadFile(request);
    }
}
```

---

# Multi Storage Client

If your application interacts with **multiple storage zones or regions**, you can inject `MultiBunnyStorage`.

Example:

```java
@Service
public class MultiStorageService {

    private final MultiBunnyStorage storage;

    public MultiStorageService(MultiBunnyStorage storage) {
        this.storage = storage;
    }

    public void upload(PutObjectRequest request) {
        storage.uploadFile(
                request,
                "storage-zone",
                Region.LONDON_UK
        );
    }
}
```

---

# Available Properties

| Property                | Description            |
| ----------------------- | ---------------------- |
| `bunnynet.api-key`      | Bunny Storage API key  |
| `bunnynet.storage-zone` | Default storage zone   |
| `bunnynet.region`       | Default storage region |

---

# Auto Configuration

The Spring module provides auto-configuration classes that automatically register storage clients as Spring beans.

Internal configuration classes:

```
SingleBunnyStorageConfig
MultiBunnyStorageConfig
```

These classes create and configure the storage clients based on Spring Boot properties.

---

# Architecture

```
Spring Application
        ↓
BunnyNet Spring AutoConfig
        ↓
SingleBunnyStorage / MultiBunnyStorage
        ↓
BunnyNet Core
        ↓
Bunny Storage API
```

---

# Requirements

* Java 17+
* Spring Boot 3+

---

# License

This project is licensed under the **Apache License 2.0**.
