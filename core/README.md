# BunnyNet Core

⬅ Back to [Main README](../README.md)

The **core module** contains the main implementation of the BunnyNet Java client.

It provides the infrastructure required to interact with the **Bunny Storage API**, including HTTP communication, request models, region configuration, and storage clients.

The module includes support for both:

* **Single-region storage**
* **Multi-region storage**

---

# Storage Clients

Two storage clients are available inside this module.

| Client               | Description                                                                    |
| -------------------- | ------------------------------------------------------------------------------ |
| `SingleBunnyStorage` | Use when your application stores files in **one storage zone and region**      |
| `MultiBunnyStorage`  | Use when your application interacts with **multiple storage zones or regions** |

---

# Single Storage Client

## Configuration

```java
SingleBunnyNetConfig config =
        new SingleBunnyNetConfig(
                "API_KEY",
                Region.LONDON_UK,
                "storage-zone"
        );
```

## Create Client

```java
SingleBunnyStorage storage =
        SingleBunnyStorage.create(config);
```

---

## Upload File

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

## Download File

```java
GetObjectResponse response =
        storage.downloadFile("images/photo.png");
```

You can access the file stream from the response:

```java
InputStream stream = response.inputStream();
```

---

## Delete File

```java
storage.deleteFile("images/photo.png");
```

---

# Multi Storage Client

Use the multi client when your application needs to interact with **multiple storage zones or regions**.

## Configuration

```java
MultiBunnyNetConfig config =
        new MultiBunnyNetConfig("API_KEY");
```

## Create Client

```java
MultiBunnyStorage storage =
        MultiBunnyStorage.create(config);
```

---

## Upload File

```java
storage.uploadFile(
        request,
        "storage-zone",
        Region.LONDON_UK
);
```

---

## Download File

```java
GetObjectResponse response =
        storage.downloadFile(
                "storage-zone",
                "images/photo.png",
                Region.LONDON_UK
        );
```

---

## Delete File

```java
storage.deleteFile(
        "storage-zone",
        "images/photo.png",
        Region.LONDON_UK
);
```

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
├── single
│   └── SingleBunnyStorage
│
└── multi
    └── MultiBunnyStorage
```

---


# HTTP Client

The library internally uses:

```

BunnyHttpClient

```

Responsibilities:

* building HTTP requests
* attaching authentication headers
* streaming uploads and downloads
* handling Bunny Storage API responses

The client is implemented using ~~**OkHttp**~~ **Java HttpClient (java.net.http)**.



# Exception Hierarchy

All library exceptions extend:

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

Example usage:

```java
try {
    storage.downloadFile("file.png");
} catch (BunnyException e) {
    // handle bunny storage error
}
```

---

# Requirements

* Java 17+

---

# License

This project is licensed under the **Apache License 2.0**.
