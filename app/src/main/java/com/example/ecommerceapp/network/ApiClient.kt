package com.example.ecommerceapp.network

import com.example.ecommerceapp.data.api.AuthService
import com.example.ecommerceapp.data.api.CategoryService
import com.example.ecommerceapp.data.api.ProductService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "http://10.0.2.2:8000/api/"

    private val authInterceptor = AuthInterceptor()

    private val client = OkHttpClient.Builder()
        .addInterceptor(authInterceptor) // Add the interceptor here!
        .build()

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client) // Attach the OkHttpClient with AuthInterceptor
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val authService: AuthService by lazy {
        retrofit.create(AuthService::class.java)
    }

    val productApiService: ProductService by lazy {
        retrofit.create(ProductService::class.java)
    }

    val categoryApiService: CategoryService by lazy {
        retrofit.create(CategoryService::class.java)
    }

    // Function to update the token when user logs in
    fun setAuthToken(token: String) {
        authInterceptor.token = token
    }
}
