package com.example.ecommerceapp.ui.Onboarding

import androidx.activity.OnBackPressedCallback
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ecommerceapp.R

@Composable
fun OnBoardingLayout(
    @DrawableRes image: Int,
    @StringRes title: Int,
    @StringRes description: Int,
    viewModel: OnBoardingViewModel,
    navController: NavHostController,
    modifier: Modifier = Modifier
){
    val currentPage by viewModel.currentPage.observeAsState(1)  // Observe state properly
    val backDispatcher = LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher

    // Handle system back press
    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (currentPage > 1) {
                    viewModel.previousPage(navController)  // Navigate back
                } else {
                    isEnabled = false  // Let the system handle back (exit app)
                }
            }
        }
    }

    DisposableEffect(backDispatcher) {
        backDispatcher?.addCallback(backCallback)
        onDispose { backCallback.remove() }
    }
    
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_column))
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "$currentPage / 3",
                fontSize = dimensionResource(R.dimen.text_button_size).value.sp
            )
            Text(
                text = "Skip",
                fontSize = dimensionResource(R.dimen.text_button_size).value.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable {
                    viewModel.skip(navController)
                }
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier.size(dimensionResource(R.dimen.image_size))
            )
            Text(
                text = stringResource(title),
                fontSize = dimensionResource(R.dimen.title_size).value.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(description),
                fontSize = 15.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                lineHeight = 20.sp,
                modifier = Modifier.alpha(0.8f)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if(currentPage > 1){
                TextButton(
                    onClick = { viewModel.previousPage(navController) }
                ) {
                    Text(
                        text = "Previous",
                        fontSize = dimensionResource(R.dimen.text_button_size).value.sp,
                        color = MaterialTheme.colorScheme.outlineVariant,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }else{
                Text("")
            }
            if(currentPage < 3){
                TextButton(
                    onClick = { viewModel.nextPage(navController) }
                ) {
                    Text(
                        text = "Next",
                        fontSize = dimensionResource(R.dimen.text_button_size).value.sp,
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }else{
                TextButton(
                    onClick = { viewModel.nextPage(navController) }
                ) {
                    Text(
                        text = "Get Started",
                        fontSize = dimensionResource(R.dimen.text_button_size).value.sp,
                        color = MaterialTheme.colorScheme.error,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}