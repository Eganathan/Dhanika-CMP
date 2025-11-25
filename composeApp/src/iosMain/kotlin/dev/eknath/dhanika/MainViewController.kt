package dev.eknath.dhanika

import androidx.compose.ui.window.ComposeUIViewController
import dev.eknath.dhanika.di.initKoin

fun MainViewController() = ComposeUIViewController {
    initKoin()
    App()
}