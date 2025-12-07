package dev.eknath.dhanika.di

import dev.eknath.dhanika.room.AppDatabase
import dev.eknath.dhanika.room.dao.UserInfoDao
import dev.eknath.dhanika.room.getRoomDatabase
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module

expect val platformModule: org.koin.core.module.Module

@Module
@ComponentScan("dev.eknath.dhanika") // Scans entire package for modules
class AppModule

val sharedModule = module {
    single<AppDatabase> { getRoomDatabase(get()) }
    single<UserInfoDao> { get<AppDatabase>().userInfoDao() }
}

fun initKoin(config: KoinAppDeclaration? = null) { // the config is required to pass the application context etc
    startKoin {
        config?.invoke(this)
        modules(platformModule)
        modules(sharedModule)
        modules(AppModule().module)
    }
}