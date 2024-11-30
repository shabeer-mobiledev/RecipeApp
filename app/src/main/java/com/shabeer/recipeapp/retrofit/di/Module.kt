package com.shabeer.recipeapp.retrofit.di

import com.shabeer.recipeapp.retrofit.data.repositories.MealRepository
import com.shabeer.recipeapp.retrofit.remote.Remote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class Module {

    @Provides
    @Singleton
    fun provideRetrofiObJetc(): Retrofit {
        return Retrofit.Builder().baseUrl("https://www.themealdb.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    fun provideApiServe(retrofit: Retrofit): Remote{
        return retrofit.create(Remote::class.java)
    }

    @Provides
    fun provideRepository(remote: Remote) : MealRepository{
        return MealRepository(remote)
    }
}