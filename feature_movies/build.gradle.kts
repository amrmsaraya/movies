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

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose
    }

    kotlinOptions {
        freeCompilerArgs = listOf(
            "-opt-in=kotlin.RequiresOptIn"
        )
        jvmTarget = AndroidConfig.jvmTarget
    }
}

dependencies {

    // Local Modules
    implementation(project(":common"))
    implementation(project(":database"))
    implementation(project(":network"))
    implementation(project(":preference"))

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

    // Hilt
    implementation(Libs.hilt_android)
    kapt(Libs.hilt_compiler_dagger_kapt)
    kapt(Libs.hilt_compiler_android_kapt)
    implementation(Libs.hilt_compose_navigation)

    // Kotlin Serialization
    implementation(Libs.kotlinx_serialization)

    // Paging
    implementation(Libs.paging)
    implementation(Libs.paging_compose)

    // Coil
    implementation(Libs.coil)

    // PlaceHolder
    implementation(Libs.placeholder)

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
}