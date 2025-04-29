package com.example.ecommerceapp.ui.auth.components

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun InputLayout(
    @StringRes label: Int,
    @DrawableRes leadingIcon: Int,
    @DrawableRes trailingIcon: Int? = null,
    value: String,
    onValueChange: (String) -> Unit,
    onTrailingIconClick: () -> Unit = {},
    isTextVisible: Boolean = true,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
){
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        visualTransformation = if (isTextVisible) VisualTransformation.None else PasswordVisualTransformation(),
        leadingIcon = {
            Icon(
                painter = painterResource(id = leadingIcon),
                null,
                modifier = Modifier.size(24.dp)
            )
        },
        trailingIcon = {
            IconButton(onClick = onTrailingIconClick) {
                trailingIcon?.let { painterResource(id = it) }?.let {
                    Icon(
                        painter = it,
                        contentDescription = "Toggle Password",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        },
        label = { Text(stringResource(label), textAlign = TextAlign.Center) },
        singleLine = true,
        modifier = modifier
    )
}

@Composable
fun ButtonLayout(
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    OutlinedButton(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        //colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.tertiaryContainer),
        modifier = modifier
    ) {
        Text(
            text = stringResource(text),
            fontSize = 25.sp,
        )
    }
}

@Composable
fun SocialButtonLayout(
    @DrawableRes image: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
){
    OutlinedIconButton(
        onClick = onClick,
        modifier = modifier.size(58.dp)
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            modifier = Modifier.size(34.dp)
        )
    }
}
