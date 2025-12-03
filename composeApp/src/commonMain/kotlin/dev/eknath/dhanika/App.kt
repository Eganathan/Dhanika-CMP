package dev.eknath.dhanika

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import dev.eknath.dhanika.resources.Background
import dev.eknath.dhanika.resources.Primary
import dev.eknath.dhanika.resources.Secondary
import dev.eknath.dhanika.resources.TextPrimary
import dev.eknath.dhanika.resources.TextSecondary
import dev.eknath.dhanika.ui.nav.AppNav
import dev.eknath.dhanika.util.dhanikaCustomTypography
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme(
        colorScheme = getDhanikaColorScheme(),
        typography = dhanikaCustomTypography()
    ) {
        AppNav()
    }
}

@Composable
private fun getDhanikaColorScheme(): ColorScheme {
    return MaterialTheme.colorScheme.copy(
        primary = Primary,
        onPrimary = TextPrimary,
        secondary = Secondary,
        onSecondary = TextSecondary,
        background = Background,
        onBackground = TextPrimary
    )
}