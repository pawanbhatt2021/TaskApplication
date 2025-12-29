plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id ("kotlin-kapt")
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android.plugin)
}

android {
    namespace = "com.example.taskapplication"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.taskapplication"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    viewBinding{
        enable = true
    }
    dataBinding{
        enable = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    kapt {
        correctErrorTypes = true
    }
    hilt {
        enableAggregatingTask = false
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.circular.imageview)
    implementation(libs.dot.indicator)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    // network call
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp)
    // coroutines
    implementation(libs.coroutines.android)

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)

    implementation(libs.retrofit.rxjava2)
    implementation(libs.viewmodel.ktx)

    implementation(libs.glide)
    annotationProcessor(libs.glide.compiler)

    implementation(libs.coil)
    implementation(libs.coil.svg)
}