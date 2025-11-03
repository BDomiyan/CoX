package com.example.coveragex.di

import com.example.coveragex.domain.usecase.GetUsersUseCase
import com.example.coveragex.data.repository.UserRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

/**
 * Helper class to access Koin dependencies from iOS
 * This provides a bridge between Kotlin Koin DI and Swift
 */
class KoinHelper : KoinComponent {
    
    init {
        // Initialize Koin if not already started
        try {
            startKoin {
                modules(sharedModule)
            }
        } catch (e: Exception) {
            // Koin already started, ignore
        }
    }
    
    /**
     * Get GetUsersUseCase from Koin
     */
    fun getGetUsersUseCase(): GetUsersUseCase {
        val useCase: GetUsersUseCase by inject()
        return useCase
    }
    
    /**
     * Get UserRepository from Koin
     */
    fun getUserRepository(): UserRepository {
        val repository: UserRepository by inject()
        return repository
    }
}
