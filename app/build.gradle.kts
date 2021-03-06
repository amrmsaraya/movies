plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.devtools.ksp") version "1.6.20-1.0.5"
    id("kotlinx-serialization")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = AndroidConfig.compileSdk

    defaultConfig {
        applicationId = "com.github.amrmsaraya.movies"
        minSdk = AndroidConfig.minSdk
        targetSdk = AndroidConfig.targetSdk
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "API_KEY", "\"${Constants.API_KEY}\"")
        buildConfigField("String", "BASE_URL", "\"${Constants.BASE_URL}\"")

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
        targetCompatibility = AndroidConfig.targetCompatibility
    }

    kotlinOptions {
        freeCompilerArgs = listOf(
                "-opt-in=kotlin.RequiresOptIn"
        )
        jvmTarget = AndroidConfig.jvmTarget
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    // Local Modules
    implementation(project(":common"))
    implementation(project(":feature_movies"))

    // Core
    implementation(Libs.core_ktx)
    implementation(Libs.app_compat)
    implementation(Libs.material)

    // Compose
    implementation(Libs.compose_ui)
    implementation(Libs.compose_material3)
    implementation(Libs.compose_ui_tooling)
    implementation(Libs.compose_icons)
    implementation(Libs.compose_icons_extended)
    implementation(Libs.activity_compose)
    implementation(Libs.navigation)

    // Test
    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.coroutines)
    testImplementation(TestLibs.truth)
    testImplementation(TestLibs.mockk)
    testImplementation(TestLibs.mockk_jvm)

    // Android Test
    androidTestImplementation(AndroidTestLibs.junit)
    androidTestImplementation(TestLibs.truth)
    androidTestImplementation(AndroidTestLibs.junit_compose)
    debugImplementation(AndroidTestLibs.debug_compose_ui)
    androidTestImplementation(AndroidTestLibs.arch_core)
    androidTestImplementation(TestLibs.coroutines)

    // Splash Screen
    implementation(Libs.splashscreen)

    // Lifecycle
    implementation(Libs.lifecycle_runtime)
    implementation(Libs.viewModel)
    implementation(Libs.viewModel_compose)

    // SystemUiController
    implementation(Libs.systemuicontroller)

    // Coroutine
    implementation(Libs.coroutines_core)
    implementation(Libs.coroutines_android)

    // Kotlin Serialization
    implementation(Libs.kotlinx_serialization)

    // Ktor
    implementation(Libs.ktor_core)
    implementation(Libs.ktor_android)
    implementation(Libs.ktor_content_negotiation)
    implementation(Libs.ktor_serialization)
    implementation(Libs.ktor_logging)

    // Hilt
    implementation(Libs.hilt_android)
    kapt(Libs.hilt_compiler_dagger_kapt)
    implementation(Libs.hilt_compose_navigation)
    kapt(Libs.hilt_compiler_android_kapt)
}