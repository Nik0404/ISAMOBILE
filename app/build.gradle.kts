plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.androidx.navigation.safargs)

    id("kotlin-kapt")
}

android {
    namespace = "com.example.isa"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.isa"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            buildConfigField("String", "BASE_URL", "\"https://isa.acron.ru:8181/w/\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }

        debug {
            buildConfigField("String", "BASE_URL", "\"https://test-isa-vnov.acron.ru:10081/w/\"")
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
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
        viewBinding = true
        buildConfig = true
    }
    dataBinding {
        enable = true
    }
}

dependencies {
    // Android X
    implementation(libs.androidx.annotation.annotation)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.lifecycle.extensions.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
//    implementation(libs.androidx.lifecycle.livedata.ktx)
//    implementation(libs.androidx.lifecycle.viewmodel.ktx)

//    // Kotlin

    // Navigation Components
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // Design
    implementation(libs.android.legacy.support)
    implementation(libs.material)

    // implementation 'androidx.appcompat:appcompat:1.7.0'
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.activity)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Moxy
    implementation(libs.com.arello.mobile.moxy)
    kapt(libs.com.arello.mobile.moxy.compiler)

    // MaterialViews
    implementation(libs.com.afollestad.material.dialogs)
    implementation(libs.com.rengwuxian.materialedittext.library)

    // Glide
    implementation(libs.com.github.bumptech)
    kapt(libs.com.github.bumptech)
    kapt(libs.com.github.bumptech.compiler)

    // RxJava2
    implementation(libs.io.reactivex.rxjava2)
    implementation(libs.io.reactivex.rxjava2.rxandroid)
    implementation(libs.com.jakewharton.rxbinding2)
    kapt(libs.io.reactivex.rxjava2)
    kapt(libs.io.reactivex.rxjava2.rxandroid)
    kapt(libs.com.jakewharton.rxbinding2)

    // RxPermissions
    implementation(libs.com.github.tbruyelle)

    // Dagger2
    implementation(libs.com.google.dagger)
    implementation(libs.com.google.dagger.android)
    implementation(libs.com.google.dagger.android.support)
    kapt(libs.com.google.dagger.compiler)
    kapt(libs.com.google.dagger.processor)
    kapt(libs.com.google.dagger.android.support)

    // Retrofit2
    implementation(libs.com.squareup.retrofit2)
    implementation(libs.com.squareup.retrofit2.gson)
    implementation(libs.com.squareup.retrofit2.adapter.rxjava)
    implementation(libs.com.jakewharton.rxbinding2)
    implementation(libs.com.squareup.okhttp3.logging.interceptor)

    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.rxjava2)
    kapt(libs.androidx.room.compiler)
    kapt(libs.androidx.room.runtime)
    kapt(libs.androidx.room.rxjava2)

    // Timber
    implementation(libs.com.jakewharton.timber)
}
