package com.example.xyzen.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
	primary = Primary,
	onPrimary = Color(0xFFFFFFFF),
	primaryContainer = PrimaryLight,
	onPrimaryContainer = Color(0xFFFFFFFF),
	secondary = Secondary,
	onSecondary = Color(0xFFFFFFFF),
	secondaryContainer = SecondaryLight,
	onSecondaryContainer = Color(0xFFFFFFFF),
	tertiary = Secondary,
	background = NeutralDark,
	surface = Neutral,
	onSurface = Color(0xFFE0E0E0),
	error = Error
)

private val LightColorScheme = lightColorScheme(
	primary = Primary,
	onPrimary = Color(0xFFFFFFFF),
	primaryContainer = PrimaryLight,
	onPrimaryContainer = Color(0xFFFFFFFF),
	secondary = Secondary,
	onSecondary = Color(0xFFFFFFFF),
	secondaryContainer = SecondaryLight,
	onSecondaryContainer = Color(0xFFFFFFFF),
	tertiary = Secondary,
	background = NeutralBackground,
	surface = SurfaceColor,
	onSurface = TextPrimary,
	error = Error
)

@Composable
fun XYZenFrontendTheme(
	darkTheme: Boolean = isSystemInDarkTheme(),
	dynamicColor: Boolean = false, // Disabled by default for consistent branding
	content: @Composable () -> Unit
) {
	val colorScheme = when {
		dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
			val context = LocalContext.current
			if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
		}
		darkTheme -> DarkColorScheme
		else -> LightColorScheme
	}

	// Set status bar color
	val view = LocalView.current
	if (!view.isInEditMode) {
		SideEffect {
			val window = (view.context as Activity).window
			window.statusBarColor = colorScheme.primary.toArgb()
			WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
		}
	}

	MaterialTheme(
		colorScheme = colorScheme,
		typography = Typography,
		content = content
	)
}