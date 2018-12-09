package com.gdavidpb.daggerexample.data.di.modules

import com.gdavidpb.daggerexample.data.repository.JsonDataRepository
import com.gdavidpb.daggerexample.domain.repository.JsonRepository
import dagger.Binds
import dagger.Module

@Module
interface RepositoryModule {
    @Binds
    fun bindAbacusRepository(repository: JsonDataRepository): JsonRepository
}