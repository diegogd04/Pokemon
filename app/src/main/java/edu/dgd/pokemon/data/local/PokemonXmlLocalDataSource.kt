package edu.dgd.pokemon.data.local

import android.content.Context
import com.google.gson.Gson
import edu.dgd.pokemon.domain.Pokemon

class PokemonXmlLocalDataSource(private val context: Context) {

    private val gson = Gson()
    private val sharedPreferences = context.getSharedPreferences("pokemons", Context.MODE_PRIVATE)

    fun save(pokemon: Pokemon) {
        val editor = sharedPreferences.edit()
        editor.putString(pokemon.id, gson.toJson(pokemon))
        editor.apply()
    }

    fun saveAll(pokemons: List<Pokemon>) {
        val editor = sharedPreferences.edit()
        pokemons.forEach {
            editor.putString(it.id, gson.toJson(it))
        }
        editor.apply()
    }

    fun findById(pokemonId: String): Pokemon? {
        return sharedPreferences.getString(pokemonId, null)?.let {
            gson.fromJson(it, Pokemon::class.java)
        }
    }

    fun findAll(): List<Pokemon> {
        val pokemons = mutableListOf<Pokemon>()
        val mapPokemons = sharedPreferences.all as Map<String, String>
        mapPokemons.values.forEach {
            val pokemon = gson.fromJson(it, Pokemon::class.java)
            pokemons.add(pokemon)
        }
        return pokemons
    }

    fun delete() {
        sharedPreferences.edit().clear().apply()
    }

    fun deleteById(pokemonId: String) {
        sharedPreferences.edit().remove(pokemonId).apply()
    }
}