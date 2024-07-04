package com.example.squarerepos.di

import com.example.squarerepos.remote.SquareApiClient
import com.example.squarerepos.repository.BackendRepository
import com.example.squarerepos.viewmodel.ReposOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { SquareApiClient }
    single { BackendRepository(get(), get()) }

    viewModel { ReposOverviewViewModel(get()) }
}