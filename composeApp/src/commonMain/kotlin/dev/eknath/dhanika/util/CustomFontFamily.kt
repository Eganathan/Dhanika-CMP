package dev.eknath.dhanika.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import dhanika.composeapp.generated.resources.Res
import dhanika.composeapp.generated.resources.ubuntu_sans_Italic
import dhanika.composeapp.generated.resources.ubuntu_sans__regular
import dhanika.composeapp.generated.resources.ubuntu_sans_bold
import dhanika.composeapp.generated.resources.ubuntu_sans_light
import dhanika.composeapp.generated.resources.ubuntu_sans_medium
import dhanika.composeapp.generated.resources.ubuntu_sans_semi_bold
import org.jetbrains.compose.resources.Font

@Composable
internal fun dhanikaCustomTypography(): Typography {

    val ubuntuFonts = FontFamily(
        Font(Res.font.ubuntu_sans_light, FontWeight.Light, style = FontStyle.Normal),
        Font(Res.font.ubuntu_sans_medium, FontWeight.Medium, style = FontStyle.Normal),
        Font(Res.font.ubuntu_sans__regular, FontWeight.Normal, style = FontStyle.Normal),
        Font(Res.font.ubuntu_sans_semi_bold, FontWeight.SemiBold, style = FontStyle.Normal),
        Font(Res.font.ubuntu_sans_bold, FontWeight.Bold, style = FontStyle.Normal),
        Font(Res.font.ubuntu_sans_Italic, FontWeight.Normal, style = FontStyle.Italic)
    )
    return with(MaterialTheme.typography) {
        copy(
            displayLarge = TextStyle(fontFamily = ubuntuFonts),
            displayMedium = TextStyle(fontFamily = ubuntuFonts),
            displaySmall = TextStyle(fontFamily = ubuntuFonts),

            headlineLarge = TextStyle(fontFamily = ubuntuFonts),
            headlineMedium = TextStyle(fontFamily = ubuntuFonts),
            headlineSmall = TextStyle(fontFamily = ubuntuFonts),

            titleLarge = TextStyle(fontFamily = ubuntuFonts),
            titleMedium = TextStyle(fontFamily = ubuntuFonts),
            titleSmall = TextStyle(fontFamily = ubuntuFonts),

            bodyLarge = TextStyle(fontFamily = ubuntuFonts),
            bodyMedium = TextStyle(fontFamily = ubuntuFonts),
            bodySmall = TextStyle(fontFamily = ubuntuFonts),

            labelLarge = TextStyle(fontFamily = ubuntuFonts),
            labelMedium = TextStyle(fontFamily = ubuntuFonts),
            labelSmall = TextStyle(fontFamily = ubuntuFonts),
        )
    }
}
