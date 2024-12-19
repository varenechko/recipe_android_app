package com.example.recipe.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
fun RecipeList(recipes: List<Recipe>, onClick: (Recipe) -> Unit) {
    Scaffold(
        topBar = { TopAppBar(title = { Text("Recipe App") }) },
        content = { padding ->
            LazyColumn(modifier = Modifier.padding(padding).fillMaxSize()) {
                items(recipes) { recipe ->
                    RecipeCard(recipe, onClick)
                }
            }
        }
    )
}

@Composable
fun RecipeCard(recipe: Recipe, onClick: (Recipe) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {  onClick(recipe) },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(text = recipe.name, style = MaterialTheme.typography.headlineSmall)
                Text(text = recipe.description, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}