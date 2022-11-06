import org.gradle.kotlin.dsl.DependencyHandlerScope

@Suppress("MemberVisibilityCanBePrivate")
sealed class Dependencies {

    object Ktor : Dependencies() {
        const val version = "2.0.3"

        const val ktorClientCore = "io.ktor:ktor-client-core:$version"
        const val ktorClientCio = "io.ktor:ktor-client-cio:$version"
        const val ktorClientWebsockets = "io.ktor:ktor-client-websockets:$version"
        const val ktorClientContentNegotiation = "io.ktor:ktor-client-content-negotiation:$version"
        const val ktorSerializationJson = "io.ktor:ktor-serialization-kotlinx-json:$version"
        const val ktorClientLogging = "io.ktor:ktor-client-logging:$version"

        override fun invoke(scope: DependencyHandlerScope) {
            scope {
                implementation(ktorClientCore)
                implementation(ktorClientCio)
                implementation(ktorClientWebsockets)
                implementation(ktorClientContentNegotiation)
                implementation(ktorSerializationJson)
                implementation(ktorClientLogging)
            }
        }
    }

    object KotlinX : Dependencies() {
        const val datetimeVersion = "0.4.0"
        const val serializationVersion = "1.3.3"

        const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:$datetimeVersion"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:$serializationVersion"

        override fun invoke(scope: DependencyHandlerScope) {
            scope {
                implementation(datetime)
                implementation(serialization)
            }
        }
    }

    object AndroidxCore : Dependencies() {
        const val version = "1.8.0"

        const val core = "androidx.core:core:$version"
        const val coreKtx = "androidx.core:core-ktx:$version"
        const val coreSplashScreen = "androidx.core:core-splashscreen:1.0.0"

        override fun invoke(scope: DependencyHandlerScope) {
            scope {
                implementation(core)
                implementation(coreKtx)
                implementation(coreSplashScreen)
            }
        }
    }

    object AndroidxPreferences : Dependencies() {
        const val version = "1.2.0"

        const val preference = "androidx.preference:preference:$version"
        const val preferenceKtx = "androidx.preference:preference-ktx:$version"

        override fun invoke(scope: DependencyHandlerScope) {
            scope {
                implementation(preference)
                implementation(preferenceKtx)
            }
        }
    }

    object AndroidxRoom : Dependencies() {
        const val version = "2.4.3"

        const val roomRuntime = "androidx.room:room-runtime:$version"
        const val roomKtx = "androidx.room:room-ktx:$version"
        const val roomCompiler = "androidx.room:room-compiler:$version"

        override fun invoke(scope: DependencyHandlerScope) {
            scope {
                implementation(roomRuntime)
                implementation(roomKtx)
                ksp(roomCompiler)
            }
        }
    }

    object Compose : Dependencies() {
        const val version = "1.2.1"
        const val compilerVersion = "1.3.0"

        const val activity = "androidx.activity:activity-compose:1.5.1"
        const val animation = "androidx.compose.animation:animation:$version"
        const val compiler = "androidx.compose.compiler:compiler:$compilerVersion"
        const val foundation = "androidx.compose.foundation:foundation:$version"
        const val material = "androidx.compose.material:material:$version"
        const val material3 = "androidx.compose.material3:material3:1.0.0-alpha16"
        const val runtime = "androidx.compose.runtime:runtime:$version"
        const val ui = "androidx.compose.ui:ui:$version"
        const val uiText = "androidx.compose.ui:ui-text:$version"
        const val uiUtil = "androidx.compose.ui:ui-util:$version"
        const val uiTool = "androidx.compose.ui:ui-tooling:$version"
        const val preTool = "androidx.compose.ui:ui-tooling-preview:$version"
        const val iconExt = "androidx.compose.material:material-icons-extended:$version"

        override fun invoke(scope: DependencyHandlerScope) {
            scope {
                implementation(activity)
                implementation(animation)
                implementation(foundation)
                implementation(ui)
                implementation(uiUtil)
                implementation(uiText)
                implementation(runtime)
                implementation(material)
                implementation(material3)
                implementation(uiTool)
                implementation(preTool)
                implementation(iconExt)
            }
        }
    }
    object Cnd : Dependencies() {
        const val version = "1.1.2-beta"

