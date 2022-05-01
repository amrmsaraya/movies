<p align="center">
  <img src="https://raw.githubusercontent.com/amrmsaraya/movies/master/app/src/main/res/mipmap-xxxhdpi/ic_launcher.png">
</p>

# Movies

Keep track of popular & top rated movies and see movie details

## Features

- Keep track of popular & top rated movies
- See movie details

## Libraries

- [Jetpack Compose](https://developer.android.com/jetpack/compose) - Declarative UI Framework
- [Material You](https://m3.material.io) - Design System
- [Splash Screen](https://developer.android.com/reference/android/window/SplashScreen) - Newly
  introduced splash screen API
- [Coroutines](https://kotlinlang.org/docs/coroutines-guide.html) - Asynchronous Programming
- [Kotlin Serlization](https://github.com/Kotlin/kotlinx.serialization) - Kotlin Multiplatform
  Serialization
- [Ktor](https://ktor.io/) - HTTP Client
- [Room](https://developer.android.com/jetpack/androidx/releases/room) - Local Database
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - Dependency
  Injection
- [Coil](https://coil-kt.github.io/coil/) - An image loading library for Android backed by Kotlin
  Coroutines

## Architecture and Design Patterns

- [Clean Architecture](https://koenig-media.raywenderlich.com/uploads/2019/02/Clean-Architecture-Bob-650x454.png)
    - :app - Application main activity and dependency injection
    - :buildSrc - Manage application dependencies
    - :database - Contains database, DTOs (Data Transfer Object) & DAOs
    - :preference - Contains app
    - :common - Provide common shared ui & utilities
    - :feature_movie - Provide movie list & movie details
    - each module from above contains its own layers
        - data : contains Mapper, Data sources and Repository Implementation
        - domain : Business layer that contains Repository interfaces and Models (Entities)
        - presentation : UI related code

- [MVI](https://miro.medium.com/max/5152/1*iFis87B9sIfpsgQeFkgu8Q.png) - Model-View-Intent design
  pattern


