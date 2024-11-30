package com.shabeer.recipeapp.retrofit.data.repositories

import com.shabeer.recipeapp.retrofit.remote.Remote
import javax.inject.Inject

class MealRepository @Inject constructor(private val remote: Remote) {
    suspend fun getAllData() = remote.fetchData()
}