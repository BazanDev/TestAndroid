package com.factumex.prueba.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.factumex.prueba.R


@Composable
fun ImagenCircular(
    url: String,
    name: String,
    placeholder: Painter,
    backgroundColor: Color = Color.Gray,
    textColor: Color = Color.White,
    size: Dp = 96.dp
) {

    val idImagen = 1

    val placeholder = when (idImagen) {
        1 -> painterResource(R.drawable.placeholder_uno)
        2 -> painterResource(R.drawable.placeholder_dos)
        3 -> painterResource(R.drawable.placeholder_tres)
        else -> painterResource(R.drawable.logo_factum)
    }


    val initials = remember(name) {
        val words = name.split(" ")
            .filter { it.isNotEmpty() }
        when {
            words.isEmpty() -> ""
            words.size == 1 -> words[0].take(1)
            else -> words[0].take(1) + words[1].take(1)
        }.uppercase()
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(size)
            .clip(CircleShape)
            .background(backgroundColor)
            .border(2.dp, Color.Gray, CircleShape)
    ) {
        when {
            url.isEmpty() -> {
                if (initials.isNotEmpty() && initials.first().isLetter()) {
                    // Mostrar iniciales
                    Text(
                        text = initials,
                        color = textColor,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold
                    )
                } else {
                    // Mostrar  placeholder
                    Image(
                        painter = placeholder,
                        contentDescription = "Placeholder",
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.clip(CircleShape)
                    )
                }
            }

            else -> {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(url)
                        .crossfade(true)
                        .build(),
                    placeholder = placeholder,
                    contentDescription = "Imagen",
                    contentScale = ContentScale.FillHeight,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(CircleShape)
                )
            }
        }
    }
}
