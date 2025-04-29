package com.example.ecommerceapp.ui.auth

import AuthViewModel
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.R
import com.example.ecommerceapp.ui.auth.components.ButtonLayout
import com.example.ecommerceapp.ui.auth.components.InputLayout

@Composable
fun ForgetPasswordScreen(
    authViewModel: AuthViewModel,
    onResetLinkSent: () -> Unit,
    onNavigateToLogin: () -> Unit,
    modifier: Modifier = Modifier
){
    val uiState by authViewModel.uiState.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.padding_column))
    ) {
        Text(
            text = stringResource(R.string.forget_password),
            fontSize = dimensionResource(R.dimen.welcome_size).value.sp,
            lineHeight = dimensionResource(R.dimen.welcom_height).value.sp,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = modifier.padding(dimensionResource(R.dimen.padding_column))
        )
        Spacer(modifier = Modifier.height(12.dp))
        InputLayout(
            label = R.string.email,
            leadingIcon = R.drawable.email,
            value = uiState.email,
            onValueChange = {authViewModel.onEmailChange(it)},
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
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.error)) { // Orange color
                    append("* ")
                }
                append("We will send you a message to set or reset your new password")
            },
            fontSize = 12.sp,
            modifier = Modifier
                .padding(
                    start = dimensionResource(R.dimen.padding_column),
                    top = dimensionResource(R.dimen.padding_column),
                    bottom = dimensionResource(R.dimen.padding_column),
                    end = 108.dp
                )
        )
        Spacer(modifier = Modifier.height(16.dp))
        ButtonLayout(
            text = R.string.submit,
            onClick = { onResetLinkSent() },
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
        Spacer(modifier = Modifier.height(24.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Oh!!!, I remembered it. Go to",
                fontSize = 15.sp,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
            TextButton(
                onClick = { onNavigateToLogin() }
            ) {
                Text(
                    text = "Login",
                    fontSize = 15.sp
                )
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewForgetPasswordScreen(){
//    MaterialTheme {
//        ForgetPasswordScreen(
//            authViewModel = AuthViewModel(),
//            onNavigateToLogin = {},
//            onResetLinkSent = {}
//        )
//    }
//}