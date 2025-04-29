package com.example.compose
import android.app.Activity
import android.os.Build
import android.view.View
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.ecommerceapp.ui.theme.backgroundDark
import com.example.ecommerceapp.ui.theme.backgroundDarkHighContrast
import com.example.ecommerceapp.ui.theme.backgroundDarkMediumContrast
import com.example.ecommerceapp.ui.theme.backgroundLight
import com.example.ecommerceapp.ui.theme.backgroundLightHighContrast
import com.example.ecommerceapp.ui.theme.backgroundLightMediumContrast
import com.example.ecommerceapp.ui.theme.errorContainerDark
import com.example.ecommerceapp.ui.theme.errorContainerDarkHighContrast
import com.example.ecommerceapp.ui.theme.errorContainerDarkMediumContrast
import com.example.ecommerceapp.ui.theme.errorContainerLight
import com.example.ecommerceapp.ui.theme.errorContainerLightHighContrast
import com.example.ecommerceapp.ui.theme.errorContainerLightMediumContrast
import com.example.ecommerceapp.ui.theme.errorDark
import com.example.ecommerceapp.ui.theme.errorDarkHighContrast
import com.example.ecommerceapp.ui.theme.errorDarkMediumContrast
import com.example.ecommerceapp.ui.theme.errorLight
import com.example.ecommerceapp.ui.theme.errorLightHighContrast
import com.example.ecommerceapp.ui.theme.errorLightMediumContrast
import com.example.ecommerceapp.ui.theme.inverseOnSurfaceDark
import com.example.ecommerceapp.ui.theme.inverseOnSurfaceDarkHighContrast
import com.example.ecommerceapp.ui.theme.inverseOnSurfaceDarkMediumContrast
import com.example.ecommerceapp.ui.theme.inverseOnSurfaceLight
import com.example.ecommerceapp.ui.theme.inverseOnSurfaceLightHighContrast
import com.example.ecommerceapp.ui.theme.inverseOnSurfaceLightMediumContrast
import com.example.ecommerceapp.ui.theme.inversePrimaryDark
import com.example.ecommerceapp.ui.theme.inversePrimaryDarkHighContrast
import com.example.ecommerceapp.ui.theme.inversePrimaryDarkMediumContrast
import com.example.ecommerceapp.ui.theme.inversePrimaryLight
import com.example.ecommerceapp.ui.theme.inversePrimaryLightHighContrast
import com.example.ecommerceapp.ui.theme.inversePrimaryLightMediumContrast
import com.example.ecommerceapp.ui.theme.inverseSurfaceDark
import com.example.ecommerceapp.ui.theme.inverseSurfaceDarkHighContrast
import com.example.ecommerceapp.ui.theme.inverseSurfaceDarkMediumContrast
import com.example.ecommerceapp.ui.theme.inverseSurfaceLight
import com.example.ecommerceapp.ui.theme.inverseSurfaceLightHighContrast
import com.example.ecommerceapp.ui.theme.inverseSurfaceLightMediumContrast
import com.example.ecommerceapp.ui.theme.onBackgroundDark
import com.example.ecommerceapp.ui.theme.onBackgroundDarkHighContrast
import com.example.ecommerceapp.ui.theme.onBackgroundDarkMediumContrast
import com.example.ecommerceapp.ui.theme.onBackgroundLight
import com.example.ecommerceapp.ui.theme.onBackgroundLightHighContrast
import com.example.ecommerceapp.ui.theme.onBackgroundLightMediumContrast
import com.example.ecommerceapp.ui.theme.onErrorContainerDark
import com.example.ecommerceapp.ui.theme.onErrorContainerDarkHighContrast
import com.example.ecommerceapp.ui.theme.onErrorContainerDarkMediumContrast
import com.example.ecommerceapp.ui.theme.onErrorContainerLight
import com.example.ecommerceapp.ui.theme.onErrorContainerLightHighContrast
import com.example.ecommerceapp.ui.theme.onErrorContainerLightMediumContrast
import com.example.ecommerceapp.ui.theme.onErrorDark
import com.example.ecommerceapp.ui.theme.onErrorDarkHighContrast
import com.example.ecommerceapp.ui.theme.onErrorDarkMediumContrast
import com.example.ecommerceapp.ui.theme.onErrorLight
import com.example.ecommerceapp.ui.theme.onErrorLightHighContrast
import com.example.ecommerceapp.ui.theme.onErrorLightMediumContrast
import com.example.ecommerceapp.ui.theme.onPrimaryContainerDark
import com.example.ecommerceapp.ui.theme.onPrimaryContainerDarkHighContrast
import com.example.ecommerceapp.ui.theme.onPrimaryContainerDarkMediumContrast
import com.example.ecommerceapp.ui.theme.onPrimaryContainerLight
import com.example.ecommerceapp.ui.theme.onPrimaryContainerLightHighContrast
import com.example.ecommerceapp.ui.theme.onPrimaryContainerLightMediumContrast
import com.example.ecommerceapp.ui.theme.onPrimaryDark
import com.example.ecommerceapp.ui.theme.onPrimaryDarkHighContrast
import com.example.ecommerceapp.ui.theme.onPrimaryDarkMediumContrast
import com.example.ecommerceapp.ui.theme.onPrimaryLight
import com.example.ecommerceapp.ui.theme.onPrimaryLightHighContrast
import com.example.ecommerceapp.ui.theme.onPrimaryLightMediumContrast
import com.example.ecommerceapp.ui.theme.onSecondaryContainerDark
import com.example.ecommerceapp.ui.theme.onSecondaryContainerDarkHighContrast
import com.example.ecommerceapp.ui.theme.onSecondaryContainerDarkMediumContrast
import com.example.ecommerceapp.ui.theme.onSecondaryContainerLight
import com.example.ecommerceapp.ui.theme.onSecondaryContainerLightHighContrast
import com.example.ecommerceapp.ui.theme.onSecondaryContainerLightMediumContrast
import com.example.ecommerceapp.ui.theme.onSecondaryDark
import com.example.ecommerceapp.ui.theme.onSecondaryDarkHighContrast
import com.example.ecommerceapp.ui.theme.onSecondaryDarkMediumContrast
import com.example.ecommerceapp.ui.theme.onSecondaryLight
import com.example.ecommerceapp.ui.theme.onSecondaryLightHighContrast
import com.example.ecommerceapp.ui.theme.onSecondaryLightMediumContrast
import com.example.ecommerceapp.ui.theme.onSurfaceDark
import com.example.ecommerceapp.ui.theme.onSurfaceDarkHighContrast
import com.example.ecommerceapp.ui.theme.onSurfaceDarkMediumContrast
import com.example.ecommerceapp.ui.theme.onSurfaceLight
import com.example.ecommerceapp.ui.theme.onSurfaceLightHighContrast
import com.example.ecommerceapp.ui.theme.onSurfaceLightMediumContrast
import com.example.ecommerceapp.ui.theme.onSurfaceVariantDark
import com.example.ecommerceapp.ui.theme.onSurfaceVariantDarkHighContrast
import com.example.ecommerceapp.ui.theme.onSurfaceVariantDarkMediumContrast
import com.example.ecommerceapp.ui.theme.onSurfaceVariantLight
import com.example.ecommerceapp.ui.theme.onSurfaceVariantLightHighContrast
import com.example.ecommerceapp.ui.theme.onSurfaceVariantLightMediumContrast
import com.example.ecommerceapp.ui.theme.onTertiaryContainerDark
import com.example.ecommerceapp.ui.theme.onTertiaryContainerDarkHighContrast
import com.example.ecommerceapp.ui.theme.onTertiaryContainerDarkMediumContrast
import com.example.ecommerceapp.ui.theme.onTertiaryContainerLight
import com.example.ecommerceapp.ui.theme.onTertiaryContainerLightHighContrast
import com.example.ecommerceapp.ui.theme.onTertiaryContainerLightMediumContrast
import com.example.ecommerceapp.ui.theme.onTertiaryDark
import com.example.ecommerceapp.ui.theme.onTertiaryDarkHighContrast
import com.example.ecommerceapp.ui.theme.onTertiaryDarkMediumContrast
import com.example.ecommerceapp.ui.theme.onTertiaryLight
import com.example.ecommerceapp.ui.theme.onTertiaryLightHighContrast
import com.example.ecommerceapp.ui.theme.onTertiaryLightMediumContrast
import com.example.ecommerceapp.ui.theme.outlineDark
import com.example.ecommerceapp.ui.theme.outlineDarkHighContrast
import com.example.ecommerceapp.ui.theme.outlineDarkMediumContrast
import com.example.ecommerceapp.ui.theme.outlineLight
import com.example.ecommerceapp.ui.theme.outlineLightHighContrast
import com.example.ecommerceapp.ui.theme.outlineLightMediumContrast
import com.example.ecommerceapp.ui.theme.outlineVariantDark
import com.example.ecommerceapp.ui.theme.outlineVariantDarkHighContrast
import com.example.ecommerceapp.ui.theme.outlineVariantDarkMediumContrast
import com.example.ecommerceapp.ui.theme.outlineVariantLight
import com.example.ecommerceapp.ui.theme.outlineVariantLightHighContrast
import com.example.ecommerceapp.ui.theme.outlineVariantLightMediumContrast
import com.example.ecommerceapp.ui.theme.primaryContainerDark
import com.example.ecommerceapp.ui.theme.primaryContainerDarkHighContrast
import com.example.ecommerceapp.ui.theme.primaryContainerDarkMediumContrast
import com.example.ecommerceapp.ui.theme.primaryContainerLight
import com.example.ecommerceapp.ui.theme.primaryContainerLightHighContrast
import com.example.ecommerceapp.ui.theme.primaryContainerLightMediumContrast
import com.example.ecommerceapp.ui.theme.primaryDark
import com.example.ecommerceapp.ui.theme.primaryDarkHighContrast
import com.example.ecommerceapp.ui.theme.primaryDarkMediumContrast
import com.example.ecommerceapp.ui.theme.primaryLight
import com.example.ecommerceapp.ui.theme.primaryLightHighContrast
import com.example.ecommerceapp.ui.theme.primaryLightMediumContrast
import com.example.ecommerceapp.ui.theme.scrimDark
import com.example.ecommerceapp.ui.theme.scrimDarkHighContrast
import com.example.ecommerceapp.ui.theme.scrimDarkMediumContrast
import com.example.ecommerceapp.ui.theme.scrimLight
import com.example.ecommerceapp.ui.theme.scrimLightHighContrast
import com.example.ecommerceapp.ui.theme.scrimLightMediumContrast
import com.example.ecommerceapp.ui.theme.secondaryContainerDark
import com.example.ecommerceapp.ui.theme.secondaryContainerDarkHighContrast
import com.example.ecommerceapp.ui.theme.secondaryContainerDarkMediumContrast
import com.example.ecommerceapp.ui.theme.secondaryContainerLight
import com.example.ecommerceapp.ui.theme.secondaryContainerLightHighContrast
import com.example.ecommerceapp.ui.theme.secondaryContainerLightMediumContrast
import com.example.ecommerceapp.ui.theme.secondaryDark
import com.example.ecommerceapp.ui.theme.secondaryDarkHighContrast
import com.example.ecommerceapp.ui.theme.secondaryDarkMediumContrast
import com.example.ecommerceapp.ui.theme.secondaryLight
import com.example.ecommerceapp.ui.theme.secondaryLightHighContrast
import com.example.ecommerceapp.ui.theme.secondaryLightMediumContrast
import com.example.ecommerceapp.ui.theme.surfaceBrightDark
import com.example.ecommerceapp.ui.theme.surfaceBrightDarkHighContrast
import com.example.ecommerceapp.ui.theme.surfaceBrightDarkMediumContrast
import com.example.ecommerceapp.ui.theme.surfaceBrightLight
import com.example.ecommerceapp.ui.theme.surfaceBrightLightHighContrast
import com.example.ecommerceapp.ui.theme.surfaceBrightLightMediumContrast
import com.example.ecommerceapp.ui.theme.surfaceContainerDark
import com.example.ecommerceapp.ui.theme.surfaceContainerDarkHighContrast
import com.example.ecommerceapp.ui.theme.surfaceContainerDarkMediumContrast
import com.example.ecommerceapp.ui.theme.surfaceContainerHighDark
import com.example.ecommerceapp.ui.theme.surfaceContainerHighDarkHighContrast
import com.example.ecommerceapp.ui.theme.surfaceContainerHighDarkMediumContrast
import com.example.ecommerceapp.ui.theme.surfaceContainerHighLight
import com.example.ecommerceapp.ui.theme.surfaceContainerHighLightHighContrast
import com.example.ecommerceapp.ui.theme.surfaceContainerHighLightMediumContrast
import com.example.ecommerceapp.ui.theme.surfaceContainerHighestDark
import com.example.ecommerceapp.ui.theme.surfaceContainerHighestDarkHighContrast
import com.example.ecommerceapp.ui.theme.surfaceContainerHighestDarkMediumContrast
import com.example.ecommerceapp.ui.theme.surfaceContainerHighestLight
import com.example.ecommerceapp.ui.theme.surfaceContainerHighestLightHighContrast
import com.example.ecommerceapp.ui.theme.surfaceContainerHighestLightMediumContrast
import com.example.ecommerceapp.ui.theme.surfaceContainerLight
import com.example.ecommerceapp.ui.theme.surfaceContainerLightHighContrast
import com.example.ecommerceapp.ui.theme.surfaceContainerLightMediumContrast
import com.example.ecommerceapp.ui.theme.surfaceContainerLowDark
import com.example.ecommerceapp.ui.theme.surfaceContainerLowDarkHighContrast
import com.example.ecommerceapp.ui.theme.surfaceContainerLowDarkMediumContrast
import com.example.ecommerceapp.ui.theme.surfaceContainerLowLight
import com.example.ecommerceapp.ui.theme.surfaceContainerLowLightHighContrast
import com.example.ecommerceapp.ui.theme.surfaceContainerLowLightMediumContrast
import com.example.ecommerceapp.ui.theme.surfaceContainerLowestDark
import com.example.ecommerceapp.ui.theme.surfaceContainerLowestDarkHighContrast
import com.example.ecommerceapp.ui.theme.surfaceContainerLowestDarkMediumContrast
import com.example.ecommerceapp.ui.theme.surfaceContainerLowestLight
import com.example.ecommerceapp.ui.theme.surfaceContainerLowestLightHighContrast
import com.example.ecommerceapp.ui.theme.surfaceContainerLowestLightMediumContrast
import com.example.ecommerceapp.ui.theme.surfaceDark
import com.example.ecommerceapp.ui.theme.surfaceDarkHighContrast
import com.example.ecommerceapp.ui.theme.surfaceDarkMediumContrast
import com.example.ecommerceapp.ui.theme.surfaceDimDark
import com.example.ecommerceapp.ui.theme.surfaceDimDarkHighContrast
import com.example.ecommerceapp.ui.theme.surfaceDimDarkMediumContrast
import com.example.ecommerceapp.ui.theme.surfaceDimLight
import com.example.ecommerceapp.ui.theme.surfaceDimLightHighContrast
import com.example.ecommerceapp.ui.theme.surfaceDimLightMediumContrast
import com.example.ecommerceapp.ui.theme.surfaceLight
import com.example.ecommerceapp.ui.theme.surfaceLightHighContrast
import com.example.ecommerceapp.ui.theme.surfaceLightMediumContrast
import com.example.ecommerceapp.ui.theme.surfaceVariantDark
import com.example.ecommerceapp.ui.theme.surfaceVariantDarkHighContrast
import com.example.ecommerceapp.ui.theme.surfaceVariantDarkMediumContrast
import com.example.ecommerceapp.ui.theme.surfaceVariantLight
import com.example.ecommerceapp.ui.theme.surfaceVariantLightHighContrast
import com.example.ecommerceapp.ui.theme.surfaceVariantLightMediumContrast
import com.example.ecommerceapp.ui.theme.tertiaryContainerDark
import com.example.ecommerceapp.ui.theme.tertiaryContainerDarkHighContrast
import com.example.ecommerceapp.ui.theme.tertiaryContainerDarkMediumContrast
import com.example.ecommerceapp.ui.theme.tertiaryContainerLight
import com.example.ecommerceapp.ui.theme.tertiaryContainerLightHighContrast
import com.example.ecommerceapp.ui.theme.tertiaryContainerLightMediumContrast
import com.example.ecommerceapp.ui.theme.tertiaryDark
import com.example.ecommerceapp.ui.theme.tertiaryDarkHighContrast
import com.example.ecommerceapp.ui.theme.tertiaryDarkMediumContrast
import com.example.ecommerceapp.ui.theme.tertiaryLight
import com.example.ecommerceapp.ui.theme.tertiaryLightHighContrast
import com.example.ecommerceapp.ui.theme.tertiaryLightMediumContrast
import com.example.ui.theme.AppTypography

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    error = errorLight,
    onError = onErrorLight,
    errorContainer = errorContainerLight,
    onErrorContainer = onErrorContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    error = errorDark,
    onError = onErrorDark,
    errorContainer = errorContainerDark,
    onErrorContainer = onErrorContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)

