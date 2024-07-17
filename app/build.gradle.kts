

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.jetbrains.kotlin.kapt)

    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)

}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 29
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


        vectorDrawables {
            useSupportLibrary = true
        }


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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}
dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.room.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation(libs.media3.common)
    implementation(libs.media3.session)
    implementation(libs.media3.exoplayer)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.compose.remember.preference)
    implementation(libs.navigation.compose)
    implementation(libs.hilt.android)
    implementation(libs.play.services.basement)
    implementation(libs.hilt.navigation.compose)
    implementation(libs.paging.compose)
    implementation(libs.paging.runtime.ktx)
    implementation(libs.coil.compose)
    implementation(libs.coil.compose)
    implementation(libs.material.motion.compose.core)
    implementation(libs.krypto.android)

    implementation(libs.androidx.material.icons.extended)
    implementation(libs.accompanist.pager)
    implementation(libs.accompanist.permissions)
    implementation(libs.accompanist.swiperefresh)
    implementation(libs.accompanist.flowlayout)
    implementation(libs.accompanist.placeholder.material)
    implementation(libs.accompanist.navigation.animation)
    implementation(libs.accompanist.pager.indicators)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.okhttp3)
    implementation(libs.logging.interceptor)

    implementation(libs.compose.colorful.sliders)

    implementation(libs.palette)

    kapt(libs.hilt.compiler)
    ksp(libs.androidx.room.compiler)



}