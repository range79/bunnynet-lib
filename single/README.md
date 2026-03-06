# BunnyNet Single Storage Client

⬅ Back to [Main README](../README.md)

Client for interacting with **Bunny Storage using a single region and storage zone**.

This is the **most common use case** and the simplest way to integrate Bunny Storage.

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

### Configuration

```java
SingleBunnyNetConfig config =
        new SingleBunnyNetConfig(
                "API_KEY",
                Region.LONDON_UK,
                "storage-zone"
        );
```

### Create Client

```java
SingleBunnyStorage storage =
        SingleBunnyStorage.create(config);
```

---

# Upload File

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

```java
GetObjectResponse response =
        storage.downloadFile("images/photo.png");
```

---

# Delete File

```java
storage.deleteFile("images/photo.png");
```

---

# When to Use

Use this module if:

* your application uses **one storage zone**
* uploads happen in **one region**
* you do **not need multi-region replication**

---

# License

This project is licensed under the [Apache License 2.0](../LICENSE).
