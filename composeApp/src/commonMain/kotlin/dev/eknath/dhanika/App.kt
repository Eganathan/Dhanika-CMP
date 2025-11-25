package dev.eknath.dhanika

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import dev.eknath.dhanika.ui.nav.AppNav
import dev.eknath.dhanika.util.dhanikaCustomTypography
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme(typography = dhanikaCustomTypography()) {
        AppNav()
    }
}