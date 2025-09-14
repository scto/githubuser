// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    // Originale Plugins
    id("com.android.application") version "8.11.1" apply false
    id("com.android.library") version "8.11.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.22" apply false
    id("com.google.devtools.ksp") version "1.8.22-1.0.11" apply false
    id("com.android.dynamic-feature") version "8.11.1" apply false

    // Plugins aus dem alten buildscript-Block konvertiert
    id("com.google.gms.google-services") version "4.4.1" apply false
    id("com.google.firebase.crashlytics") version "2.9.9" apply false
}
