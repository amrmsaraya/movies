plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
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

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }

    kotlinOptions {
        jvmTarget = AndroidConfig.jvmTarget
    }
}

dependencies {

    // Core
    implementation(Libs.core_ktx)
    implementation(Libs.app_compat)
    implementation(Libs.material)

    // Test
    testImplementation(TestLibs.junit)
    testImplementation(TestLibs.coroutines)
    testImplementation(TestLibs.truth)
    testImplementation(TestLibs.mockk)
    testImplementation(TestLibs.mockk_jvm)

    // Android Test
    androidTestImplementation(AndroidTestLibs.junit)
    androidTestImplementation(TestLibs.truth)
    androidTestImplementation(AndroidTestLibs.arch_core)
    androidTestImplementation(TestLibs.coroutines)
    androidTestImplementation(TestLibs.mockk)
    androidTestImplementation(TestLibs.mockk_jvm)

    // Hilt
    implementation(Libs.hilt_android)
    kapt(Libs.hilt_compiler_dagger_kapt)
    kapt(Libs.hilt_compiler_android_kapt)

    // DataStore
    implementation(Libs.datastore)

}