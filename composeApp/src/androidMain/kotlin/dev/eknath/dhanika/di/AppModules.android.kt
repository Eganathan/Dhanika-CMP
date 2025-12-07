package dev.eknath.dhanika.di

import dev.eknath.dhanika.getDatabaseBuilder
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformModule: org.koin.core.module.Module = module {
    single { getDatabaseBuilder(androidContext()) }
}