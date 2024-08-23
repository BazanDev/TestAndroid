package com.factumex.prueba.presentation.ui.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.factumex.prueba.R
import com.factumex.prueba.presentation.ui.components.ImagenCircular

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp


@Preview(showBackground = true)
@Composable
fun ActividadUnoViewPreview() {
    ImagenCircular(
        url = "https://hips.hearstapps.com/hmg-prod/images/gettyimages-1398008130-663c7735a4fd4.jpg?crop=1xw:0.7280716162943496xh;center,top&resize=1200:*",
        name = "Omar Cruz",
        placeholder = painterResource(R.drawable.logo_factum),
        backgroundColor = Color.Blue,
        textColor = Color.White
    )
}


@Composable
fun ActividadUnoView(navHostController: NavHostController) {
    var url by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }

    var backgroundColor by remember { mutableStateOf(Color.White) }
    var red by remember { mutableStateOf(backgroundColor.red) }
    var green by remember { mutableStateOf(backgroundColor.green) }
    var blue by remember { mutableStateOf(backgroundColor.blue) }




    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 16.dp, start = 32.dp, end = 32.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Imagen Circular
            ImagenCircular(
                url = url,
                name = name,
                placeholder = painterResource(R.drawable.logo_factum),
                backgroundColor = backgroundColor,
                textColor = Color.Black,
                size = 200.dp
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para la URL
            OutlinedTextField(
                value = url,
                onValueChange = { url = it },
                label = { Text("URL de la Imagen") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo de texto para el nombre
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))


            Text(
                text = "Selecciona el color de fondo:",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
            )


            // Slider rojo
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Rojo",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                )
                Slider(
                    value = red,
                    onValueChange = {
                        red = it
                        backgroundColor = Color(red, green, blue)
                    },
                    valueRange = 0f..1f,
                    modifier = Modifier.weight(1f)
                )
            }

            // Slider verde
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Verde",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                )
                Slider(
                    value = green,
                    onValueChange = {
                        green = it
                        backgroundColor = Color(red, green, blue)
                    },
                    valueRange = 0f..1f,
                    modifier = Modifier.weight(1f)
                )
            }

            // Slider azil
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Azul:",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                )
                Slider(
                    value = blue,
                    onValueChange = {
                        blue = it
                        backgroundColor = Color(red, green, blue)
                    },
                    valueRange = 0f..1f,
                    modifier = Modifier.weight(1f)
                )
            }


        }

    }
}