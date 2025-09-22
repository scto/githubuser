plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    //kotlin("kapt") // Alternative Schreibweise für id("kotlin-kapt")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

android {
    namespace = "com.arya.githubuser"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.arya.githubuser"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        
        isMultiDexEnabled = true // 'is' Präfix für Boolean

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") { // Expliziter Aufruf von getByName
            isMinifyEnabled = false // 'is' Präfix für Boolean
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        buildConfig = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    
    kotlin {
        jvmToolchain(17)
    }
    
    /*
    kotlinOptions {
        jvmTarget = "17"
    }
    */
    
    //dynamicFeatures += ":favorite" // Kotlin-konforme Syntax für Listen
}

dependencies {
    implementation(project(":common"))
    implementation(project(":core"))
    implementation(project(":presentation"))
    
    // Funktionsaufrufe mit Klammern und doppelten Anführungszeichen
    implementation("androidx.core:core-ktx:1.17.0")
    implementation("androidx.appcompat:appcompat:1.7.1")
    implementation("io.insert-koin:koin-android:3.2.2")
    implementation("androidx.multidex:multidex:2.0.1")
    implementation("com.google.android.play:core:1.10.3")
    implementation("com.google.firebase:firebase-crashlytics:18.6.2")

    debugImplementation("com.squareup.leakcanary:leakcanary-android:2.13")
}
