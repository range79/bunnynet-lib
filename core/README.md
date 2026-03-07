# BunnyNet Core

⬅ Back to [Main README](../README.md)

The **core module** contains the internal infrastructure used by all BunnyNet storage clients.

It provides the low-level building blocks required to communicate with the **Bunny Storage API**.

Most developers **do not need to use this module directly**, because it is automatically included by higher-level modules.

Used by:

* [`single`](../single/README.md)
* [`multi`](../multi/README.md)
* [`spring-single`](../spring-single/README.md)
* [`spring-multi`](../spring-multi/README.md)

---

# Purpose

[![Version](https://img.shields.io/maven-central/v/io.github.range79/bunnynetunofficial-core)](https://search.maven.org/artifact/io.github.range79/bunnynetunofficial-core)

The core module centralizes the shared infrastructure used by the entire library.

It contains:

* HTTP communication layer
* request / response models
* exception hierarchy
* region configuration
* abstract storage operation logic

Keeping these components in one place allows higher-level modules to remain simple and focused.

---

# Installation

Add the dependency from **Maven Central**.

### Gradle (Kotlin DSL)

```kotlin
implementation("io.github.range79:bunnynetunofficial-core:VERSION")
```

### Gradle (Groovy)

```groovy
implementation "io.github.range79:bunnynetunofficial-core:VERSION"
```

### Maven

```xml
<dependency>
  <groupId>io.github.range79</groupId>
  <artifactId>bunnynetunofficial-core</artifactId>
  <version>VERSION</version>
</dependency>
```

In most cases you **do not need to include this module manually**, since it is already pulled in by higher-level modules such as `single` or `multi`.

---

# Package Structure

```
com.range.bunnynet.core
│
├── http
│   └── BunnyHttpClient
│
├── model
│   ├── PutObjectRequest
│   ├── PutObjectResponse
│   └── GetObjectResponse
│
├── region
│   └── Region
│
├── exception
│   ├── BunnyException
│   ├── BunnyConnectionFailedException
│   ├── BunnyFileUploadFailedException
│   ├── BunnyFileDownloadFailedException
│   ├── BunnyFileDeleteFailedException
│   ├── BunnyInvalidCredentialsException
│   └── BunnyObjectNotFoundException
│
├── AbstractBunnyUploader
├── AbstractBunnyDownloader
└── AbstractBunnyDeleter
```

---

# HTTP Client

The core module includes a lightweight HTTP client responsible for communicating with the Bunny Storage API.

Main class:

```
BunnyHttpClient
```

Responsibilities:

* building HTTP requests
* sending requests to Bunny Storage
* attaching authentication headers
* streaming uploads and downloads
* processing HTTP responses

The client is implemented using **OkHttp**, a widely used and reliable HTTP client for Java.

OkHttp is included as an internal dependency, so users of the library **do not need to add it manually**.

---

# Data Models

The core module provides request and response models used by the Bunny Storage API.

### PutObjectRequest

Represents an upload request.

Contains:

* object key
* content type
* metadata
* input stream

### PutObjectResponse

Represents the result of a successful upload.

### GetObjectResponse

Represents a downloaded object and exposes the response stream.

---

# Region Configuration

The library provides a strongly typed `Region` class with predefined constants.

Example:

```java
Region.LONDON_UK
```

Each region maps to a specific Bunny Storage endpoint.

Example mapping:

```
LONDON_UK     -> uk.storage.bunnycdn.com
FRANKFURT_DE  -> storage.bunnycdn.com
NEW_YORK_US   -> ny.storage.bunnycdn.com
```

Custom endpoints are also supported:

```java
Region custom = new Region("my.storage.endpoint");
```

---

# Exception System

Instead of throwing generic exceptions, the core module defines a clear exception hierarchy.

Base type:

```
BunnyException
```

Hierarchy:

```
BunnyException
 ├─ BunnyConnectionFailedException
 ├─ BunnyFileUploadFailedException
 ├─ BunnyFileDownloadFailedException
 ├─ BunnyFileDeleteFailedException
 ├─ BunnyInvalidCredentialsException
 └─ BunnyObjectNotFoundException
```

This allows applications to catch either specific errors or all Bunny-related errors.

Example:

```java
try {
    storage.downloadFile(...);
} catch (BunnyException e) {
    // handle bunny storage error
}
```

---

# Storage Operation Abstractions

The core module provides abstract implementations for storage operations.

### AbstractBunnyUploader

Handles upload logic.

### AbstractBunnyDownloader

Handles download logic.

### AbstractBunnyDeleter

Handles deletion logic.

These classes are extended by higher-level modules.

Example:

```
single module -> SingleBunnyUploaderImpl
multi module  -> MultiBunnyUploaderImpl
```

---

# Internal Architecture

```
Uploader / Downloader / Deleter
            ↓
      BunnyHttpClient
            ↓
      Bunny Storage API
```

---

# When Should You Use This Module?

Normally you **should not depend on this module directly**.

Instead use one of the higher-level clients:

* [`single`](../single/README.md)
* [`multi`](../multi/README.md)

These modules already include the core module internally.

---

# Requirements

* Java 17 or newer

---

# License

This project is licensed under the [Apache License 2.0](../LICENSE).
