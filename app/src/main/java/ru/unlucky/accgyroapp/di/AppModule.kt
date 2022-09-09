package ru.unlucky.accgyroapp.di

import org.koin.dsl.module
import ru.unlucky.accgyroapp.ui.MainPresenter

val appModule = module {
    factory { MainPresenter(get(), get(), get()) }
}