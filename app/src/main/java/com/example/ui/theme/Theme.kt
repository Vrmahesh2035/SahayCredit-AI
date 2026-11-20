package com.example.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

val EmeraldPrimary = Color(0xFF047857)
val EmeraldDeep = Color(0xFF065F46)
val EmeraldDarkest = Color(0xFF022C22)
val EmeraldSoft = Color(0xFFECFDF5)
val EmeraldContainer = Color(0xFFD1FAE5)
val EmeraldAccent = Color(0xFF6EE7B7)

val AmberWarm = Color(0xFFB45309)
val AmberSoft = Color(0xFFFFFBEB)
val AmberBorder = Color(0xFFFDE68A)

val RoseAlert = Color(0xFFBE123C)
val RoseSoft = Color(0xFFFFF1F2)
val RoseBorder = Color(0xFFFECDD3)

val SlateBackground = Color(0xFFF8FAFC)
val SlateSurface = Color(0xFFFFFFFF)
val SlateMuted = Color(0xFFF1F5F9)
val SlateBorder = Color(0xFFE2E8F0)
val SlateTextPrimary = Color(0xFF0F172A)
val SlateTextSecondary = Color(0xFF475569)
val SlateTextMuted = Color(0xFF64748B)

private val SahayLightColorScheme = lightColorScheme(
    primary = EmeraldPrimary,
    onPrimary = Color.White,
    primaryContainer = EmeraldContainer,
    onPrimaryContainer = EmeraldDarkest,
    secondary = EmeraldDeep,
    onSecondary = Color.White,
    secondaryContainer = EmeraldSoft,
    onSecondaryContainer = EmeraldDeep,
    tertiary = AmberWarm,
    onTertiary = Color.White,
    tertiaryContainer = AmberSoft,
    onTertiaryContainer = Color(0xFF78350F),
    error = RoseAlert,
    onError = Color.White,
    errorContainer = RoseSoft,
    onErrorContainer = Color(0xFF881337),
    background = SlateBackground,
    onBackground = SlateTextPrimary,
    surface = SlateSurface,
    onSurface = SlateTextPrimary,
    surfaceVariant = SlateMuted,
    onSurfaceVariant = SlateTextSecondary,
    outline = SlateBorder,
    outlineVariant = Color(0xFFCBD5E1)
)

val SahayTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 26.sp,
        lineHeight = 32.sp,
        letterSpacing = (-0.5).sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = (-0.25).sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
        lineHeight = 21.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        lineHeight = 22.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp,
        lineHeight = 19.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        lineHeight = 18.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.SansSerif,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 15.sp
    )
)

val TabularMoneyStyle = TextStyle(
    fontFamily = FontFamily.Monospace,
    fontWeight = FontWeight.Bold,
    fontFeatureSettings = "tnum"
)

val SahayShapes = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(14.dp),
    large = RoundedCornerShape(20.dp)
)

@Composable
fun SahayCreditTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = SahayLightColorScheme,
        typography = SahayTypography,
        shapes = SahayShapes,
        content = content
    )
}
