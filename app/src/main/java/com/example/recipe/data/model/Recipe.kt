package com.example.recipe.data.model

data class Ingredient (
    val name: String, val quantity: String,
)

data class Recipe(
    val id: Int,
    val name: String,
    val description: String,
    val imageUrl: String,
    val ingredients: List<Ingredient>,
    val steps: List<String>,
    val preparation_time: String,
    val difficulty: String,
)

data class RecipeDTO (
    val recipes: List<Recipe>
)
