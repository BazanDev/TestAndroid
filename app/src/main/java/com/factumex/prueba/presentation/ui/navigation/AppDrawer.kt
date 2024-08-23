package com.factumex.prueba.presentation.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.factumex.prueba.R


@Composable
fun AppDrawer(
    route: String,
    navigateToActividadUno: () -> Unit = {},
    navigateToActividadDos: () -> Unit = {},
    navigateToActividadTres: () -> Unit = {},
    navigateToPokemon: () -> Unit = {},
    closeDrawer: () -> Unit = {},
    modifier: Modifier = Modifier,
) {

    ModalDrawerSheet(
        modifier = Modifier.fillMaxHeight(),
        drawerShape = MaterialTheme.shapes.medium,
        drawerContainerColor = Color.White,
        drawerContentColor = Color.White,
        drawerTonalElevation = 16.dp
    ) {
        DrawerHeader(modifier.background(Color.White))

        Box() {
            Column() {


                NavigationDrawerItem(
                    label = {
                        Text(
                            text = "Actividad 1",
                            style = MaterialTheme.typography.labelMedium
                        )
                    },
                    selected = route == AllDestinations.ActividadUno,
                    onClick = {
                        navigateToActividadUno()
                        closeDrawer()
                    },
                    icon = {
                        Icon(
                            modifier = Modifier
                                .fillMaxHeight(.40f),
                            imageVector = Icons.Filled.Favorite , // Aquí cambias el ícono al que prefieras
                            contentDescription = null
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.White, // Fondo del ítem no seleccionado
                        selectedContainerColor = Color.White // Fondo del ítem seleccionado
                    ),
                    modifier = Modifier
                        .background(Color.White)
                )


                NavigationDrawerItem(
                    label = {
                        Text(
                            text = "Actividad 2",
                            style = MaterialTheme.typography.labelMedium
                        )
                    },
                    selected = route == AllDestinations.ActividadDos,
                    onClick = {
                        navigateToActividadDos()
                        closeDrawer()
                    },
                    icon = {
                        Icon(
                            modifier = Modifier
                                .fillMaxHeight(.40f),
                            imageVector = Icons.Filled.Favorite , // Aquí cambias el ícono al que prefieras
                            contentDescription = null
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.White, // Fondo del ítem no seleccionado
                        selectedContainerColor = Color.White // Fondo del ítem seleccionado
                    ),
                    modifier = Modifier
                        .background(Color.White)
                )

                NavigationDrawerItem(
                    label = {
                        Text(
                            text = "Actividad 3",
                            style = MaterialTheme.typography.labelMedium
                        )
                    },
                    selected = route == AllDestinations.ActividadTres,
                    onClick = {
                        navigateToActividadTres()
                        closeDrawer()
                    },
                    icon = {
                        Icon(
                            modifier = Modifier
                                .fillMaxHeight(.40f),
                            imageVector = Icons.Filled.Favorite , // Aquí cambias el ícono al que prefieras
                            contentDescription = null
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.White, // Fondo del ítem no seleccionado
                        selectedContainerColor = Color.White // Fondo del ítem seleccionado
                    ),
                    modifier = Modifier
                        .background(Color.White)
                )


                NavigationDrawerItem(
                    label = {
                        Text(
                            text = "Pokemon",
                            style = MaterialTheme.typography.labelMedium
                        )
                    },
                    selected = route == AllDestinations.Pokemon,
                    onClick = {
                        navigateToPokemon()
                        closeDrawer()
                    },
                    icon = {
                        Icon(
                            modifier = Modifier
                                .fillMaxHeight(.40f),
                            imageVector = Icons.Filled.Favorite , // Aquí cambias el ícono al que prefieras
                            contentDescription = null
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.White, // Fondo del ítem no seleccionado
                        selectedContainerColor = Color.White // Fondo del ítem seleccionado
                    ),
                    modifier = Modifier
                        .background(Color.White)
                )

            }
        }
    }
}


@Composable
fun DrawerHeader(modifier: Modifier = Modifier) {

    Card(
        shape = RoundedCornerShape(8.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = MaterialTheme.colorScheme.onSurface,
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    )
    {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = modifier
                .padding(50.dp)
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_factum),
                contentDescription = "Logo",
                contentScale = ContentScale.Fit
            )


            Text(
                text = stringResource(id = R.string.version_app),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        }

    }
}