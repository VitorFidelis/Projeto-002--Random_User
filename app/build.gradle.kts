plugins {
    id("com.android.application")
}

android {
    namespace = "br.com.finasdev.randomuser"
    compileSdk = 33

    defaultConfig {
        applicationId = "br.com.finasdev.randomuser"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    //biblioteca para capturar images de API´s
    implementation ("com.github.bumptech.glide:glide:4.14.1")
    annotationProcessor ("com.github.bumptech.glide:compiler:4.14.1")

    //biblioteca para consumo de API´s
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    //biblioteca para arredondar as bordas dos card
    implementation ("androidx.cardview:cardview:1.0.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}