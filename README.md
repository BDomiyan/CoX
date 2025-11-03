This project is a Kotlin Multiplatform (KMP) application with three main components:

1. **Shared Module** – Contains the domain and data layers written in Kotlin Multiplatform.
2. **Compose App** – Uses Jetpack Compose Multiplatform for UI that can run on both Android and iOS.
3. **iOS App** – Native iOS app built with SwiftUI that also uses the shared module.

The project follows **Clean Architecture** and uses **MVVM** in the presentation layer. It also applies **SOLID** principles where possible to keep the code modular and clean. Since this is a single-feature app, it’s kept simple but well-structured.

---

### Shared Module

* Domain models and use cases (`User`, `GetUsersUseCase`)
* Repository interfaces and implementations
* Network layer using Ktor
* Common utilities like `NetworkResult` and `Logger`

### Compose App

* UI using Jetpack Compose Multiplatform
* ViewModels use shared use cases
* Runs on Android only (iOS requires additional wrapper app setup)

### iOS App

* Native SwiftUI app
* MVVM architecture
* Uses shared Kotlin module via Koin dependency injection
* Organized into App, Core, Domain, Presentation, and Resources folders

---

## Features

* User list screen with search, loading, error, and empty states
* User detail screen with profile info and contact details
* Centralized theme: colors, fonts, dimensions, and strings
* Reusable UI components like avatar, search bar, loading view, and error view
* Type-safe result handling with `NetworkResult<T>`
* Clean and modular code ready for testing

---

## How to Run the Application

### Prerequisites

* macOS with **Xcode** installed (and iOS Simulator or a real device)
* **Android Studio** installed (for syncing and building shared code)
* JDK installed and configured

### Steps

1. Open the project in **Android Studio**.
2. Let Gradle sync and download all dependencies.
   This also builds the **shared module** automatically.
3. After syncing, build the shared module:

   ```
   ./gradlew :shared:build
   ```
4. To run the iOS app:

   * Open `iosApp.xcodeproj` in **Xcode**
   * Choose a simulator or connected device
   * Press **Run (⌘R)**
     The app will use the shared Kotlin framework automatically.
5. You can also run the **Compose App** from Android Studio:

   * Select the **composeApp** run configuration
   * Choose Android device/emulator
   * Press **Run**
   
   Note: The Compose app currently runs on Android only. For iOS, use the native SwiftUI app (iosApp).

---

## Common Fixes

If you face build issues:

```
./gradlew clean
./gradlew :shared:build
./gradlew --refresh-dependencies
```

Or in Android Studio:
**File → Invalidate Caches / Restart**

---

## Architecture Summary

* **Clean Architecture**: Domain → Data → Presentation layers
* **MVVM**: ViewModels manage state and logic separately from UI
* **Dependency Injection**: Koin used for shared and platform-specific initialization
* **SOLID Principles**: Each layer and class has a single clear responsibility
* **Reusable Components**: Shared UI parts and centralized design system
