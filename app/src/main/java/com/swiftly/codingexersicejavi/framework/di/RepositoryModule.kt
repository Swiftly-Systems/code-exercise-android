package com.swiftly.codingexersicejavi.framework.di

import android.app.Application
import com.swiftly.codingexersicejavi.framework.ManagerSpecialDbSource
import com.swiftly.codingexersicejavi.framework.ManagerSpecialRepository
import com.swiftly.codingexersicejavi.framework.network.ManagerSpecialService
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(app: Application) = ManagerSpecialRepository(
        ManagerSpecialDbSource(app),
        ManagerSpecialService(app, ManagerSpecialDbSource(app)
        )
    )
}