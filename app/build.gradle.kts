plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)

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
        compose = true
//        dataBinding = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
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
    testOptions.unitTests{
        isIncludeAndroidResources = true

    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.junit.ktx)
    implementation (libs.androidx.fragment.testing)
    implementation (libs.androidx.core)

    testImplementation(libs.junit)
    testImplementation("junit:junit:4.12")
    testImplementation (libs.androidx.core.testing)
    testImplementation(libs.kotlinx.coroutines.test)


    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.androidx.espresso.core)

    //compose material3 and bom
    implementation(libs.androidx.material3)
    platform (libs.androidx.compose.bom)
    implementation (libs.androidx.ui)
    implementation (libs.androidx.ui.tooling.preview)
    implementation (libs.androidx.activity.compose)
    implementation (libs.androidx.lifecycle.runtime.compose)

    //navigation components
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //for lifecycle viewmodel providers and livedata
    implementation (libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    //for api service call
    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    //for logging service call
    implementation (libs.logging.interceptor)

    //for json parsing
    implementation (libs.gson)

    //for  recyclerview
    implementation (libs.androidx.recyclerview)


    //Very optimized image loading library for android based in kotlin
    implementation(libs.coil)
    implementation(libs.transformations)

    //and for composables
    implementation(libs.coil.compose)


}