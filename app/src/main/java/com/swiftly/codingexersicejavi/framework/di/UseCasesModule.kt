package com.swiftly.codingexersicejavi.framework.di

import com.swiftly.codingexersicejavi.framework.usecases.UseCases
import com.swiftly.core.repo.ManagerSpecialRepository
import com.swiftly.core.usecase.GetAllSpecials
import dagger.Module
import dagger.Provides


@Module
class UseCasesModule {
    @Provides
    fun getUseCases(repository: ManagerSpecialRepository) = UseCases(
        GetAllSpecials(repository),
    )
}
