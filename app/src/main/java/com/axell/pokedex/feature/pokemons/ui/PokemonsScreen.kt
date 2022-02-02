package com.axell.pokedex.feature.pokemons.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.axell.pokedex.feature.pokemons.entity.PokemonEntity

@Composable
fun PokemonsBody(
    onPokemonSelected: () -> Unit = {},
    pokemonsViewModel: PokemonsViewModel = viewModel()
) {
    pokemonsViewModel.loadPokemons()
    val pokemons: List<PokemonEntity> by pokemonsViewModel.pokemons().observeAsState(listOf())
    LazyColumn {
        items(items = pokemons) { pokemon ->
            PokemonCard(name = pokemon.name, picture = pokemon.getImageUrl(), types = listOf())
        }
    }

    pokemonsViewModel.pokemons()
}

@Composable
fun PokemonCard(
    name: String = "",
    picture: String = "",
    types: List<String> = listOf()
) {
    Surface {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF060606)
                )
                .padding(all = 10.dp)
        ) {
            Image(
                painter = rememberImagePainter(data = picture, builder = {
                    crossfade(true)
                }),
                contentDescription = null,
                modifier = Modifier.size(46.dp)
            )
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically)
                    .weight(1f)
            ) {
                Text(text = name, color = Color(0xFFFFFFFF))
                Text("Bis", color = Color(0xFFFFFFFF))
            }
            Icon(
                imageVector = Icons.Filled.ArrowRight,
                contentDescription = "Search",
                modifier = Modifier
                    .padding(start = 10.dp)
                    .align(Alignment.CenterVertically),
                tint = Color(0xFF858585)
            )
        }
    }
}

@Preview
@Composable
fun PokemonCardPreview() {
    PokemonCard()
}
