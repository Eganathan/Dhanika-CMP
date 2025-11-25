package dev.eknath.dhanika.di

import dev.eknath.dhanika.platformspecific.getDhanikaDatabase
import dev.eknath.dhanika.room.AppDatabase
import dev.eknath.dhanika.room.dao.UserInfoDao
import dev.eknath.dhanika.ui.nav.routes.platformAppContext
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes
import org.koin.dsl.module

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.ksp.generated.module

@Module
@ComponentScan("dev.eknath.dhanika") // Scans entire package for modules
class AppModule

val databaseModules = module {
    single<AppDatabase> { getDhanikaDatabase(platformAppContext) }
    single<UserInfoDao> { get<AppDatabase>().userInfoDao() }
}

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        includes(config)
        modules(databaseModules)
        modules(AppModule().module)
    }
}