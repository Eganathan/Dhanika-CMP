package dev.eknath.dhanika

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.eknath.dhanika.nav.routes.platformAppContext
import dev.eknath.dhanika.room.getRoomDatabase

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