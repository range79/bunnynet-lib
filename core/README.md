# BunnyNet Core

⬅ Back to [Main README](../README.md)

The **core module** provides the internal infrastructure used by all BunnyNet storage clients.

It contains the low-level components required to communicate with the **Bunny Storage API**.

Most developers **do not need to use this module directly**, as it is automatically included by higher-level modules.

Used by:

* [`single`](../single/README.md)
* [`multi`](../multi/README.md)
* [`spring-single`](../spring-single/README.md)
* [`spring-multi`](../spring-multi/README.md)

---

# Purpose

The core module exists to provide reusable components that are shared across the entire library.

It centralizes:

* HTTP communication
* DTO models
* exception handling
* region configuration
* internal storage operations

This keeps higher level modules clean and focused.

---

# Installation

Add the dependency from **Maven Central**.

### Gradle (Kotlin DSL)

```kotlin
implementation("io.github.range79:bunnynetunofficial-core:2.1.1")
```

### Gradle (Groovy)

```groovy
implementation "io.github.range79:bunnynetunofficial-core:2.1.1"
```

### Maven

```xml
<dependency>
    <groupId>io.github.range79</groupId>
    <artifactId>bunnynetunofficial-core</artifactId>
    <version>2.1.1</version>
</dependency>
```

In most cases you **do not need to add this module manually**, since it is already included by higher level modules such as `single` and `multi`.

---

# Package Structure

```
com.range.common
│
├── http
│   └── BunnyHttpClient
│
├── dto
│   ├── PutObjectRequest
│   ├── PutObjectResponse
│   └── GetObjectResponse
│
├── enums
│   └── Region
│
├── exception
│   ├── BunnyConnectionFailedException
│   ├── BunnyFileUploadFailedException
│   ├── BunnyFileDownloadFailedException
│   ├── BunnyFileDeleteFailedException
│   ├── BunnyInvalidCredentialsException
│   └── BunnyObjectNotFoundException
│
├── upload
│   └── AbstractBunnyUploader
│
├── download
│   └── AbstractBunnyDownloader
│
└── delete
    └── AbstractBunnyDeleter
```

---

# HTTP Client

The core module contains a lightweight HTTP client used to communicate with the Bunny Storage API.

Main class:

```
BunnyHttpClient
```

Responsibilities:

* sending HTTP requests
* handling authentication headers
* managing connections
* processing responses

The HTTP client is built on top of **OkHttp**, a widely used and reliable HTTP client for Java.

OkHttp is included **internally as a dependency**, so users of the library do not need to add it manually.

---

# DTO Models

The following data models represent request and response objects used by the storage API.

### PutObjectRequest

Represents a file upload request.

Contains:

* object key
* content type
* metadata
* input stream

### PutObjectResponse

Returned after a successful upload.

### GetObjectResponse

Represents a downloaded object.

---

# Region Enum

The library provides a strongly typed `Region` enum.

Example:

```java
Region.LONDON_UK
```

Each region maps to a specific Bunny Storage endpoint.

Example mapping:

```
LONDON_UK -> uk.storage.bunnycdn.com
FRANKFURT_DE -> storage.bunnycdn.com
NEW_YORK_US -> ny.storage.bunnycdn.com
```

Custom endpoints are also supported.

---

# Exception System

Instead of throwing generic exceptions, the core module defines a clear exception hierarchy.

Examples:

* `BunnyConnectionFailedException`
* `BunnyFileUploadFailedException`
* `BunnyFileDownloadFailedException`
* `BunnyFileDeleteFailedException`
* `BunnyInvalidCredentialsException`
* `BunnyObjectNotFoundException`

This allows applications to handle specific error scenarios cleanly.

---

# Storage Operations

The core module provides abstract implementations for the main storage operations.

### AbstractBunnyUploader

Handles file uploads.

### AbstractBunnyDownloader

Handles file downloads.

### AbstractBunnyDeleter

Handles file deletion.

These classes are extended by higher level modules.

Example:

```
single module -> SingleBunnyUploaderImpl
multi module -> MultiBunnyUploaderImpl
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

Instead use one of the higher level clients:

* [`single`](../single/README.md)
* [`multi`](../multi/README.md)

They already include the core module internally.

---

# Requirements

* Java 17 or newer

---

# License

This project is licensed under the [Apache License 2.0](../LICENSE).
