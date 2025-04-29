package com.example.ecommerceapp.data.api

import com.example.ecommerceapp.data.models.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {
    @GET("products") // Your API endpoint
    suspend fun getProducts(): Response<List<ProductResponse>>

    @GET("products/{id}")
    suspend fun getProductDetail(@Path("id") id: Int): Response<ProductResponse>
}