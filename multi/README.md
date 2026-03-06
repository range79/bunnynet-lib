# BunnyNet Multi Storage Client

⬅ Back to [Main README](../README.md)

Client for interacting with **multiple Bunny Storage regions**.

Designed for systems requiring **multi-region redundancy or backups**.

Internally uses:

* [`core`](../core/README.md)

---

# Installation

[![Version](https://img.shields.io/maven-central/v/io.github.range79/bunnynetunofficial-multi)](https://search.maven.org/artifact/io.github.range79/bunnynetunofficial-multi)

### Gradle

```kotlin
implementation("io.github.range79:bunnynetunofficial-multi:VERSION")
```

### Maven

```xml
<dependency>
  <groupId>io.github.range79</groupId>
  <artifactId>bunnynetunofficial-multi</artifactId>
  <version>VERSION</version>
</dependency>
```

---

# Quick Start

### Configuration

```java
MultiBunnyNetConfig config =
        new MultiBunnyNetConfig("API_KEY");
```

### Create Client

```java
MultiBunnyStorage storage =
        MultiBunnyStorage.create(config);
```

---

# Upload File

```java
storage.uploadFile(
        request,
        "storage-zone",
        Region.LONDON_UK
);
```

---

# Download File

```java
GetObjectResponse response =
        storage.downloadFile(
                "storage-zone",
                "images/photo.png",
                Region.LONDON_UK
        );
```

---

# Delete File

```java
storage.deleteFile(
        "storage-zone",
        "images/photo.png",
        Region.LONDON_UK
);
```

---

# When to Use

Use this module when:

* multiple regions must store the same data
* redundancy is required
* disaster recovery is needed
* global storage distribution is needed

---

# License

This project is licensed under the [Apache License 2.0](../LICENSE).
