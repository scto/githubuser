import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    //kotlin("kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.arya.githubuser.core"
    compileSdk = 34

    defaultConfig {
        val commit = getGitCommit()
        applicationId = "com.arya.githubuser.core"
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        //consumerProguardFiles("consumer-rules.pro")

        vectorDrawables.useSupportLibrary = true

        buildConfigField("String", "GIT_COMMIT", "\"$commit\"")
        buildConfigField("String", "API_KEY", "\"${getApiKey()}\"")
        buildConfigField("String", "BASE_URL", "\"${getBaseUrl()}\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
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
    
    buildFeatures {
        buildConfig = true
        //compose = true
    }
}

dependencies {
    implementation(project(":common"))
    
    // Variable in Kotlin definieren
    val roomVersion = "2.8.0"

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // Variablen-Interpolation in Strings verwenden
    implementation("androidx.room:room-runtime:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")

    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("net.zetetic:android-database-sqlcipher:4.5.3")
    implementation("io.insert-koin:koin-android:3.2.2")
    
    androidTestImplementation("androidx.test.ext:junit:1.3.0")
    
    //testImplementation("junit:junit:4.12")
    testImplementation("junit:junit:4.13.2")
    testImplementation("io.insert-koin:koin-test-junit4:3.3.2")
    testImplementation("org.mockito:mockito-core:5.2.0")
    testImplementation("org.mockito:mockito-inline:5.2.0")
}

fun getGitCommit(): String {
    return try {
        val commit = providers.exec {
            commandLine("git", "rev-parse", "--short", "HEAD")
        }.standardOutput.asText.get().trim()
        println("Git commit: $commit")
        commit
    } catch (_: Exception) {
        ""
    }
}

fun getApiKey(): String {
    val properties = Properties()
    val localPropertiesFile = project.rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        properties.load(FileInputStream(localPropertiesFile))
    }
    return properties.getProperty("API_KEY", "DEFAULT_API_KEY_IF_NOT_FOUND")
}

fun getBaseUrl(): String {
    val properties = Properties()
    val localPropertiesFile = project.rootProject.file("local.properties")
    if (localPropertiesFile.exists()) {
        properties.load(FileInputStream(localPropertiesFile))
    }
    return properties.getProperty("BASE_URL", "DEFAULT_BASE_URL_IF_NOT_FOUND")
}