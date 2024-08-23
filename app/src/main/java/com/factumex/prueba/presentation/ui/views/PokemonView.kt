package com.factumex.prueba.presentation.ui.views

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.factumex.prueba.R
import com.factumex.prueba.data.datasource.local.PokemonEntity
import com.factumex.prueba.presentation.ui.theme.qqAzulElectrico
import com.factumex.prueba.presentation.viewmodels.PokemonCompartidoViewModel
import com.factumex.prueba.presentation.viewmodels.PokemonViewModel
import com.factumex.prueba.presentation.ui.components.ImagenCircular
import com.factumex.prueba.utils.VerificarConexion


@Composable
fun PokemonView(
    navController: NavHostController,
    sharedViewModel: PokemonCompartidoViewModel,
    pokemonViewModel: PokemonViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val isConnected = remember { VerificarConexion(context) }
    var titulo by remember { mutableStateOf("Pokédex") } // Usar mutableStateOf para permitir actualizaciones dinámicas

    LaunchedEffect(isConnected) {
        if (isConnected) {
            Log.i("===", "Con red")
            pokemonViewModel.cargarPokemon(0, 25)
        } else {
            Log.i("===", "Sin red")
            pokemonViewModel.cargarDatosSinRed()
            titulo = "Pokédex fuera de línea"
        }
    }

    PokemonListView(
        navController = navController,
        sharedViewModel = sharedViewModel,
        pokemonViewModel = pokemonViewModel,
        titulo = titulo,
    )
}


@Composable
fun PokemonListView(
    navController: NavHostController,
    sharedViewModel: PokemonCompartidoViewModel,
    pokemonViewModel: PokemonViewModel,
    titulo: String
) {

    val pokemonList by pokemonViewModel.listaPokemon.observeAsState(emptyList())
    val isLoading by pokemonViewModel.isLoading.observeAsState(false)


    //Modal de espera
    LoadingDialog(isLoading = isLoading)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {


                Text(
                    text = titulo,
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                )
            }

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                LazyColumn {
                    items(pokemonList) { pokemon ->
                        ListaPokemon(
                            pokemon = pokemon,
                            onClick = {
                                sharedViewModel.selectPokemon(pokemon)
                                navController.navigate("PokemonDetalle")
                            },
                        )
                    }

                    item {
                        if (pokemonViewModel.isLoading.value == true) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                //CircularProgressIndicator()
                            }
                        } else {
                            LaunchedEffect(Unit) {
                                if (pokemonList.isNotEmpty() && !isLoading) {
                                    pokemonViewModel.cargarMasPokemon(pokemonList.size)
                                }
                            }
                        }
                    }
                }

            }
        }
    }

}


@Composable
fun ListaPokemon(pokemon: PokemonEntity, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {

        ImagenCircular(
            //url = "",
            url = pokemon.imageUrl,
            name = pokemon.name,
            //name = "",
            placeholder = painterResource(R.drawable.logo_factum),
            backgroundColor = Color.White,
            textColor = Color.Black
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = pokemon.id.toString() + " - " + pokemon.name.capitalize(),
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start
            ),
            modifier = Modifier.weight(1f),
        )

        IconButton(
            modifier = Modifier
                .size(48.dp),
            onClick = {
                Log.d("PokemonDetalle", "isFavorite antes: ${pokemon.isFavorite}")
                //sharedViewModel.toggleFavorite(it)
                Log.d("PokemonDetalle", "isFavorite después: ${pokemon.isFavorite}")
            }
        ) {
            Icon(
                imageVector = if (pokemon.isFavorite == 1) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = null,
                tint = if (pokemon.isFavorite == 1) Color.Red else Color.Gray,
                modifier = Modifier.size(64.dp)
            )
        }

        /*
        IconButton(
            modifier = Modifier
                .size(64.dp),
            onClick = { onFavoriteClick() }) {
            Icon(
                imageVector = if (pokemon.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = null,
                tint = Color.Red // Color verde para el icono
            )
        }*/
    }
}


@Composable
fun LoadingDialog(isLoading: Boolean, onDismiss: () -> Unit = {}) {
    if (isLoading) {
        Dialog(onDismissRequest = { onDismiss() }) {
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color.White,
                modifier = Modifier
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp)
                ) {
                    Text(
                        text = "Cargando...",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))


                    Text(
                        text = "Espere mientras se carga la información.",
                        style = TextStyle(
                            color = Color.Gray,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )


                    Spacer(modifier = Modifier.height(24.dp))
                    CircularProgressIndicator(
                        color = qqAzulElectrico,
                        modifier = Modifier
                            .size(80.dp)
                    )
                }
            }
        }
    }
}