package com.example.coveragex.di

import com.example.coveragex.presentation.screens.userdetail.UserDetailViewModel
import com.example.coveragex.presentation.screens.userlist.UserListViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

/**
 * Koin module for presentation layer dependencies.
 * SOLID: Dependency Inversion - ViewModels depend on abstractions
 */
val presentationModule = module {
    // ViewModels
    viewModelOf(::UserListViewModel)
    viewModelOf(::UserDetailViewModel)
}
