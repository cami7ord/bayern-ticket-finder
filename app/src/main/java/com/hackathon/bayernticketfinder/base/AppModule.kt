package com.hackathon.bayernticketfinder.base

import com.hackathon.bayernticketfinder.data.auth.AuthRepository
import com.hackathon.bayernticketfinder.data.auth.AuthRepositoryImpl
import com.hackathon.bayernticketfinder.data.trips.DestinationsRepository
import com.hackathon.bayernticketfinder.data.trips.DestinationsRepositoryImpl
import com.hackathon.bayernticketfinder.ui.auth.AuthViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // single instance of HelloRepository
    single<AuthRepository> { AuthRepositoryImpl() }
    single<DestinationsRepository> { DestinationsRepositoryImpl() }
    // MyViewModel ViewModel
    viewModel { AuthViewModel(get(), get()) }

}