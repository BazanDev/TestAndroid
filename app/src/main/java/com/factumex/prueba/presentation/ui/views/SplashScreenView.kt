package com.factumex.prueba.presentation.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.factumex.prueba.presentation.ui.theme.AppFontLuna
import kotlinx.coroutines.delay

@Composable
fun SplashScreenView(
    rememberNavController: NavHostController
) {
    Splash()
    LaunchedEffect(key1 = true) {
        delay(4000)
        rememberNavController.navigate("PantallaUno")
    }
}


@Composable
fun Splash() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, bottom = 16.dp, start = 32.dp, end = 32.dp)
    ) {
        Column(
            modifier =
            Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Factumex",
                style = TextStyle(
                    color = Color.Red,
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = AppFontLuna,
                    textAlign = TextAlign.Center
                )
            )
        }
    }
}