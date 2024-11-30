package com.shabeer.recipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shabeer.recipeapp.retrofit.data.models.MealDB
import com.shabeer.recipeapp.retrofit.presentation.ui.CategoryScreen
import com.shabeer.recipeapp.retrofit.presentation.ui.DetailScreen
import com.shabeer.recipeapp.retrofit.presentation.viewmodel.MealViewModel
import com.shabeer.recipeapp.ui.theme.RecipeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RecipeAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {

                    Scaffold(topBar = {
                        CenterAlignedTopAppBar(
                            title = {
                                Text(
                                    text = stringResource(
                                        R.string.app_name
                                    )
                                )
                            },
                            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary
                            )
                        )
                    }) {
                        Box(Modifier.padding(it)) {
                            val viewModel: MealViewModel = hiltViewModel()
                            val getMealData by viewModel.storeMealData.observeAsState()

                            val navController = rememberNavController()
                            NavHost(
                                navController = navController,
                                startDestination = "category_screen"
                            ) {
                                composable("category_screen") {
                                    getMealData?.let {
                                        CategoryScreen(
                                            mealDB = MealDB(categories = it.categories),
                                            navController = navController
                                        )
                                    }
                                }
                                composable("detail_screen/{categoryId}/{categoryTitle}/{categoryDesc}/{categoryImg}") { backStackEntry ->
                                    val categoryTitle =
                                        backStackEntry.arguments?.getString("categoryTitle") ?: ""
                                    val categoryDesc =
                                        backStackEntry.arguments?.getString("categoryDesc") ?: ""
                                    val categoryImg =
                                        backStackEntry.arguments?.getString("categoryImg") ?: ""
                                    DetailScreen(categoryTitle, categoryDesc, categoryImg)
                                }
                            }
                        }
                    }

                }
            }
        }
    }
}


