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

The Spring starter exposes two configuration groups:

* `bunnynet.single`
* `bunnynet.multi`

Each client can be enabled independently.

---

# Single Storage Configuration

```yaml
bunnynet:
  single:
    enabled: true
    api-key: YOUR_API_KEY
    storage-zone: your-storage-zone
    region: LONDON_UK
```

When enabled, the starter automatically registers a `SingleBunnyStorage` bean.

---

# Multi Storage Configuration

```yaml
bunnynet:
  multi:
    enabled: true
    api-key: YOUR_API_KEY
```

When enabled, the starter automatically registers a `MultiBunnyStorage` bean.

---

# Using Single Storage Client

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

# Using Multi Storage Client

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

## Single Client

| Property                       | Description                       |
| ------------------------------ | --------------------------------- |
| `bunnynet.single.enabled`      | Enables the single storage client |
| `bunnynet.single.api-key`      | Bunny Storage API key             |
| `bunnynet.single.storage-zone` | Default storage zone              |
| `bunnynet.single.region`       | Default storage region            |

---

## Multi Client

| Property                 | Description                      |
| ------------------------ | -------------------------------- |
| `bunnynet.multi.enabled` | Enables the multi storage client |
| `bunnynet.multi.api-key` | Bunny Storage API key            |

---

# Auto Configuration

The Spring module automatically registers storage clients using Spring Boot auto-configuration.

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
BunnyNet Spring AutoConfiguration
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
