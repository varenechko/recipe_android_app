package com.example.recipe.ui

import android.util.Log
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import com.example.recipe.data.api.ApiClient
import com.example.recipe.data.model.Recipe
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

sealed interface Screen {
    data object List : Screen

    data class Details(val recipe: Recipe) : Screen
}

@Composable
fun RecipeApp() {
    val recipes = remember { mutableStateListOf<Recipe>() }
    val currentScreen = remember { mutableStateOf<Screen>(Screen.List) }
    val scope = rememberCoroutineScope()
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Загрузка данных при запуске
    LaunchedEffect(Unit) {
        while (true) {
        try {
            val fetchedRecipes = ApiClient.apiService.getRecipes()
            Log.d("fetchedRecipes", fetchedRecipes.toString())
            recipes.clear()
            recipes.addAll(fetchedRecipes.recipes)
            errorMessage = null
        } catch (e: Exception) {
            errorMessage = "Failed to fetch recipes: ${e.message}"
            Log.e("error!!!", e.toString())
        }
            delay(60000L)
        }
    }

    when (val state = currentScreen.value) {
        is Screen.Details -> RecipeDetails(
            recipe = state.recipe,
            onBackPressed = {
                currentScreen.value = Screen.List
            },
        )

        Screen.List -> RecipeList(
            recipes = recipes,
            onClick = { recipe ->
                currentScreen.value = Screen.Details(recipe)
            })
    }

    errorMessage?.let {
        Snackbar(
            action = {
                TextButton(onClick = {
                    scope.launch {
                        try {
                            val fetchedRecipes = ApiClient.apiService.getRecipes()
                            recipes.clear()
                            recipes.addAll(fetchedRecipes.recipes)
                            errorMessage = null
                        } catch (e: Exception) {
                            errorMessage = "Failed to fetch recipes: ${e.message}"
                        }
                    }
                }) {
                    Text("Retry")
                }
            }
        ) {
            Text(errorMessage!!)
        }
    }
}