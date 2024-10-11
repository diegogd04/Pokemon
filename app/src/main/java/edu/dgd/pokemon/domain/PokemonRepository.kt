package edu.dgd.pokemon.domain

interface PokemonRepository {
    fun getPokemons(): List<Pokemon>
    fun getPokemon(pokemonId: String): Pokemon?
}