plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    kotlin("plugin.serialization") version "1.9.0"
//    id("com.google.dagger.hilt.android")
    id("kotlinx-serialization")
//    id("com.google.devtools.ksp")
}

android {
    namespace = "dev.xget.havasreddit"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.xget.havasreddit"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
//        dataBinding = true
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //navigation components
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //for lifecycle viewmodel providers and livedata
    implementation (libs.androidx.lifecycle.extensions)

    //for api service call
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    //for logging service call
    implementation (libs.logging.interceptor)

    //for json parsing
    implementation (libs.gson)

    //for  recyclerview
    implementation (libs.androidx.recyclerview)



}