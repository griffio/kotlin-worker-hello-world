import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackOutput.Target

plugins {
    kotlin("js") version "1.4.10"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-js"))
    testImplementation(kotlin("test-js"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.4.0-M1")
}

kotlin {
    js(IR) { /* new backend compiler enables aggressive optimizations of output bundle */
        browser {
            webpackTask {
                output.libraryTarget = Target.SELF
            }
        }
        binaries.executable()
    }
}
