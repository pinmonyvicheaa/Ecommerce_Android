package com.example.ecommerceapp.ui.auth

import AuthViewModel
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.EcommerceAppTheme
import com.example.ecommerceapp.R
import com.example.ecommerceapp.ui.auth.components.ButtonLayout
import com.example.ecommerceapp.ui.auth.components.InputLayout
import com.example.ecommerceapp.ui.auth.components.SocialButtonLayout

@Composable
fun LoginScreen(
    authViewModel: AuthViewModel,
    onNavigateToSignup: () -> Unit,
    onNavigateToForget: () -> Unit,
    onLoginSuccess: () -> Unit,
    modifier: Modifier = Modifier
) {

    val uiState by authViewModel.uiState.collectAsState()
    val isLoading = uiState.isLoading
    val errorMessage = uiState.errorMessage

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_column))
    ) {
        Text(
            text = stringResource(R.string.welcome),
            fontSize = dimensionResource(R.dimen.welcome_size).value.sp,
            lineHeight = dimensionResource(R.dimen.welcom_height).value.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = modifier.padding(dimensionResource(R.dimen.padding_column))
        )
        Spacer(modifier = Modifier.height(12.dp))

        InputLayout(
            label = R.string.username_email,
            leadingIcon = R.drawable.user,
            value = uiState.email,
            onValueChange = { authViewModel.onEmailChange(it) },
            modifier = Modifier
                .width(370.dp)
                .height(95.dp)
                .padding(
                    top = dimensionResource(R.dimen.padding_column),
                    start = dimensionResource(R.dimen.padding_column),
                    end = dimensionResource(R.dimen.padding_column)
                )
        )

        Spacer(modifier = Modifier.height(6.dp))

        InputLayout(
            label = R.string.password,
            leadingIcon = R.drawable.password,
            trailingIcon = R.drawable.eye,
            value = uiState.password,
            onValueChange = { authViewModel.onPasswordChange(it) },
            onTrailingIconClick = { authViewModel.togglePasswordVisibility() },
            isTextVisible = uiState.isPasswordVisible,
            modifier = Modifier
                .width(370.dp)
                .height(95.dp)
                .padding(
                    top = dimensionResource(R.dimen.padding_column),
                    start = dimensionResource(R.dimen.padding_column),
                    end = dimensionResource(R.dimen.padding_column)
                )
        )

        Text(
            text = "Forgot Password?",
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = dimensionResource(R.dimen.padding_column))
                .clickable {
                    onNavigateToForget()
                }
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Display error message if present
        if (errorMessage.isNotBlank()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        ButtonLayout(
            text = R.string.login,
            onClick = {
                if (authViewModel.validateInputs()) {
                    authViewModel.login(
                        onSuccess = {
                            onLoginSuccess()
                        },
                        onError = { errorMsg ->
                            authViewModel.setErrorMessage(errorMsg) // Set error message for display
                        }
                    )
                } else {
                    authViewModel.setErrorMessage("Please enter a valid email and password.") // Validation error
                }
            },
            modifier = Modifier
                .width(370.dp)
                .height(75.dp)
                .align(Alignment.CenterHorizontally)
                .padding(
                    top = dimensionResource(R.dimen.padding_column),
                    start = dimensionResource(R.dimen.padding_column),
                    end = dimensionResource(R.dimen.padding_column)
                )
        )

        // Show loading spinner if login is in progress
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        Spacer(modifier = Modifier.height(44.dp))

        Box {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "- OR Continue with -",
                    fontSize = 15.sp
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    SocialButtonLayout(
                        image = R.drawable.google,
                        onClick = {},
                        modifier = Modifier.padding(8.dp)
                    )
                    SocialButtonLayout(
                        image = R.drawable.apple,
                        onClick = {},
                        modifier = Modifier.padding(8.dp)
                    )
                    SocialButtonLayout(
                        image = R.drawable.facebook,
                        onClick = {},
                        modifier = Modifier.padding(8.dp)
                    )
                }
                Row {
                    Text(
                        text = "Create An Account",
                        fontSize = 15.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    TextButton(
                        onClick = { onNavigateToSignup() }
                    ) {
                        Text(
                            text = "Sign up",
                            fontSize = 15.sp
                        )
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewSignInScreen() {
//    EcommerceAppTheme {
//        LoginScreen(
//            authViewModel = AuthViewModel(),
//            onNavigateToSignup = {},
//            onNavigateToForget = {},
//            onLoginSuccess = {}
//        )
//    }
//}
