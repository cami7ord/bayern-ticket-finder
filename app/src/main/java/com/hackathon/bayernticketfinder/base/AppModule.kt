package com.hackathon.bayernticketfinder.base

import com.hackathon.bayernticketfinder.data.auth.AuthRepository
import com.hackathon.bayernticketfinder.data.auth.AuthRepositoryImpl
import com.hackathon.bayernticketfinder.ui.auth.AuthViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // single instance of HelloRepository
    single<AuthRepository> { AuthRepositoryImpl() }

    // MyViewModel ViewModel
    viewModel { AuthViewModel(get()) }
}
