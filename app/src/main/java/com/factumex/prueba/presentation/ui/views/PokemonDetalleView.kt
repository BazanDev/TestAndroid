package com.factumex.prueba.presentation.ui.views

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.factumex.prueba.R
import com.factumex.prueba.presentation.viewmodels.PokemonCompartidoViewModel
import com.factumex.prueba.presentation.ui.components.ImagenCircular

import androidx.compose.material3.Icon


@Composable
fun PokemonDetalleView(sharedViewModel: PokemonCompartidoViewModel) {
    val pokemon by sharedViewModel.selectedPokemon.observeAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 16.dp, start = 32.dp, end = 32.dp)
            .verticalScroll(rememberScrollState())
    ) {
        pokemon?.let {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                // Nombre del Pokémon
                Text(
                    text = it.name.capitalize(),
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))


                ImagenCircular(
                    //url = "",
                    url = it.imageUrl,
                    name = it.name,
                    //name = "",
                    placeholder = painterResource(R.drawable.logo_factum),
                    backgroundColor = Color.White,
                    textColor = Color.Black,
                    size = 200.dp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Detalles del Pokémon
                Text(
                    text = "No Pokémon: ${it.id} mts",  // Altura en metros
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Detalles del Pokémon
                Text(
                    text = "Altura: ${it.height} mts",  // Altura en metros
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Peso: ${it.weight} Kg",  // Peso en kilogramos
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Tipos: ${it.types.capitalize()}",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 20.sp,
                        textAlign = TextAlign.Start
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))


                IconButton(
                    modifier = Modifier
                        .size(200.dp),
                    onClick = {
                        Log.d("PokemonDetalle", "isFavorite antes: ${it.isFavorite}")
                        sharedViewModel.toggleFavorite(it)
                        Log.d("PokemonDetalle", "isFavorite después: ${it.isFavorite}")
                    }
                ) {
                    Icon(
                        imageVector = if (it.isFavorite == 1) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = if (it.isFavorite == 1) Color.Red else Color.Gray,
                        modifier = Modifier.size(64.dp)
                    )
                }




            }
        } ?: run {
            Text(
                text = "No se seleccionó ningún Pokémon",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}