private val mediumContrastLightColorScheme = lightColorScheme(
    primary = primaryLightMediumContrast,
    onPrimary = onPrimaryLightMediumContrast,
    primaryContainer = primaryContainerLightMediumContrast,
    onPrimaryContainer = onPrimaryContainerLightMediumContrast,
    secondary = secondaryLightMediumContrast,
    onSecondary = onSecondaryLightMediumContrast,
    secondaryContainer = secondaryContainerLightMediumContrast,
    onSecondaryContainer = onSecondaryContainerLightMediumContrast,
    tertiary = tertiaryLightMediumContrast,
    onTertiary = onTertiaryLightMediumContrast,
    tertiaryContainer = tertiaryContainerLightMediumContrast,
    onTertiaryContainer = onTertiaryContainerLightMediumContrast,
    error = errorLightMediumContrast,
    onError = onErrorLightMediumContrast,
    errorContainer = errorContainerLightMediumContrast,
    onErrorContainer = onErrorContainerLightMediumContrast,
    background = backgroundLightMediumContrast,
    onBackground = onBackgroundLightMediumContrast,
    surface = surfaceLightMediumContrast,
    onSurface = onSurfaceLightMediumContrast,
    surfaceVariant = surfaceVariantLightMediumContrast,
    onSurfaceVariant = onSurfaceVariantLightMediumContrast,
    outline = outlineLightMediumContrast,
    outlineVariant = outlineVariantLightMediumContrast,
    scrim = scrimLightMediumContrast,
    inverseSurface = inverseSurfaceLightMediumContrast,
    inverseOnSurface = inverseOnSurfaceLightMediumContrast,
    inversePrimary = inversePrimaryLightMediumContrast,
    surfaceDim = surfaceDimLightMediumContrast,
    surfaceBright = surfaceBrightLightMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestLightMediumContrast,
    surfaceContainerLow = surfaceContainerLowLightMediumContrast,
    surfaceContainer = surfaceContainerLightMediumContrast,
    surfaceContainerHigh = surfaceContainerHighLightMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestLightMediumContrast,
)

