package com.axell.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Mic
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.axell.pokedex.feature.pokemons.ui.PokemonsViewModel
import com.axell.pokedex.ui.theme.PokedexTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val pokemonsViewModel: PokemonsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme {
                PokedexMain()
            }
        }
    }
}

@Composable
private fun PokedexMain() {
    Surface(
        color = Color(0xFF1E1E1E), modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Column(modifier = Modifier.padding(24.dp)) {
            Title(
                text = "What are\nyou looking for ?",
                modifier = Modifier.padding(bottom = 20.dp)
            )
            Search(Modifier.padding(vertical = 15.dp))
            MenuCard(
                name = "Pokemon",
                color = Color(0xFF4EB54F),
                modifier = Modifier
                    .padding(bottom = 12.dp)
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                MenuCard(
                    name = "Items",
                    color = Color(0xFFF26057),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 6.dp)
                )
                MenuCard(
                    name = "Moves",
                    color = Color(0xFF4996F0),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 6.dp)
                )
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                MenuCard(
                    name = "Types",
                    color = Color(0xFFFEC53C),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 6.dp)
                )
                MenuCard(
                    name = "Favorite",
                    color = Color(0xFFA846C4),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 6.dp)
                )
            }

        }

    }
}

@Composable
fun MenuCard(name: String, color: Color, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .clickable {
                // Todo add action
            },
        shape = RoundedCornerShape(7.dp),
        elevation = 10.dp,
        backgroundColor = color
    ) {
        Text(
            text = name,
            textAlign = TextAlign.Start,
            style = MaterialTheme.typography.h6.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.background
            ),
            modifier = Modifier
                .padding(top = 50.dp, bottom = 10.dp, start = 10.dp)
                .wrapContentWidth(align = Alignment.Start),
        )
    }
}

@Composable
fun Title(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold),
        color = Color.White,
        modifier = modifier
    )
}

@Composable
fun Search(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFF101010),
                shape = RoundedCornerShape(30.dp)
            )
    ) {
        val textState = remember { mutableStateOf(TextFieldValue()) }
        Icon(
            imageVector = Icons.Outlined.Search,
            contentDescription = "Search",
            modifier = Modifier
                .padding(start = 10.dp)
                .align(Alignment.CenterVertically),
            tint = Color(0xFF858585)
        )
        TextField(value = textState.value, onValueChange = { textState.value = it }, modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Outlined.Mic,
            contentDescription = "Voice Search",
            modifier = Modifier
                .padding(end = 10.dp)
                .align(Alignment.CenterVertically),
            tint = Color(0xFF858585)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearch() {
    PokedexTheme {
        Search()
    }
}

@Preview(
    showBackground = true
)
@Composable
fun PreviewMenu() {
    PokedexTheme {
        PokedexMain()
    }
}

@Preview(
    widthDp = 320,
    showBackground = true,
    backgroundColor = 0xFF000000
)
@Composable
fun PreviewTitle() {
    PokedexTheme {
        Title(text = "What are\nyou looking for ?")
    }
}
