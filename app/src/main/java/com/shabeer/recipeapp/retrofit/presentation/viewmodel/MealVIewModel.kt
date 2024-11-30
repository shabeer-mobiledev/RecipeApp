package com.shabeer.recipeapp.retrofit.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shabeer.recipeapp.retrofit.data.models.MealDB
import com.shabeer.recipeapp.retrofit.data.repositories.MealRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealVIewModel @Inject constructor(private val repository: MealRepository) : ViewModel() {

    private val _storeMealData = MutableLiveData<MealDB>()
    val storeMealData: LiveData<MealDB> = _storeMealData

    init {
        getMealDataFromApi()
    }

    fun getMealDataFromApi() {
        viewModelScope.launch {
            val responce = repository.getAllData()
            if (responce.isSuccessful) {
                _storeMealData.value = responce.body()
            } else {
                Log.d("error", responce.message())
            }
        }
    }
}