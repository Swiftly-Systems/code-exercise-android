package com.swiftly.codingexersicejavi.framework.di

import com.swiftly.codingexersicejavi.framework.ManagerSpecialsViewModel
import dagger.Component

@Component(modules = [ApplicationModule::class, RepositoryModule::class, UseCasesModule::class, ServiceModule::class])
interface ViewModelComponent {
    fun inject(specialsViewModel: ManagerSpecialsViewModel)
}