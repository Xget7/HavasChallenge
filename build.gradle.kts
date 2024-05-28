// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
//        classpath(libs.gradle)
//        classpath(libs.kotlin.serialization)
//        classpath(kotlin("gradle-plugin", version = "1.9.0"))
//        classpath(libs.hilt.android.gradle.plugin)
    }

}



plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.0"
    id("com.google.devtools.ksp") version "1.9.0-1.0.12" apply false

}

