import pro.devapp.AppConfig
import pro.devapp.VersionsDeps

plugins {
    applyPlugins(AppDependencies.appPlugins)
}

android {
    compileSdk = AppConfig.compileSdkVersion

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        testInstrumentationRunner = AppConfig.androidTestInstrumentation
        multiDexEnabled = true
        setProperty("archivesBaseName", "$applicationId-v$versionName($versionCode)")
    }

    viewBinding {
        android.buildFeatures.viewBinding = true
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
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
        languageVersion = VersionsDeps.kotlin
    }
    packagingOptions {
        resources.excludes.add("META-INF/gradle/incremental.annotation.processors")
    }
}

configurations{
    implementation.exclude("group" to "org.jetbrains" , "module" to "annotations")
}

dependencies {
    applyListImplementation(AppDependencies.appLibraries)
    applyListImplementation(AppDependencies.roomLib)
    applyListImplementation(AppDependencies.daggerLib)
    applyListImplementation(AppDependencies.rxLib)
    //test libs
    testImplementation(AppDependencies.testLibraries)
    androidTestImplementation(AppDependencies.androidTestLibraries)
}

kotlinter {
    ignoreFailures = false
    indentSize = 4
    reporters = arrayOf("checkstyle", "html")
    experimentalRules = false
    disabledRules = emptyArray<String>()
}