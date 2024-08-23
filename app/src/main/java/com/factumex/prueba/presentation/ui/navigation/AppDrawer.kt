package com.factumex.prueba.presentation.ui.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.factumex.prueba.R


@Composable
fun AppDrawer(
    route: String,
    navigateToInicio: () -> Unit = {},
    navigateToActividadUno: () -> Unit = {},
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

        Box {
            Column {
                NavigationDrawerItem(
                    label = {
                        Text(
                            text = "Inicio",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )
                        )
                    },
                    selected = route == AllDestinations.Inicio,
                    onClick = {
                        navigateToInicio()
                        closeDrawer()
                    },
                    icon = {
                        Icon(
                            modifier = Modifier
                                .fillMaxHeight(.40f),
                            imageVector = Icons.Filled.Home,
                            contentDescription = null
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.White,
                        selectedContainerColor = Color.White
                    ),
                    modifier = Modifier
                        .background(Color.White)
                )

                NavigationDrawerItem(
                    label = {
                        Text(
                            text = "Pokedex",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )
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
                            imageVector = Icons.Filled.Search,
                            contentDescription = null
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.White,
                        selectedContainerColor = Color.White
                    ),
                    modifier = Modifier
                        .background(Color.White)
                )


                NavigationDrawerItem(
                    label = {
                        Text(
                            text = "Componente",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Medium,
                                textAlign = TextAlign.Center
                            )
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
                            imageVector = Icons.Filled.AccountCircle,
                            contentDescription = null
                        )
                    },
                    colors = NavigationDrawerItemDefaults.colors(
                        unselectedContainerColor = Color.White,
                        selectedContainerColor = Color.White
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
        modifier = Modifier.border(2.dp, Color.LightGray),
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
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(48.dp)
            )


            Text(
                text = stringResource(id = R.string.version_app),
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            )
        }

    }
}
