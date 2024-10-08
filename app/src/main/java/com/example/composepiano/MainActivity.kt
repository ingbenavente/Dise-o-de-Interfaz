package com.example.composepiano

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composepiano.ui.theme.ComposePianoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposePianoTheme {
                PianoScreen()
            }
        }
    }

    // Función para reproducir la nota usando MediaPlayer
    private fun playNote(noteResId: Int) {
        val mediaPlayer = MediaPlayer.create(this, noteResId)
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener {
            mediaPlayer.release()
        }
    }

    // Pantalla principal con las 7 notas musicales
    @Composable
    fun PianoScreen() {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly  // Espaciado uniforme entre las teclas
            ) {

                PianoKey("Do", Color.Black) { playNote(R.raw.doo) }
                PianoKey("Re", Color.Black) { playNote(R.raw.re) }
                PianoKey("Mi", Color.Black) { playNote(R.raw.mi) }
                PianoKey("Fa", Color.Black) { playNote(R.raw.fa) }
                PianoKey("Sol", Color.Black) { playNote(R.raw.sol) }
                PianoKey("La", Color.Black) { playNote(R.raw.la) }
                PianoKey("Si", Color.Black) { playNote(R.raw.si) }
            }
        }
    }

    // Composable para cada tecla del piano
    @Composable
    fun PianoKey(note: String, color: Color, onClick: () -> Unit) {
        Button(
            onClick = { onClick() },
            modifier = Modifier
                .width(60.dp)
                .height(200.dp)
                .padding(4.dp)
                .background(color)
                .shadow(elevation = 4.dp),  // Añadir sombra para un efecto de tecla en 3D
            colors = ButtonDefaults.buttonColors(
                containerColor = color
            )
        ) {
            Text(text = note)
        }
    }

}
