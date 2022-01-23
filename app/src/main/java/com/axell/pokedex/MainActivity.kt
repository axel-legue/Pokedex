package com.axell.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.axell.pokedex.feature.pokemons.ui.PokemonsViewModel
import com.axell.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val pokemonsViewModel: PokemonsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Greeting("Android", viewModel = pokemonsViewModel)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String,viewModel: PokemonsViewModel) {
    viewModel.loadPokemons()
    Text(text = "Hello $name!")
}

