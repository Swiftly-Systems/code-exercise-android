package com.swiftly.codingexersicejavi.framework.di

import android.app.Application
import com.swiftly.codingexersicejavi.framework.ManagerSpecialDataSource
import com.swiftly.codingexersicejavi.framework.network.ManagerSpecialService
import com.swiftly.core.repo.ManagerSpecialRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(app: Application) = ManagerSpecialRepository(ManagerSpecialDataSource(app))
}