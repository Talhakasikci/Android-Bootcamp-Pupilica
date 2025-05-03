buildscript {
    dependencies{
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.8.9")
    }
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}