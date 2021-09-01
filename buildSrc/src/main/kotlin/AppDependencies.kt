import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec
import pro.devapp.TestVersions
import pro.devapp.VersionsDeps


object AppDependencies {
    //std lib
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${VersionsDeps.kotlinStd}"

    //android ui
    private val appcompat = "androidx.appcompat:appcompat:${VersionsDeps.appcompat}"
    private val coreKtx = "androidx.core:core-ktx:${VersionsDeps.ktx}"
    private val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${VersionsDeps.constraintLayout}"
    private val material = "com.google.android.material:material:${VersionsDeps.material}"

    //room
    private val roomCore = "androidx.room:room-runtime:${VersionsDeps.room}"
    private val roomKapt = "androidx.room:room-compiler:${VersionsDeps.room}"
    private val roomRx = "androidx.room:room-rxjava2:${VersionsDeps.room}"

    //dagger
    private val dagger = "com.google.dagger:dagger:${VersionsDeps.dagger}"
    private val daggerKapt = "com.google.dagger:dagger-compiler:${VersionsDeps.dagger}"

    //rx
    private val rxJava = "io.reactivex.rxjava2:rxjava:${VersionsDeps.rxjava}"
    private val rxAndroid = "io.reactivex.rxjava2:rxandroid:${VersionsDeps.rxandroid}"

    //test libs
    private val junit = "junit:junit:${TestVersions.junit}"
    private val extJUnit = "androidx.test.ext:junit:${TestVersions.extJunit}"

    val appLibraries = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(coreKtx)
        add(appcompat)
        add(material)
        add(constraintLayout)
    }

    val commonLibraries = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(coreKtx)
    }

    val roomLib = arrayListOf<String>().apply {
        add(roomCore)
        add(roomKapt)
        add(roomRx)
    }

    val daggerLib = arrayListOf<String>().apply {
        add(dagger)
        add(daggerKapt)
    }

    val rxLib = arrayListOf<String>().apply {
        add(rxJava)
        add(rxAndroid)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
    }

    val appPlugins = arrayListOf<String>().apply {
        add("com.android.application")
        add("kotlin-android")
        add("kotlin-kapt")
        add("org.jmailen.kotlinter")
    }

    val commonPlugins = arrayListOf<String>().apply {
        add("com.android.library")
        add("kotlin-android")
        add("kotlin-kapt")
        add("common-precompiled")
        add("org.jmailen.kotlinter")
    }
}

//util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.applyListImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun PluginDependenciesSpec.applyPlugins(list: List<String>) {
    list.forEach { dependency ->
        id(dependency)
    }
    kotlin("android")
}