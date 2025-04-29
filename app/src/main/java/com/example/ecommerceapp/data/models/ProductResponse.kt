package com.example.ecommerceapp.data.models

// ProductResponse.kt
data class ProductResponse(
    val id: Int,
    val name: String,
    val description: String?,
    val price: Double,
    val stock: Int,
    val category_id: Int,
    val image_url: String?,
    val category: CategoryResponse
)