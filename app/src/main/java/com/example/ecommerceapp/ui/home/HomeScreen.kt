package com.example.ecommerceapp.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.compose.EcommerceAppTheme
import com.example.ecommerceapp.R
import com.example.ecommerceapp.data.models.ProductResponse
import com.example.ecommerceapp.ui.home.viewmodel.CategoryViewModel
import com.example.ecommerceapp.ui.home.viewmodel.ProductViewModel

@Composable
fun HomeScreen(
    productViewModel: ProductViewModel = viewModel(),
    categoryViewModel: CategoryViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val products by productViewModel.products.collectAsState()
    val productsByCategory by productViewModel.productsByCategory.collectAsState()

    LaunchedEffect(Unit) {
        productViewModel.fetchProducts()
    }

    LazyColumn {
        item {
            HeaderSection()
        }

        // " Bunny Pillows" Section (for category id = 1)
        item {
            Spacer(modifier = modifier.height(16.dp))
            Text(
                "Bunny Pillows",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            val pillowProducts = products.filter { it.category_id == 1 } // 1 = Pillow category id
            ProductGridSection(
                products = pillowProducts,
                onProductClick = {}
            )
        }

        // "Master Pillows" Section (for category id = 2)
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Master Pillows",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            val bolsterProducts = products.filter { it.category_id == 2 } // 2 = Bolster Pillow category id
            ProductGridSection(
                products = bolsterProducts,
                onProductClick = {}
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Good Pillows For Your Bed",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
            NewSectionCard(
                title = "Join the Green Movement",
                body = "Discover eco-friendly home essentials now",
                imageRes = R.drawable.banner,
                onClick = { println("Card clicked!") }
            )
            NewSectionCard(
                title = "Eco-Friendly Bedding",
                body = "Sleep better with nature-friendly products",
                imageRes = R.drawable.banner,
                onClick = { println("Card clicked!") }
            )
        }
    }
}

@Composable
fun HeaderSection(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth()) {
        // Optional image placeholder
        Image(
            painter = painterResource(id = R.drawable.banner),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Planta - shining your", style = MaterialTheme.typography.headlineLarge, color = MaterialTheme.colorScheme.background)
            Text("little space", style = MaterialTheme.typography.headlineMedium, color = MaterialTheme.colorScheme.background)
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(onClick = { /* TODO: handle navigation */ }) {
                Text(
                    text = "See New Arrivals →",
                    //color = MaterialTheme.colorScheme.inversePrimary, // green tone
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        IconButton(
            onClick = { /* TODO: cart action */ },
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background, shape = CircleShape)
        ) {
            Icon(Icons.Default.ShoppingCart, contentDescription = "Cart")
        }
    }
}

@Composable
fun ProductGridSection(
    products: List<ProductResponse>,
    onProductClick: (ProductResponse) -> Unit,
    modifier: Modifier = Modifier
) {
    //val rows = (products.take(6).size + 1) / 2  // 2 items per row

    Column(modifier = modifier.fillMaxWidth()) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .height(530.dp)
                //.height((rows * 220).dp)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            userScrollEnabled = false // no inner scrolling
        ) {
            items(products.take(6)) { product ->
                ProductItemCard(
                    product = product,
                    onClick = { onProductClick(product) }
                )
            }
        }

        TextButton(
            onClick = { /* TODO: Handle See More */ },
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 16.dp, top = 8.dp)
        ) {
            Text(
                text = "See More",
                style = MaterialTheme.typography.bodyLarge.copy(
                    textDecoration = TextDecoration.Underline
                )
            )
        }
    }
}

@Composable
fun ProductItemCard(
    product: ProductResponse,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White // Set card background to white
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            //modifier = Modifier.padding(12.dp)
        ){
            AsyncImage(
                model = product.image_url,
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            )
            Text(
                text = product.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 12.dp)
            )
            Text(
                text = product.price.toString(),
                color = MaterialTheme.colorScheme.tertiary,
                modifier = Modifier.padding(start = 12.dp)
            )
        }
    }
}

@Composable
fun NewSectionCard(
    title: String,
    body: String,
    imageRes: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min) // Makes image fill vertical space
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = body,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .width(100.dp) // Adjust as needed
                    .height(150.dp)
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun HomeScreenGuestPreview() {
    EcommerceAppTheme {
        HomeScreen()
    }
}