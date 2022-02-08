package com.axell.pokedex.feature.pokemoninfo.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.axell.pokedex.R
import com.axell.pokedex.core.extension.toColor
import com.axell.pokedex.core.extension.toDrawable
import com.axell.pokedex.core.extension.toPokemonType
import com.axell.pokedex.model.PokemonType
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@ExperimentalUnitApi
@Composable
fun PokemonInfoBody(
    name: String = "",
    pokemonInfoViewModel: PokemonInfoViewModel = viewModel()
) {
    val systemUiController = rememberSystemUiController()
    LaunchedEffect(Unit) {
        pokemonInfoViewModel.loadPokemonInfo(name = name)
    }
    val pokemonInfo = pokemonInfoViewModel.pokemonInfo().observeAsState()
    val types = mutableListOf<PokemonType>()

    pokemonInfo.value?.let {
        types.addAll(it.types.map { typeResponse ->
            typeResponse.type.toPokemonType()
        })
    }
    if (types.isNotEmpty()) {
        SideEffect {
            systemUiController.setStatusBarColor(types.first().toColor())
            if (types.size > 1) {
                systemUiController.setNavigationBarColor(types.last().toColor())
            } else {
                systemUiController.setNavigationBarColor(types.first().toColor())
            }
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = if (types.isNotEmpty() && types.size > 1) {
                Modifier.background(brush = Brush.verticalGradient(colors = types.map { it.toColor() }))
            } else if (types.size == 1) {
                Modifier.background(
                    color = types[0].toColor()
                )
            } else {
                Modifier.background(
                    color = Color.Green
                )
            }
        ) {
            Icon(
                imageVector = Icons.Outlined.ChevronLeft,
                contentDescription = "Back",
                modifier = Modifier.padding(start = 7.dp, bottom = 20.dp, top = 20.dp),
                tint = Color.White,
            )
            PokemonTitle(name = pokemonInfo.value?.name ?: "", number = pokemonInfo.value?.id ?: 0, modifier = Modifier.fillMaxWidth())
            Badges(pokemonTypes = types, modifier = Modifier.fillMaxWidth())
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) {
                Image(
                    painterResource(id = R.drawable.pokeball),
                    contentDescription = "Pokemon type icon",
                    colorFilter = ColorFilter.tint(Color.White),
                    modifier = Modifier.align(Alignment.Center),
                    alpha = 0.1f
                )
                Image(
                    painter = rememberImagePainter(data = pokemonInfo.value?.getImageUrl(), builder = {
                        crossfade(true)
                    }),
                    contentDescription = null,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

    }
}

@ExperimentalUnitApi
@Preview(widthDp = 320, heightDp = 640)
@Composable
fun PokemonDetailBodyPreview() {
    PokemonInfoBody()
}

@Composable
fun PokemonTypeBadge(pokemonType: PokemonType = PokemonType.Bug) {
    val color: Color = pokemonType.toColor()
    val icon: Int = pokemonType.toDrawable()
    Surface(
        shape = MaterialTheme.shapes.medium.copy(all = CornerSize(15.dp)),
        elevation = 1.dp,
        color = color,
        modifier = Modifier.padding(top = 5.dp, bottom = 5.dp, end = 10.dp),
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painterResource(id = icon),
                contentDescription = "Pokemon type icon",
                modifier = Modifier.size(12.dp)
            )
            Text(text = pokemonType.name.replaceFirstChar { it.uppercaseChar() }, color = Color.White, modifier = Modifier.padding(start = 5.dp))
        }
    }
}

@Preview
@Composable
fun PokemonTypePreview(pokemonType: PokemonType = PokemonType.Bug) {
    PokemonTypeBadge()
}

@OptIn(ExperimentalUnitApi::class)
@Composable
fun PokemonTitle(name: String, number: Int, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 15.dp, bottom = 10.dp)
    ) {
        Text(
            text = name,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = TextUnit(value = 20f, TextUnitType.Sp)
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "#$number",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(end = 15.dp)
                .align(alignment = Alignment.Bottom)
        )
    }
}

@Composable
fun Badges(pokemonTypes: List<PokemonType>, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(start = 15.dp, bottom = 15.dp)) {
        pokemonTypes.forEach {
            PokemonTypeBadge(it)
        }
    }
}