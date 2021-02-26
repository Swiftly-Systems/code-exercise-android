package com.swiftly.codingexersicejavi.framework.di

import android.app.Application
import com.swiftly.codingexersicejavi.framework.ManagerSpecialDbSource
import com.swiftly.codingexersicejavi.framework.network.ManagerSpecialService
import com.swiftly.core.repo.ManagerSpecialRepository
import dagger.Module
import dagger.Provides

@Module
class ServiceModule {
    @Provides
    fun provideService(app: Application) = ManagerSpecialService(app, ManagerSpecialDbSource(app))
}