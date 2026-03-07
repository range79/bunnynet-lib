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

## Why did I even write this library?

1. because the official library is outdated and poorly designed.

2. I opened the official BunnyCDN Java library, stared at it for three seconds, and straight-up lost it laughing. That thing is so bad it feels like some 2008 intern lied his way into the job saying "yeah I know Java" and nobody ever code-reviewed a single line.

   * The main class extends Exception. Yeah, the whole library is literally an exception.
   * Every single method throws raw Exception. Like "I have no idea what could go wrong so let's just let it explode everywhere."
   * Static LinkedList<String> for crawling files. Thread-safety? Never heard of her.
   * Does Collections.reverse(Arrays.asList(...)) like it's fresh off a 2009 StackOverflow copy-paste.
   * Manual URL encoding done by hand, and you just know it's half-assed.
   * Overall energy: pure caffeine, deadline, and junior-dev panic attack in code form.

   In short, the official library is a tragic comedy that somehow made it to production.

Official BunnyCDN Java Storage Library (click if you want to suffer):
https://github.com/BunnyWay/BunnyCDN.Java.Storage

So yeah, I sat down and wrote a clean, modern, actually-thought-out library that feels like it belongs in 2026 instead of a time capsule from the dark ages.

---

## Why two config classes?

Because the official one couldn't even dream of this level.

### SingleBunnyNetConfig

* When you just need one region and one storage zone.
* Dead simple, zero overcomplication, "bro I just wanna upload files" mode.

### MultiBunnyNetConfig

* When you want to blast the same file to multiple regions at once.
* Real multi-region backup god tier stuff.
* Something the official garbage could never pull off even on its best day.

---

## Features (stuff that makes the official library look even worse)

* Clean architecture with proper interfaces and implementations.
* Typed exception hierarchy (`BunnyException`) instead of throwing random `Exception`.
* Region configuration with predefined constants.
* Streaming uploads and downloads.
* Full support for uploads, downloads, deletes.
* Blazing fast `BunnyHttpClient` running on OkHttp.
* Java 17+ modern toolchain.
* One-line Maven Central install.
* Spring Boot starters for easy integration.
* Multi-region storage support.

Unlike the official BunnyCDN Java library, this project is published on **Maven Central**.

Instead of manually downloading and adding a JAR file, you can install it with a single dependency.

---

# Modules

The project is split into multiple modules so developers can include **only what they need**.

| Module                                     | Description                                |
| ------------------------------------------ | ------------------------------------------ |
| [core](./core/README.md)                   | Core infrastructure used by all modules    |
| [single](./single/README.md)               | Client for single region storage           |
| [multi](./multi/README.md)                 | Client for multi-region storage            |
| [spring-single](./spring-single/README.md) | Spring Boot integration for single storage |
| [spring-multi](./spring-multi/README.md)   | Spring Boot integration for multi storage  |

Each module contains its own README with documentation and usage examples.

---

# Architecture

```
Spring Boot Starters
        ↓
   Single / Multi Clients
        ↓
        Core
        ↓
     HTTP Client
        ↓
   Bunny Storage API
```

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
| OkHttp based HTTP client     | ❌                              | ✅                   |
| Modular project structure    | ❌                              | ✅                   |
| Maven Central publishing     | ❌                              | ✅                   |
| Java 17+ modern toolchain    | ❌                              | ✅                   |
| Active development           | ❌                              | ✅                   |

---

# Support

If you find this project useful, you can support development here:

☕ Buy me a coffee
https://buymeacoffee.com/darkrange6s
## Contributors

<a href="https://github.com/range79/bunnynet-lib/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=range79/bunnynet-lib" />
</a>