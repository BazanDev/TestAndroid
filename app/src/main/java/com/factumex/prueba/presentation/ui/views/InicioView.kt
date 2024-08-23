package com.factumex.prueba.presentation.ui.views

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.factumex.prueba.presentation.ui.theme.qqAzulElectrico
import com.factumex.prueba.presentation.ui.theme.qqNegroMate
import com.factumex.prueba.presentation.viewmodels.InicioViewModel
import com.factumex.prueba.utils.VerificarConexion
import com.factumex.prueba.utils.estadoRedActual
import com.factumex.prueba.utils.observeConnectivityAsFlow


@Preview(showBackground = true)
@Composable
fun InicioViewPreview() {
    InicioView()
}

@Composable
fun InicioView(
    navHostController: NavHostController = rememberNavController(),
    inicioViewModel: InicioViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val isConnected by context.observeConnectivityAsFlow().collectAsState(initial = estadoRedActual(context))


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


            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = "Test",
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

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .border(1.dp, qqAzulElectrico),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    onClick =
                    {
                        inicioViewModel.navegarPokedex(navHostController)
                    }
                )
                {
                    Text(
                        text = "Pokédex",
                        style = TextStyle(
                            color = qqNegroMate,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .border(1.dp, qqAzulElectrico),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                    onClick =
                    {
                        inicioViewModel.navegarComponente(navHostController)
                    }
                )
                {
                    Text(
                        text = "Componente",
                        style = TextStyle(
                            color = qqNegroMate,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }

            if (!isConnected) {
                mostrarRed()
            }
        }
    }
}

@Composable
fun mostrarRed() {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = "Sin conexión a internet",
            style = TextStyle(
                color = Color.Red,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        )


    }
}

/*
@SuppressLint("ServiceCast")
fun estadoRedActual(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
    return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}
*/