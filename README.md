# ChildClock
[![Build Status](https://travis-ci.org/clockvoid/ChildClock.svg?branch=master)](https://travis-ci.org/clockvoid/ChildClock)

A tiny timer for managing time

[![](https://i.imgur.com/QXMxVHSm.png)](https://i.imgur.com/QXMxVHS.png)

## Description
A tiny timer for watching children created by JavaFX and Kotlin.
This program measure how long children playing game or something in PC.
This program runs 3 billion computers because of JVM.

## Installation
* You can build the project by gradle.
    ```
    git clone https://github.com/clockvoid/ChildClock.git
    cd childclock
    gradle build
    ```
    and make executable jar.
    ```
    gradle makeExecutableJar
    ```
    now, your binary exists in `built/libs` named `childclock.jar`.

* Download Jar from GitHub releases

    See [Releases](https://github.com/clockvoid/ChildClock/releases)!

## Usage
* Using Gradle
    ```
    gradle run
    ```
    
* Using Java command

    Before this, you have to make executable Jar.
    ```
    java -jar childclock.jar
    ```

* Double click executable Jar(Only on Windows)
