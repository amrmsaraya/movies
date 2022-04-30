@file:Suppress("UnstableApiUsage")

include(":preference")


pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Movies"
include(":app")
include(":common")
include(":database")
include(":network")
include(":feature_movies")
