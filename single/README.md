# BunnyNet Single Storage Client

⬅ Back to [Main README](../README.md)

Client for interacting with **Bunny Storage using a single region and storage zone**.

This module provides the **simplest and most common integration** for applications that store files in one Bunny Storage region.

Internally uses:

* [`core`](../core/README.md)

---

# Installation

[![Version](https://img.shields.io/maven-central/v/io.github.range79/bunnynetunofficial-single)](https://search.maven.org/artifact/io.github.range79/bunnynetunofficial-single)

### Gradle (Kotlin DSL)

```kotlin
implementation("io.github.range79:bunnynetunofficial-single:VERSION")
```

### Gradle (Groovy)

```groovy
implementation "io.github.range79:bunnynetunofficial-single:VERSION"
```

### Maven

```xml
<dependency>
    <groupId>io.github.range79</groupId>
    <artifactId>bunnynetunofficial-single</artifactId>
    <version>VERSION</version>
</dependency>
```

---

# Quick Start

## Configuration

Create a configuration object with your API key, region, and storage zone.

```java
SingleBunnyNetConfig config =
        new SingleBunnyNetConfig(
                "API_KEY",
                Region.LONDON_UK,
                "storage-zone"
        );
```

---

## Create Client

Instantiate the storage client.

```java
SingleBunnyStorage storage =
        SingleBunnyStorage.create(config);
```

---

# Upload File

Create a request object and upload a file.

```java
PutObjectRequest request =
        new PutObjectRequest(
                "images/photo.png",
                contentType,
                metadata,
                inputStream
        );

storage.uploadFile(request);
```

---

# Download File

Download an object from storage.

```java
GetObjectResponse response =
        storage.downloadFile("images/photo.png");
```

---

# Delete File

Delete an object from storage.

```java
storage.deleteFile("images/photo.png");
```

---

# Module Architecture

```text
Application
      ↓
SingleBunnyStorage
      ↓
 BunnyNet Core
      ↓
Bunny Storage API
```

---

# When Should You Use This Module?

Use the **single client** if:

* your application stores files in **one storage zone**
* uploads occur in **a single region**
* you **do not require multi-region replication**

If you need multi-region storage support, consider using:

* [`multi`](../multi/README.md)

---

# Requirements

* Java 17 or newer

---

# License

This project is licensed under the [Apache License 2.0](../LICENSE).
