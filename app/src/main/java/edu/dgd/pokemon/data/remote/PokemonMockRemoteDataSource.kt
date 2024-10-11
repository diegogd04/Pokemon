package edu.dgd.pokemon.data.remote

import edu.dgd.pokemon.domain.Pokemon

class PokemonMockRemoteDataSource {

    private val pokemons = listOf(
        Pokemon(
            "1",
            "Charmander",
            "Mar llamas",
            "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/004.png"
        ),
        Pokemon(
            "2",
            "Squirtle",
            "Torrente",
            "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/007.png"
        ),
        Pokemon(
            "3",
            "Rattata",
            "Fuga",
            "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/019.png"
        )
    )

    fun getPokemons(): List<Pokemon> {
        return pokemons
    }

    fun getPokemon(pokemonId: String): Pokemon?{
        return getPokemons().find { it.id == pokemonId }
    }
}