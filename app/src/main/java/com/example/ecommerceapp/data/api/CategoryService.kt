package com.example.ecommerceapp.data.api

import com.example.ecommerceapp.data.models.CategoryResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface CategoryService {
    // Fetch all categories
    @GET("/categories")
    suspend fun getCategories(): Response<List<CategoryResponse>>
}