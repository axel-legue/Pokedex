package com.axell.pokedex.feature.pokemons.entity

import android.os.Parcelable
import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class PokemonEntity(
    var page: Int = 0,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "url") val url: String
) : Parcelable {

    fun getImageUrl(): String {
        val index = url.split("/".toRegex()).dropLast(1).last()
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"
    }
}
