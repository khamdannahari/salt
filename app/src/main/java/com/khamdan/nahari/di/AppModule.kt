package com.khamdan.nahari.di

import android.util.Log
import com.khamdan.nahari.data.repository.ReqresRepositoryImpl
import com.khamdan.nahari.data.service.ApiService
import com.khamdan.nahari.ui.login.LoginViewModel
import com.khamdan.nahari.ui.home.HomeViewModel
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.features.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import io.ktor.client.engine.android.Android as Android

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { HomeViewModel(get()) }
}

val repositoryModule = module {
    single { ReqresRepositoryImpl(get()) }
}

val apiServiceModule = module {

    fun provideKtorClient(): HttpClient {
        return HttpClient(Android) {

            install(JsonFeature) {
                serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }

            install(HttpTimeout) {
                requestTimeoutMillis = 15000L
                connectTimeoutMillis = 15000L
                socketTimeoutMillis = 15000L
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("KtorLogger", message)
                    }
                }
                level = LogLevel.ALL
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("KtorHttpStatus", "${response.status.value}")
                }
            }

            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }

        }
    }
    single { ApiService(provideKtorClient()) }
}

val appModules = listOf(
    apiServiceModule,
    repositoryModule,
    viewModelModule
)
