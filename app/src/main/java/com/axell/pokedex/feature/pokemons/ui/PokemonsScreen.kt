package com.axell.pokedex.feature.pokemons.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.rememberImagePainter
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun PokemonsBody(
    onPokemonSelected: (String) -> Unit = {},
    pokemonsViewModel: PokemonsViewModel = viewModel()
) {
    val pokemonItems = pokemonsViewModel.getAllPokemons().collectAsLazyPagingItems()
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(Color(0xFF1E1E1E))
    Surface(
        modifier = Modifier
            .background(color = Color(0xFF1E1E1E))
            .semantics { contentDescription = "Pokemons Screen" }
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.background(
                color = Color.Black
            )
        ) {
            items(pokemonItems.itemCount) { index ->
                pokemonItems[index]?.let { pokemon ->
                    PokemonCard(name = pokemon.name, picture = pokemon.getImageUrl(), types = listOf()) {
                        onPokemonSelected(it)
                    }
                }
            }
            pokemonItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    loadState.append is LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    loadState.refresh is LoadState.Error -> {}
                    loadState.append is LoadState.Error -> {}
                }
            }
        }
    }
}

@Composable
fun PokemonCard(
    name: String = "",
    picture: String = "",
    types: List<String> = listOf(),
    onPokemonSelected: (String) -> Unit = {}
) {
    Surface(modifier = Modifier.clickable { onPokemonSelected(name) }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF1E1E1E)
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
                Text("Fire Water", color = Color(0xFFFFFFFF))
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

@Composable
fun LoadingItem() {
    CircularProgressIndicator(
        modifier =
        Modifier
            .testTag("ProgressBarItem")
            .fillMaxWidth()
            .padding(16.dp)
            .wrapContentWidth(
                Alignment.CenterHorizontally
            )
    )
}
