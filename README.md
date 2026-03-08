# Unofficial BunnyNet Library by Range

[![Version](https://img.shields.io/maven-central/v/io.github.range79/bunnynetunofficial-core)](https://search.maven.org/artifact/io.github.range79/bunnynetunofficial-core)
![Java](https://img.shields.io/badge/java-17+-blue)
![License](https://img.shields.io/github/license/range79/bunnynet-lib)
![Build](https://img.shields.io/github/actions/workflow/status/range79/bunnynet-lib/build.yml)
![GitHub stars](https://img.shields.io/github/stars/range79/bunnynet-lib)
![GitHub issues](https://img.shields.io/github/issues/range79/bunnynet-lib)
![GitHub last commit](https://img.shields.io/github/last-commit/range79/bunnynet-lib)
[![Buy Me a Coffee](https://img.shields.io/badge/Buy%20Me%20a%20Coffee-support-yellow?logo=buy-me-a-coffee)](https://buymeacoffee.com/darkrange6s)

Listen up, I just had to drop this because somebody had to fix the mess.

---

# Why did I even write this library?

1. Because the official library is outdated and poorly designed.

2. I opened the official BunnyCDN Java library, glanced at it for a few seconds, and couldn't believe what I was seeing. It felt like a very early-intern project that never got reviewed.

   * The main class extends `Exception`. Yes, the whole library is basically an exception.
   * Every single method throws raw `Exception`.
   * Uses `Static LinkedList<String>` for crawling files. Thread-safety? Not considered.
   * Calls `Collections.reverse(Arrays.asList(...))` as if copied from an old forum post.
   * Manual URL encoding done by hand.
   * Overall style: rushed, unstructured, and prone to errors.

In short, the official library is poorly structured and not suitable for modern development.

Official BunnyCDN Java Storage Library (for reference):
https://github.com/BunnyWay/BunnyCDN.Java.Storage

I wrote this library to provide a clean, modern, well-thought-out implementation that fits 2026 standards instead of outdated practices.

---

# Features

* Clean architecture with proper interfaces and implementations.
* Typed exception hierarchy (BunnyException) instead of throwing random Exception.
* Region configuration with predefined constants.
* Streaming uploads and downloads.
* Upload, download and delete operations.
* Blazing fast `BunnyHttpClient` powered by ~~**OkHttp**~~ **Java HttpClient (java.net.http)**.
* Java **17+ modern toolchain**.
* One-line install via **Maven Central**.
* **Single-region storage client**
* **Multi-region storage client**
* **Spring Boot auto configuration**

Unlike the official BunnyCDN Java library, this project is published on **Maven Central**.

Instead of manually downloading a random JAR from GitHub and praying it works, you can install it with a single dependency.

---

# Why I removed OkHttp

Earlier versions of this library used **OkHttp** as the HTTP client.

However, one of the goals of this project is to keep external dependencies to a minimum and avoid unnecessary dependency chains.

Since Java already provides a built-in HTTP client (`java.net.http.HttpClient`) starting from Java 11, adding OkHttp would only introduce an extra dependency for functionality that already exists in the JDK.
# Modules

The project is organized into a small set of focused modules.

| Module                       | Description                                    |
| ---------------------------- | ---------------------------------------------- |
| [core](./core/README.md)     | Main BunnyNet client implementation            |
| [spring](./spring/README.md) | Spring Boot auto-configuration and integration |

The **core module** contains the full BunnyNet client implementation including:

* Single storage client
* Multi storage client
* HTTP client
* DTO models
* Region configuration
* Exception hierarchy

The **spring module** provides Spring Boot auto-configuration for the client.

---

# Architecture

Spring Boot Integration
↓
Storage Clients (Single / Multi)
↓
Core
↓
BunnyHttpClient
↓
Bunny Storage API



---

# Comparison with official SDK

| Feature                      | Official BunnyCDN Java Library | BunnyNet Unofficial |
| ---------------------------- | ------------------------------ | ------------------- |
| Modern Java API              | ❌                              | ✅                   |
| Clean architecture           | ❌                              | ✅                   |
| DTO request/response model   | ❌                              | ✅                   |
| Typed exceptions             | ❌                              | ✅                   |
| Region configuration         | ❌                              | ✅                   |
| Single storage client        | ⚠️ Basic                       | ✅                   |
| Multi-region storage support | ❌                              | ✅                   |
| Spring Boot integration      | ❌                              | ✅                   |
| Modular project structure    | ❌                              | ✅                   |
| Maven Central publishing     | ❌                              | ✅                   |
| Java 17+ toolchain           | ❌                              | ✅                   |
| Active development           | ❌                              | ✅                   |

---

# Support

If you find this project useful, you can support development here:

☕ Buy me a coffee
https://buymeacoffee.com/darkrange6s

---

# Contributors

<a href="https://github.com/range79/bunnynet-lib/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=range79/bunnynet-lib" />
</a> 

