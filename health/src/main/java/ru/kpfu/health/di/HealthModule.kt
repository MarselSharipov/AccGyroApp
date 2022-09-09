package ru.kpfu.health.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import ru.kpfu.health.body_position.HumanActivityLogger
import ru.kpfu.health.repo.HealthRepository

val healthModule = module {
    single { HealthRepository() }
    single { HumanActivityLogger(androidContext(), get()) }
}