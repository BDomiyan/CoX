package com.example.coveragex.di

import com.example.coveragex.data.api.GenericHttpClient
import com.example.coveragex.data.api.HttpClientFactory
import com.example.coveragex.data.api.RandomUserApi
import com.example.coveragex.data.api.RandomUserApiImpl
import com.example.coveragex.data.repository.UserRepository
import com.example.coveragex.data.repository.UserRepositoryImpl
import com.example.coveragex.domain.usecase.GetUsersUseCase
import org.koin.dsl.module

val sharedModule = module {

    // HTTP Client - Single instance for connection pooling
    single {
        GenericHttpClient(HttpClientFactory.createClient())
    }

    // Random User API - Interface binding for testability
    single<RandomUserApi> {
        RandomUserApiImpl(get())
    }

    // User Repository - Interface binding for testability
    single<UserRepository> {
        UserRepositoryImpl(get())
    }

    // Get Users Use Case
    single {
        GetUsersUseCase(get())
    }
}

/**
 * Helper function for iOS to initialize Koin.
 * Call this from iOS app startup code.
 */
fun initKoin() {
    org.koin.core.context.startKoin {
        modules(sharedModule)
    }
}
