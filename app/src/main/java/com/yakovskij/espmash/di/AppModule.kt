package com.yakovskij.espmash.di

import com.yakovskij.espmash.data.repository.EspRepository
import com.yakovskij.espmash.ui.espcam.EspCamViewModel
import com.yakovskij.espmash.ui.esptoggle.EspToggleViewModel
import com.yakovskij.espmash.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { EspRepository() } // Один экземпляр репозитория
    viewModel { MainViewModel(get()) } // Новый ViewModel с репозиторием
    viewModel { EspCamViewModel(get()) }
    viewModel { EspToggleViewModel(get()) }
}