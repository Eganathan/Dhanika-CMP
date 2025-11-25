package dev.eknath.dhanika

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.eknath.dhanika.ui.nav.routes.platformAppContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            platformAppContext = this
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}