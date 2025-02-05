# libs.versions.toml

[versions]
```sh
//Navigation
navigationCompose = "2.8.3"
kotlinSerialization = "1.6.3"

//DataStore
datastorePreferences = "1.1.1"

//Room
roomRuntime = "2.6.1"
kspVersion = "2.0.21-1.0.27"

//Charts
composeCharts = "0.1.1"

//DateTime
composeDateTime = "0.9.0"

```

[libraries]
```sh
//Navigation
androidx-navigation-compose = { group = "androidx.navigation", name = "navigation-compose", version.ref = "navigationCompose" }
kotlinx-serialization = { group = "org.jetbrains.kotlinx", name = "kotlinx-serialization-json", version.ref = "kotlinSerialization" }

//ViewModel
androidx-lifecycle-viewmodel-compose = { group = "androidx.lifecycle", name = "lifecycle-viewmodel-compose", version.ref = "lifecycleRuntimeKtx" }

//DataStore
androidx-datastore-preferences = { group = "androidx.datastore", name = "datastore-preferences", version.ref = "datastorePreferences" }

//Room
androidx-room-compiler = { module = "androidx.room:room-compiler", version.ref = "roomRuntime" }
androidx-room-ktx = { module = "androidx.room:room-ktx", version.ref = "roomRuntime" }
androidx-room-runtime = { module = "androidx.room:room-runtime", version.ref = "roomRuntime" }

//Charts
compose-charts = { module = "io.github.ehsannarmani:compose-charts", version.ref = "composeCharts" }

//DateTimePicker
datetime = { module = "io.github.vanpra.compose-material-dialogs:datetime", version.ref = "composeDateTime" }

```

[plugins]
```sh
//Navigation
jetbrains-kotlin-serialization = { id = "org.jetbrains.kotlin.plugin.serialization", version.ref = "kotlin" }

//Room
kotlin-ksp = {id = "com.google.devtools.ksp", version.ref = "kspVersion"}
```




# build.gradle.kts (Module :app)

plugins {
```sh
//Navigation
alias(libs.plugins.jetbrains.kotlin.serialization)

//Room
alias(libs.plugins.kotlin.ksp)
```
}

dependencies {
```sh
//Navigation
implementation(libs.androidx.navigation.compose)
implementation(libs.kotlinx.serialization)

//ViewModel
implementation(libs.androidx.lifecycle.viewmodel.compose)

//DataStore
implementation(libs.androidx.datastore.preferences)

//Room
implementation(libs.androidx.room.runtime)
implementation(libs.androidx.room.ktx)
ksp(libs.androidx.room.compiler)

// Charts
implementation (libs.compose.charts)
}

// DateTimePicker
implementation(libs.datetime)

```





# build.gradle.kts (Project: ...)

plugins {
```sh
//Room
alias(libs.plugins.kotlin.ksp) apply false
```
}
