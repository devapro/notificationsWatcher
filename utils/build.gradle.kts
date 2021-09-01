plugins {
    applyPlugins(AppDependencies.commonPlugins)
}

android {
    defaultConfig {
        version = 1
    }
}

dependencies {
    applyListImplementation(AppDependencies.commonLibraries)
    testImplementation(AppDependencies.testLibraries)
}