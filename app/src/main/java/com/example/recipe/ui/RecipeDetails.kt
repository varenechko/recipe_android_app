package com.example.recipe.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.recipe.data.model.Recipe

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipeDetails(recipe: Recipe, onBackPressed: () -> Unit) {
    BackHandler(onBack = onBackPressed)

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = recipe.name) }, navigationIcon = {
                IconButton(onClick = onBackPressed) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            })
        }
    ) {
        Column(
            modifier = Modifier.padding(paddingValues = it)
        ) {
            Text(recipe.name, style = MaterialTheme.typography.headlineLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Времи приготовления: ${recipe.preparation_time}", style = MaterialTheme.typography.headlineSmall)
            Text("Сложность: ${recipe.difficulty}", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            Text(recipe.description, style = MaterialTheme.typography.bodyLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text("Ингредиенты:", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            recipe.ingredients.forEachIndexed { index, ingredient ->
                Text("${index + 1}. ${ingredient.name}, ${ingredient.quantity}", style = MaterialTheme.typography.bodyMedium)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text("Шаги:", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(8.dp))
            recipe.steps.forEachIndexed { index, step ->
                Text("${index + 1}. $step", style = MaterialTheme.typography.bodyMedium)
            }

        }
    }
}
