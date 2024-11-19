import java.util.Properties

val properties = Properties().apply {
    load(project.rootProject.file("local.properties").inputStream())
}
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")

    id("com.google.devtools.ksp") version "2.0.21-1.0.26"
    kotlin("plugin.serialization") version "1.9.0"
    id("com.google.dagger.hilt.android")
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.practicecompose"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.practicecompose"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "SUPABASE_ANON_KEY", "${properties["SUPABASE_ANON_KEY"]}")
        buildConfigField("String", "SECRET", "${properties["SECRET"]}")
        buildConfigField("String", "SUPABASE_URL", "${properties["SUPABASE_URL"]}")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_12
        targetCompatibility = JavaVersion.VERSION_12
    }
    kotlinOptions {
        jvmTarget = "12"
    }
    buildFeatures {
        compose = true
        buildConfig = true
        viewBinding = true
    }
        composeOptions {
            kotlinCompilerExtensionVersion = "1.5.3"
        }


    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {
    implementation(libs.androidx.constraintlayout.core)
    implementation(libs.androidx.foundation.android)
    implementation(libs.androidx.material3.android)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.car.ui.lib)
    implementation(libs.play.services.maps)

    implementation(libs.androidx.core.ktx.v1120)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.play.services.location)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.volley)
    implementation(libs.androidx.espresso.core.v351)
    implementation(libs.androidx.foundation.layout.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit.v115)
    androidTestImplementation(libs.androidx.espresso.core.v351)

    // Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.ktx.v262)
    implementation(libs.androidx.lifecycle.extensions)
    implementation(libs.androidx.activity.ktx)

    // Reflection
    implementation(libs.kotlin.reflect)

    // SwipeRefreshlayout
    implementation(libs.androidx.swiperefreshlayout)

    // DAGGER
    implementation(libs.dagger)
    ksp(libs.dagger.compiler)

    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // PICASSO for image view
    implementation(libs.picasso)

    implementation(libs.glide)
    ksp(libs.glide.compiler)

    implementation(libs.material.v1130alpha07)
    implementation(libs.androidx.fragment.ktx)

    // Google Map
    implementation(libs.play.services.maps.v1800)
    implementation(libs.play.services.location.v2101)


    /*implementation(libs.postgrest.kt)
    val ktor_version = "3.0.1"
    implementation(libs.ktor.client.core)
    implementation("io.ktor:ktor-client-android:$ktor_version")
    implementation("io.ktor:ktor-client-okhttp:$ktor_version")
    implementation("io.ktor:ktor-client-logging:$ktor_version")
    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")*/

    //Supabase
    implementation("io.github.jan-tennert.supabase:supabase-kt:3.0.2")
    implementation("io.github.jan-tennert.supabase:postgrest-kt:3.0.2")
    implementation("io.github.jan-tennert.supabase:auth-kt:3.0.2")
    implementation("io.ktor:ktor-client-android:3.0.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")


    implementation ("com.google.android.libraries.identity.googleid:googleid:1.1.1")

// optional - needed for credentials support from play services, for devices running
// Android 13 and below.
    implementation("androidx.credentials:credentials:1.3.0")
    implementation("androidx.credentials:credentials-play-services-auth:1.3.0")
    implementation("androidx.activity:activity-compose:1.3.0-alpha07")
    implementation ("io.github.chaosleung:pinview:1.4.4")
    implementation ("androidx.compose.ui:ui-tooling:1.0.1")

    //Compose Theme builder
    implementation("androidx.compose.material3:material3:1.3.1")
    implementation("androidx.compose.material3:material3-window-size-class:1.3.1")
    implementation("androidx.compose.material3:material3-adaptive-navigation-suite:1.4.0-alpha04")

    //For implementing Gif file in compose
    implementation("io.coil-kt:coil:2.0.0-rc02")
    implementation("io.coil-kt:coil-gif:2.0.0-rc02")
    implementation("io.coil-kt:coil-compose:2.0.0-rc02")

    //Compose Icon access
    implementation(libs.ui)
    implementation(libs.androidx.material.icons.core)
    implementation(libs.androidx.material.icons.extended)

    //Room
    implementation(libs.androidx.room.runtime)
    ksp(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)

}