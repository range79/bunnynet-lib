# BunnyNet Multi Storage Client

⬅ Back to [Main README](../README.md)

Client for interacting with **multiple Bunny Storage regions**.

This module is designed for applications that require **multi-region storage strategies**, such as redundancy, backups, or geographic distribution.

Internally uses:

* [`core`](../core/README.md)

---

# Installation

[![Version](https://img.shields.io/maven-central/v/io.github.range79/bunnynetunofficial-multi)](https://search.maven.org/artifact/io.github.range79/bunnynetunofficial-multi)

### Gradle (Kotlin DSL)

```kotlin
implementation("io.github.range79:bunnynetunofficial-multi:VERSION")
```

### Gradle (Groovy)

```groovy
implementation "io.github.range79:bunnynetunofficial-multi:VERSION"
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

## Configuration

Create a configuration object using your Bunny Storage API key.

```java
MultiBunnyNetConfig config =
        new MultiBunnyNetConfig("API_KEY");
```

---

## Create Client

Instantiate the storage client.

```java
MultiBunnyStorage storage =
        MultiBunnyStorage.create(config);
```

---

# Upload File

Upload a file to a specific storage zone and region.

```java
storage.uploadFile(
        request,
        "storage-zone",
        Region.LONDON_UK
);
```

---

# Download File

Download an object from a specific region.

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

Delete an object from storage.

```java
storage.deleteFile(
        "storage-zone",
        "images/photo.png",
        Region.LONDON_UK
);
```

---

# Multi-Region Usage

This module allows your application to operate across multiple Bunny Storage regions.

Typical use cases include:

* replicating files across regions
* disaster recovery setups
* geo-distributed storage architectures
* multi-region backup strategies

---

# Module Architecture

```text
Application
      ↓
MultiBunnyStorage
      ↓
 BunnyNet Core
      ↓
Bunny Storage API
```

---

# When Should You Use This Module?

Use the **multi client** when your system needs to interact with **multiple storage regions**.

If your application only uses a **single storage region**, consider using:

* [`single`](../single/README.md)

---

# Requirements

* Java 17 or newer

---

# License

This project is licensed under the [Apache License 2.0](../LICENSE).