private val highContrastLightColorScheme = lightColorScheme(
    primary = primaryLightHighContrast,
    onPrimary = onPrimaryLightHighContrast,
    primaryContainer = primaryContainerLightHighContrast,
    onPrimaryContainer = onPrimaryContainerLightHighContrast,
    secondary = secondaryLightHighContrast,
    onSecondary = onSecondaryLightHighContrast,
    secondaryContainer = secondaryContainerLightHighContrast,
    onSecondaryContainer = onSecondaryContainerLightHighContrast,
    tertiary = tertiaryLightHighContrast,
    onTertiary = onTertiaryLightHighContrast,
    tertiaryContainer = tertiaryContainerLightHighContrast,
    onTertiaryContainer = onTertiaryContainerLightHighContrast,
    error = errorLightHighContrast,
    onError = onErrorLightHighContrast,
    errorContainer = errorContainerLightHighContrast,
    onErrorContainer = onErrorContainerLightHighContrast,
    background = backgroundLightHighContrast,
    onBackground = onBackgroundLightHighContrast,
    surface = surfaceLightHighContrast,
    onSurface = onSurfaceLightHighContrast,
    surfaceVariant = surfaceVariantLightHighContrast,
    onSurfaceVariant = onSurfaceVariantLightHighContrast,
    outline = outlineLightHighContrast,
    outlineVariant = outlineVariantLightHighContrast,
    scrim = scrimLightHighContrast,
    inverseSurface = inverseSurfaceLightHighContrast,
    inverseOnSurface = inverseOnSurfaceLightHighContrast,
    inversePrimary = inversePrimaryLightHighContrast,
    surfaceDim = surfaceDimLightHighContrast,
    surfaceBright = surfaceBrightLightHighContrast,
    surfaceContainerLowest = surfaceContainerLowestLightHighContrast,
    surfaceContainerLow = surfaceContainerLowLightHighContrast,
    surfaceContainer = surfaceContainerLightHighContrast,
    surfaceContainerHigh = surfaceContainerHighLightHighContrast,
    surfaceContainerHighest = surfaceContainerHighestLightHighContrast,
)

