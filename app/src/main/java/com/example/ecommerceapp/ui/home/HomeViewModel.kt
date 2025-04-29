//package com.example.ecommerceapp.ui.home
//
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.example.ecommerceapp.data.models.Product
//import com.example.ecommerceapp.data.models.ProductResponse
//import com.example.ecommerceapp.network.ApiClient
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.launch
//
//class HomeViewModel : ViewModel() {
//
//    private val _products = MutableStateFlow<List<ProductResponse>>(emptyList())
//    val products: StateFlow<List<ProductResponse>> = _products
//
//    private val _isLoading = MutableStateFlow(false)
//    val isLoading: StateFlow<Boolean> = _isLoading
//
//    private val _errorMessage = MutableStateFlow<String?>(null)
//    val errorMessage: StateFlow<String?> = _errorMessage
//
//    private val productApiService = ApiClient.productApiService
//
//    init {
//        fetchProducts() // fetch all initially
//    }
//
//    private fun fetchProducts() {
//        viewModelScope.launch {
//            _isLoading.value = true
//            try {
//                val response = productApiService.getProducts()
//                if (response.isSuccessful) {
//                    val productList = response.body()?.map { productResponse ->
//                        productResponse.toProduct()
//                    } ?: emptyList()
//
//                    _products.value = productList
//                    _errorMessage.value = null
//                } else {
//                    _errorMessage.value = "Error: ${response.code()}"
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//                _errorMessage.value = e.localizedMessage
//                _products.value = emptyList()
//            }
//            _isLoading.value = false
//        }
//    }
//
//    fun fetchProductsByCategory(categoryId: Int) {
//        viewModelScope.launch {
//            _isLoading.value = true
//            try {
//                val response = productApiService.getProducts() // still using getProducts() API
//                if (response.isSuccessful) {
//                    val allProducts = response.body() ?: emptyList()
//                    val filteredProducts = allProducts.filter { it.category?.id == categoryId }
//                        .map { it.toProduct() }
//
//                    _products.value = filteredProducts
//                    _errorMessage.value = null
//                } else {
//                    _errorMessage.value = "Error: ${response.code()}"
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//                _errorMessage.value = e.localizedMessage
//                _products.value = emptyList()
//            }
//            _isLoading.value = false
//        }
//    }
//}
//
//// Extension function to map ProductResponse -> Product
//private fun ProductResponse.toProduct(): ProductResponse {
//    return ProductResponse(
//        id = this.id,
//        name = this.name,
//        price = "\$${this.price}", // Format price
//        imageUrl = this.image_url ?: ""
//    )
//}
