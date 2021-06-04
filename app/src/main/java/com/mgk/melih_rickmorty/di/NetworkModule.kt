package com.mgk.melih_rickmorty.di

import com.mgk.melih_rickmorty.api.RmRetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideRmRetrofitService(): RmRetrofitService {
        return RmRetrofitService.create()
    }

}