private val mediumContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkMediumContrast,
    onPrimary = onPrimaryDarkMediumContrast,
    primaryContainer = primaryContainerDarkMediumContrast,
    onPrimaryContainer = onPrimaryContainerDarkMediumContrast,
    secondary = secondaryDarkMediumContrast,
    onSecondary = onSecondaryDarkMediumContrast,
    secondaryContainer = secondaryContainerDarkMediumContrast,
    onSecondaryContainer = onSecondaryContainerDarkMediumContrast,
    tertiary = tertiaryDarkMediumContrast,
    onTertiary = onTertiaryDarkMediumContrast,
    tertiaryContainer = tertiaryContainerDarkMediumContrast,
    onTertiaryContainer = onTertiaryContainerDarkMediumContrast,
    error = errorDarkMediumContrast,
    onError = onErrorDarkMediumContrast,
    errorContainer = errorContainerDarkMediumContrast,
    onErrorContainer = onErrorContainerDarkMediumContrast,
    background = backgroundDarkMediumContrast,
    onBackground = onBackgroundDarkMediumContrast,
    surface = surfaceDarkMediumContrast,
    onSurface = onSurfaceDarkMediumContrast,
    surfaceVariant = surfaceVariantDarkMediumContrast,
    onSurfaceVariant = onSurfaceVariantDarkMediumContrast,
    outline = outlineDarkMediumContrast,
    outlineVariant = outlineVariantDarkMediumContrast,
    scrim = scrimDarkMediumContrast,
    inverseSurface = inverseSurfaceDarkMediumContrast,
    inverseOnSurface = inverseOnSurfaceDarkMediumContrast,
    inversePrimary = inversePrimaryDarkMediumContrast,
    surfaceDim = surfaceDimDarkMediumContrast,
    surfaceBright = surfaceBrightDarkMediumContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkMediumContrast,
    surfaceContainerLow = surfaceContainerLowDarkMediumContrast,
    surfaceContainer = surfaceContainerDarkMediumContrast,
    surfaceContainerHigh = surfaceContainerHighDarkMediumContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkMediumContrast,
)

