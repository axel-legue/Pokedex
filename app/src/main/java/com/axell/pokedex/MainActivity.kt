package com.axell.pokedex

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.axell.pokedex.feature.home.ui.HomeBody
import com.axell.pokedex.feature.pokemons.ui.PokemonsBody
import com.axell.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexApp()
        }
    }
}

@Composable
private fun PokedexApp() {
    PokedexTheme {
        val allScreens = PokedexScreen.values().toList()
        val navController = rememberNavController()
        val backStackEntry = navController.currentBackStackEntryAsState()
        val context = LocalContext.current
        var currentScreen = PokedexScreen.fromRoute(backStackEntry.value?.destination?.route)
        Scaffold { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = PokedexScreen.Home.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(PokedexScreen.Home.name) {
                    HomeBody(
                        onPokemonsClick = {
                            navController.navigate(PokedexScreen.Pokemons.name)
                        },
                        onFavoritesClick = { Toast.makeText(context, "Favorites to be implemented", Toast.LENGTH_SHORT).show() },
                        onTypesClick = { Toast.makeText(context, "Types to be implemented", Toast.LENGTH_SHORT).show() },
                        onItemsClick = { Toast.makeText(context, "Items to be implemented", Toast.LENGTH_SHORT).show() },
                        onMovesClick = { Toast.makeText(context, "Moves to be implemented", Toast.LENGTH_SHORT).show() }
                    )
                }
                composable(PokedexScreen.Favorites.name) {
                }
                composable(PokedexScreen.Pokemons.name) {
                    PokemonsBody(
                        onPokemonSelected = {},
                        pokemonsViewModel = hiltViewModel()
                    )
                }
                composable(PokedexScreen.PokemonDetails.name) {
                }
                composable(PokedexScreen.Items.name) {
                }
                composable(PokedexScreen.Moves.name) {
                }
            }
        }
    }
}