        const val cnd_core = "io.github.raamcosta.compose-destinations:core:$version"

        const val cnd_ksp = "io.github.raamcosta.compose-destinations:kps:$version"

        override fun invoke(scope: DependencyHandlerScope) {
            scope {
                implementation(cnd_core)
                ksp(cnd_ksp)
            }
        }
    }

    object Accompanist : Dependencies() {
        const val version = "0.25.1"

        const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:$version"
        const val placeholder = "com.google.accompanist:accompanist-placeholder:$version"

        override fun invoke(scope: DependencyHandlerScope) {
            scope {
                implementation(systemUiController)
                implementation(placeholder)
            }
        }
    }

    object Shimmer : Dependencies() {
        const val version = "1.0.3"

        const val shimmer = "com.valentinilk.shimmer:compose-shimmer:$version"

        override fun invoke(scope: DependencyHandlerScope) {
            scope {
                implementation(shimmer)
            }
        }
    }

    object Material : Dependencies() {
        const val version = "1.5.0"

        const val material = "com.google.android.material:material:$version"

        override fun invoke(scope: DependencyHandlerScope) {
            scope {
                implementation(material)
            }
        }
    }

    object Coil : Dependencies() {
        const val version = "2.1.0"

        const val coilBase = "io.coil-kt:coil:$version"
        const val coilCompose = "io.coil-kt:coil-compose:$version"


        override fun invoke(scope: DependencyHandlerScope) {
            scope {
                implementation(coilBase)
                implementation(coilCompose)
            }
        }
    }

    object Qiniu : Dependencies() {
        const val qiniu_version = "8.4.0"
        const val okhttp_version = "4.3.2"

        const val qiniu = "com.qiniu:qiniu-android-sdk:$qiniu_version"
        const val okhttp = "com.squareup.okhttp3:okhttp:$okhttp_version"
        const val matisse = "com.github.leavesCZY:Matisse:0.0.7"

        override fun invoke(scope: DependencyHandlerScope) {
            scope {
                implementation(qiniu)
                implementation(okhttp)
                implementation(matisse)
            }
        }
    }

    object ExoPlayer : Dependencies() {
        const val version = "2.18.1"

        const val exoplayerCore = "com.google.android.exoplayer:exoplayer-core:$version"
        const val exoplayerDash = "com.google.android.exoplayer:exoplayer-dash:$version"
        const val exoplayerUi = "com.google.android.exoplayer:exoplayer-ui:$version"

        override fun invoke(scope: DependencyHandlerScope) {
            scope {
                implementation(exoplayerCore)
                implementation(exoplayerDash)
                implementation(exoplayerUi)
            }
        }
    }

    object Koin : Dependencies() {
        const val version = "3.2.0"

        const val koin = "io.insert-koin:koin-android:$version"
        const val koinCompose = "io.insert-koin:koin-androidx-compose:$version"

        override fun invoke(scope: DependencyHandlerScope) {
            scope {
                implementation(koin)
                implementation(koinCompose)
            }
        }
    }

    object HCaptcha : Dependencies() {
        const val version = "2.2.0"

        const val hCaptchaSdk = "com.github.hcaptcha:hcaptcha-android-sdk:$version"

        override fun invoke(scope: DependencyHandlerScope) {
            scope {
                implementation(hCaptchaSdk)
            }
        }
    }

    object KSP : Dependencies() {
        const val version = "1.7.10-1.0.6"

        const val ksp = "com.google.devtools.ksp:symbol-processing-api:$version"

        override fun invoke(scope: DependencyHandlerScope) {
            scope {
                implementation(ksp)
            }
        }
    }

    //TODO migrate to context receivers after upgrading to kotlin 1.6.20
    abstract operator fun invoke(scope: DependencyHandlerScope)

    //TODO migrate to context receivers after upgrading to kotlin 1.6.20
    protected fun DependencyHandlerScope.implementation(dependencyNotation: String) {
        "implementation"(dependencyNotation)
    }

    //TODO migrate to context receivers after upgrading to kotlin 1.6.20
    protected fun DependencyHandlerScope.ksp(dependencyNotation: String) {
        "ksp"(dependencyNotation)
    }
}
