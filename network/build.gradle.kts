plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = AndroidConfig.sourceCompatibility
        targetCompatibility = AndroidConfig.sourceCompatibility
    }

    kotlinOptions {
        freeCompilerArgs = listOf(
            "-opt-in=kotlin.RequiresOptIn"
        )
        jvmTarget = AndroidConfig.jvmTarget
    }
}

dependencies {

    // Core
    implementation(Libs.core_ktx)
    implementation(Libs.app_compat)

    // Ktor
    implementation(Libs.ktor_core)
    implementation(Libs.ktor_android)
    implementation(Libs.ktor_content_negotiation)
    implementation(Libs.ktor_serialization)
    implementation(Libs.ktor_logging)

    // Hilt
    implementation(Libs.hilt_android)
    kapt(Libs.hilt_compiler_dagger_kapt)
    kapt(Libs.hilt_compiler_android_kapt)

    // Kotlin Serialization
    implementation(Libs.kotlinx_serialization)

    // Test
    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.coroutines)
    testImplementation(TestLibs.truth)
    testImplementation(TestLibs.mockk)
    testImplementation(TestLibs.mockk_jvm)
}