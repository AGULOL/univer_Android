// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
    // Вместо прямой версии используйте алиас из libs
    alias(libs.plugins.kotlinSerialization)
}