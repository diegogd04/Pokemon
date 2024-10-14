package edu.dgd.pokemon.data

import edu.dgd.pokemon.data.local.PokemonXmlLocalDataSource
import edu.dgd.pokemon.data.remote.PokemonMockRemoteDataSource
import edu.dgd.pokemon.domain.Pokemon

class PokemonDataRepository(
    private val local: PokemonXmlLocalDataSource,
    private val remote: PokemonMockRemoteDataSource
) {

    private fun getPokemons(): List<Pokemon> {
        val pokemonsLocal = local.findAll()
        if (pokemonsLocal.isEmpty()) {
            val pokemonsRemote = remote.getPokemons()
            local.saveAll(pokemonsRemote)
            return pokemonsRemote
        } else {
            return pokemonsLocal
        }
    }

    private fun getPokemon(pokemonId: String): Pokemon? {
        val pokemonLocal = local.findById(pokemonId)
        val pokemonRemote = remote.getPokemon(pokemonId)
        return if (pokemonLocal == null) {
            pokemonRemote?.let {
                local.save(it)
                return it
            }
        } else {
            return pokemonRemote
        }
    }
}