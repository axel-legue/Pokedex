package com.axell.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.axell.pokedex.feature.home.ui.HomeBody
import com.axell.pokedex.feature.pokemoninfo.ui.PokemonInfoBody
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
        val navController = rememberNavController()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            Scaffold { innerPadding ->
                PokedexNavHost(navController = navController, modifier = Modifier.padding(innerPadding))
            }
        }
    }
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun PokedexNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = PokedexScreen.Home.name,
        modifier = modifier
    ) {
        composable(PokedexScreen.Home.name) {
            HomeBody(
                onPokemonsClick = { navController.navigate(PokedexScreen.Pokemons.name) },
                onFavoritesClick = { /* TODO: add favorites screen */ },
                onTypesClick = { /* TODO: add types screen  */ },
                onItemsClick = { /* TODO: add items screen  */ },
                onMovesClick = { /* TODO: add moves screen  */ }
            )
        }
        composable(PokedexScreen.Pokemons.name) {
            PokemonsBody(
                onPokemonSelected = { name ->
                    navigateToPokemonInfo(navController = navController, pokemonName = name)
                },
                pokemonsViewModel = hiltViewModel()
            )
        }
        composable(PokedexScreen.Favorites.name) {
        }
        composable(
            route = "${PokedexScreen.PokemonInfo.name}/{name}",
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                }
            ),
            deepLinks = listOf(
                navDeepLink {
                    uriPattern = "pokedex://${PokedexScreen.PokemonInfo.name}/{name}"
                }
            )
        ) { entry ->
            val pokemonName = entry.arguments?.getString("name")
            PokemonInfoBody(
                name = pokemonName ?: "", pokemonInfoViewModel = hiltViewModel()
            )
        }
        composable(PokedexScreen.Items.name) {
        }
        composable(PokedexScreen.Moves.name) {
        }
    }
}

private fun navigateToPokemonInfo(
    navController: NavHostController,
    pokemonName: String
) {
    navController.navigate("${PokedexScreen.PokemonInfo.name}/$pokemonName")
}
