# Unofficial BunnyNet Library by Range
![Maven Central](https://img.shields.io/maven-central/v/io.github.range79/bunnynetunofficial-core)

Listen up, I just had to drop this because somebody had to fix the mess.

## Why did I even write this library?

1. Because I can do whatever the fuck I want, that's why.

2. I opened the official BunnyCDN Java library, stared at it for three seconds, and straight-up lost it laughing. That thing is so bad it feels like some 2008 intern lied his way into the job saying "yeah I know Java" and nobody ever code-reviewed a single line.

   - The main class extends Exception. Yeah, the whole library is literally an exception.
   - Every single method throws raw Exception. Like "I have no idea what could go wrong so let's just let it explode everywhere."
   - Static LinkedList<String> for crawling files. Thread-safety? Never heard of her.
   - Does Collections.reverse(Arrays.asList(...)) like it's fresh off a 2009 StackOverflow copy-paste.
   - Manual URL encoding done by hand, and you just know it's half-assed.
   - Overall energy: pure caffeine, deadline, and junior-dev panic attack in code form.

   In short, the official library is a tragic comedy that somehow made it to production.

Official BunnyCDN Java Storage Library (click if you want to suffer):  
https://github.com/BunnyWay/BunnyCDN.Java.Storage

So yeah, I sat down and wrote a clean, modern, actually-thought-out library that feels like it belongs in 2026 instead of a time capsule from the dark ages.

## Why two config classes?

Because the official one couldn't even dream of this level.

### SingleBunnyNetConfig
- When you just need one region and one storage zone.
- Dead simple, zero overcomplication, "bro I just wanna upload files" mode.

### MultiBunnyNetConfig
- When you want to blast the same file to multiple regions at once.
- Real multi-region backup god tier stuff.
- Something the official garbage could never pull off even on its best day.

## Features (stuff that makes the official library look even worse)

- Clean architecture with proper interfaces and implementations everywhere.
- Custom exceptions that actually mean something: BunnyFileUploadFailedException, BunnyInvalidCredentialsException, BunnyObjectNotFoundException, the works.
- Proper Region enum: LA, SG, DE, SYDNEY, whatever you need.
- Full support for single files, entire folders, downloads, deletes, the lot.
- Blazing fast BunnyHttpClient running on OkHttp 5.3.2.
- Java 17+ with proper toolchain setup.
- Ready for Maven publishing.
- One-liner Maven central install, no fuss.

## Installation

Gradle (Kotlin DSL):
```kotlin
repositories {
       mavenCentral()
}

dependencies {
   implementation("com.github.range79:bunnynet-lib:Version")
}
```

## Examples
Still cooking them, but they'll be up once I'm done roasting the official one.

## Version
1.0.0-beta test version
