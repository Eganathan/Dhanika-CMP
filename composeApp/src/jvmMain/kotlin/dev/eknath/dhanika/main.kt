package dev.eknath.dhanika

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import dev.eknath.dhanika.di.initKoin

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Dhanika",
    ) {
        initKoin()
        App()
    }
}