private val highContrastDarkColorScheme = darkColorScheme(
    primary = primaryDarkHighContrast,
    onPrimary = onPrimaryDarkHighContrast,
    primaryContainer = primaryContainerDarkHighContrast,
    onPrimaryContainer = onPrimaryContainerDarkHighContrast,
    secondary = secondaryDarkHighContrast,
    onSecondary = onSecondaryDarkHighContrast,
    secondaryContainer = secondaryContainerDarkHighContrast,
    onSecondaryContainer = onSecondaryContainerDarkHighContrast,
    tertiary = tertiaryDarkHighContrast,
    onTertiary = onTertiaryDarkHighContrast,
    tertiaryContainer = tertiaryContainerDarkHighContrast,
    onTertiaryContainer = onTertiaryContainerDarkHighContrast,
    error = errorDarkHighContrast,
    onError = onErrorDarkHighContrast,
    errorContainer = errorContainerDarkHighContrast,
    onErrorContainer = onErrorContainerDarkHighContrast,
    background = backgroundDarkHighContrast,
    onBackground = onBackgroundDarkHighContrast,
    surface = surfaceDarkHighContrast,
    onSurface = onSurfaceDarkHighContrast,
    surfaceVariant = surfaceVariantDarkHighContrast,
    onSurfaceVariant = onSurfaceVariantDarkHighContrast,
    outline = outlineDarkHighContrast,
    outlineVariant = outlineVariantDarkHighContrast,
    scrim = scrimDarkHighContrast,
    inverseSurface = inverseSurfaceDarkHighContrast,
    inverseOnSurface = inverseOnSurfaceDarkHighContrast,
    inversePrimary = inversePrimaryDarkHighContrast,
    surfaceDim = surfaceDimDarkHighContrast,
    surfaceBright = surfaceBrightDarkHighContrast,
    surfaceContainerLowest = surfaceContainerLowestDarkHighContrast,
    surfaceContainerLow = surfaceContainerLowDarkHighContrast,
    surfaceContainer = surfaceContainerDarkHighContrast,
    surfaceContainerHigh = surfaceContainerHighDarkHighContrast,
    surfaceContainerHighest = surfaceContainerHighestDarkHighContrast,
)

@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)



@Composable
fun EcommerceAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+, turned off for training purposes
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkScheme
        else -> lightScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            setUpEdgeToEdge(view, darkTheme)
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}

/**
 * Sets up edge-to-edge for the window of this [view]. The system icon colors are set to either
 * light or dark depending on whether the [darkTheme] is enabled or not.
 */
private fun setUpEdgeToEdge(view: View, darkTheme: Boolean) {
    val window = (view.context as Activity).window
    WindowCompat.setDecorFitsSystemWindows(window, false)
    window.statusBarColor = Color.Transparent.toArgb()
    val navigationBarColor = when {
        Build.VERSION.SDK_INT >= 29 -> Color.Transparent.toArgb()
        Build.VERSION.SDK_INT >= 26 -> Color(0xFF, 0xFF, 0xFF, 0x63).toArgb()
        // Min sdk version for this app is 24, this block is for SDK versions 24 and 25
        else -> Color(0x00,0x00, 0x00, 0x50).toArgb()
    }
    window.navigationBarColor = navigationBarColor
    val controller = WindowCompat.getInsetsController(window, view)
    controller.isAppearanceLightStatusBars = !darkTheme
    controller.isAppearanceLightNavigationBars = !darkTheme
}

