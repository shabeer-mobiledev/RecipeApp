package com.shabeer.recipeapp.retrofit.presentation.ui

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import com.shabeer.recipeapp.retrofit.data.models.Category
import com.shabeer.recipeapp.retrofit.data.models.MealDB

@Composable
fun CategoryScreen(mealDB: MealDB?, navController: NavController) {
    // Dynamic color based on Material theme
    val colors = MaterialTheme.colorScheme
    val isLoading = mealDB == null // Check if the data is loading or not

    // Display the CircularProgressIndicator while data is loading
    if (isLoading) {
        LoadingScreen()
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp)
        ) {
            mealDB?.categories?.let {
                items(it) { category ->
                    CategoryItem(category = category, colors = colors, navController = navController)
                }
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    // Display a circular progress indicator while data is loading
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
fun CategoryItem(category: Category, colors: ColorScheme, navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .clickable {

                val encodedCategoryTitle = Uri.encode(category.strCategory)
                val encodedCategoryDesc = Uri.encode(category.strCategoryDescription)
                val encodedCategoryImg = Uri.encode(category.strCategoryThumb)

                navController.navigate("detail_screen/${category.idCategory}/$encodedCategoryTitle/$encodedCategoryDesc/$encodedCategoryImg")
            },
        elevation = CardDefaults.elevatedCardElevation(8.dp), // Increase shadow for better visual impact
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = colors.primaryContainer,
            contentColor = colors.onPrimaryContainer
        )
    ) {

        Column(modifier = Modifier.padding(16.dp)) {

            AsyncImage(
                model = category.strCategoryThumb,
                contentDescription = category.strCategory,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))


            Text(
                text = category.strCategory,
                style = MaterialTheme.typography.headlineSmall.copy(color = colors.onSurfaceVariant),
                modifier = Modifier.padding(bottom = 8.dp)
            )


        }
    }
}

@Composable
fun DetailScreen(categoryTitle: String, categoryDesc: String, categoryImg: String) {
    val colors = MaterialTheme.colorScheme

    Column(modifier = Modifier.padding(16.dp)) {


        Text(
            text = categoryTitle,
            style = MaterialTheme.typography.headlineMedium.copy(color = colors.onSurface),
            modifier = Modifier.padding(bottom = 8.dp)
        )


        AsyncImage(
            model = categoryImg,
            contentDescription = categoryTitle,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = categoryDesc,
            style = MaterialTheme.typography.bodyLarge.copy(color = colors.onSurfaceVariant)
        )
    }
}

