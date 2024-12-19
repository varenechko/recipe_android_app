package com.example.recipe.data.api

import com.example.recipe.data.model.RecipeDTO
import retrofit2.http.GET

interface RecipeApiService {
    @GET("recipes")
    suspend fun getRecipes(): RecipeDTO
}


