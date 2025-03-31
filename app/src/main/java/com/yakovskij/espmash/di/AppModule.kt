package com.yakovskij.espmash.di

import EspRepository
import com.yakovskij.espmash.data.database.AppDatabase
import com.yakovskij.espmash.ui.espcam.EspCamViewModel
import com.yakovskij.espmash.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.getInstance(get()).espDeviceDao() }
    single { AppDatabase.getInstance(get()).espFeatureDao() }
    single { AppDatabase.getInstance(get()).espFeatureConfigDao() }
    single { EspRepository(get(), get(), get()) } // Один экземпляр репозитория

    viewModel { MainViewModel(get()) } // Новый ViewModel с репозиторием
    viewModel { EspCamViewModel(get()) }
}