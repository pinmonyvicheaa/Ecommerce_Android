package com.example.ecommerceapp.ui.home.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecommerceapp.data.models.ProductResponse
import com.example.ecommerceapp.network.ApiClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val _products = MutableStateFlow<List<ProductResponse>>(emptyList())
    val products: StateFlow<List<ProductResponse>> get() = _products

    // Products filtered by category
    private val _productsByCategory = MutableStateFlow<List<ProductResponse>>(emptyList())
    val productsByCategory: StateFlow<List<ProductResponse>> get() = _productsByCategory

    private val _productDetail = MutableStateFlow<ProductResponse?>(null)
    val productDetail: StateFlow<ProductResponse?> get() = _productDetail

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> get() = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> get() = _errorMessage

    private val baseUrl = "http://10.0.2.2:8000/" // Emulator localhost

    fun fetchProducts() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = ApiClient.productApiService.getProducts()
                if (response.isSuccessful) {
                    //_products.value = response.body() ?: emptyList()

                    // Check if the response body is not null
                    val products = response.body() ?: emptyList()

                    // Normalize image URLs for each product
                    _products.value = products.map {
                        Log.d("Product", "Original Image URL: ${it.image_url}")  // Debug log
                        it.copy(
                            image_url = it.image_url?.let { it1 -> normalizeImageUrl(it1) }
                        )
                    }
                } else {
                    _errorMessage.value = "Failed to load products: ${response.code()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchProductDetail(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = ApiClient.productApiService.getProductDetail(id)
                if (response.isSuccessful) {
                    _productDetail.value = response.body()
                } else {
                    _errorMessage.value = "Failed to load product detail: ${response.code()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun fetchProductsByCategory(categoryId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // Option 2: If you only have "getProducts()", then filter locally
                if (_products.value.isEmpty()) {
                    val response = ApiClient.productApiService.getProducts()
                    if (response.isSuccessful) {
                        val allProducts = response.body() ?: emptyList()
                        _products.value = allProducts
                        _productsByCategory.value = allProducts.filter { it.category_id == categoryId }
                    } else {
                        _errorMessage.value = "Failed to load products: ${response.code()}"
                    }
                } else {
                    _productsByCategory.value = _products.value.filter { it.category_id == categoryId }
                }

            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun normalizeImageUrl(url: String): String {
        return url.replace("http://127.0.0.1", "http://10.0.2.2")
    }
}