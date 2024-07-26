plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}

rootProject.name = "hangman-app"
include("java-hangman-app")
include("kotlin-hangman-app")
