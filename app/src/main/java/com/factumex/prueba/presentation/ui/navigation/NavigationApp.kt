package com.factumex.prueba.presentation.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.factumex.prueba.presentation.ui.views.ActividadUnoView
import com.factumex.prueba.presentation.ui.views.InicioView
import com.factumex.prueba.presentation.ui.views.PokemonDetalleView
import com.factumex.prueba.presentation.ui.views.PokemonView
import com.factumex.prueba.presentation.viewmodels.PokemonCompartidoViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationApp(
    modifier: Modifier = Modifier,
    navHostController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
) {

    val currentNavBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = currentNavBackStackEntry?.destination?.route ?: AllDestinations.Inicio

    val navigationActions = remember(navHostController) {
        AppNavigationActions(navHostController)
    }

    var showTopBar by remember { mutableStateOf(true) }

    val pokemonCompartidoViewModel: PokemonCompartidoViewModel = hiltViewModel()


    Box(modifier = Modifier.background(Color.White)) {

        ModalNavigationDrawer(
            gesturesEnabled = true,
            scrimColor = Color.White,
            drawerContent = {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.border(2.dp, Color.LightGray),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White,
                        contentColor = MaterialTheme.colorScheme.onSurface,
                    ),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    AppDrawer(
                        route = currentRoute,
                        navigateToInicio = { navigationActions.navigateToInicio() },
                        navigateToActividadUno = { navigationActions.navigateToActividadUno() },
                        navigateToPokemon = { navigationActions.navigateToPokemon() },
                        closeDrawer = { coroutineScope.launch { drawerState.close() } },
                        modifier = Modifier.background(Color.White)
                    )
                }
            },
            drawerState = drawerState
        ) {

            Scaffold(
                modifier = Modifier.background(Color.White),
                floatingActionButtonPosition = FabPosition.End,
                containerColor = Color.White,
                contentColor = Color.White,
                topBar = {
                    if (showTopBar) {
                        TopAppBar(
                            title = { Text(text = "") },
                            navigationIcon = {
                                IconButton(
                                    onClick = {
                                        coroutineScope.launch { drawerState.open() }
                                    },
                                    content = {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription = null,
                                        )
                                    })
                            },
                        )
                    }
                },
                content = { innerPadding ->

                    Box(
                        modifier = modifier
                            .fillMaxSize()
                            .background(Color.White)
                    ) {

                        NavHost(
                            navController = navHostController,
                            startDestination = AllDestinations.Inicio,
                            modifier = modifier
                                .padding(innerPadding)
                                .background(Color.White)
                        ) {

                            composable(AllDestinations.Inicio) {
                                InicioView(navHostController)
                            }

                            composable(AllDestinations.ActividadUno) {
                                ActividadUnoView(navHostController)
                            }

                            composable(AllDestinations.Pokemon) {
                                PokemonView(navHostController, sharedViewModel = pokemonCompartidoViewModel)
                            }

                            composable(AllDestinations.PokemonDetalle) {
                                PokemonDetalleView(sharedViewModel = pokemonCompartidoViewModel)
                            }

                        }

                    }
                }
            )
        }
    }
}
