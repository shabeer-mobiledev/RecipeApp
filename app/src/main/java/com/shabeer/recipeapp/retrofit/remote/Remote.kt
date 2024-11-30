package com.shabeer.recipeapp.retrofit.remote

import com.shabeer.recipeapp.retrofit.data.models.MealDB
import retrofit2.Response
import retrofit2.http.GET

interface Remote {

    @GET("api/json/v1/1/categories.php")
    suspend fun fetchData() : Response<MealDB>
}