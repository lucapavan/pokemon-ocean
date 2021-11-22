package it.pavanluca.pokemonocean.data.pokemon.room.mappers

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import it.pavanluca.pokemonocean.data.pokemon.room.entity.*

/**
 * Created by Luca Pavan on 22/11/21
 */
@ProvidedTypeConverter
class EntityConverters(var moshi: Moshi) {

    //region AbilitiesConverter
    @TypeConverter
    fun fromAbilities(abilities: List<AbilityEntity>): String {
        val type = Types.newParameterizedType(List::class.java, AbilityEntity::class.java)
        val jsonAdapter: JsonAdapter<List<AbilityEntity>> = moshi.adapter(type)
        return jsonAdapter.toJson(abilities)
    }

    @TypeConverter
    fun toAbilities(json: String): List<AbilityEntity>? {
        val type = Types.newParameterizedType(List::class.java, AbilityEntity::class.java)
        val jsonAdapter: JsonAdapter<List<AbilityEntity>> = moshi.adapter(type)
        return jsonAdapter.fromJson(json)
    }
    //endregion

    //region MoviesConverter
    @TypeConverter
    fun fromMovies(moves: List<MoveEntity>): String {
        val type = Types.newParameterizedType(List::class.java, MoveEntity::class.java)
        val jsonAdapter: JsonAdapter<List<MoveEntity>> = moshi.adapter(type)
        return jsonAdapter.toJson(moves)
    }

    @TypeConverter
    fun toMovies(json: String): List<MoveEntity>? {
        val type = Types.newParameterizedType(List::class.java, MoveEntity::class.java)
        val jsonAdapter: JsonAdapter<List<MoveEntity>> = moshi.adapter(type)
        return jsonAdapter.fromJson(json)
    }
    //endregion

    //region StatsConverter
    @TypeConverter
    fun fromStats(stats: List<StatEntity>): String {
        val type = Types.newParameterizedType(List::class.java, StatEntity::class.java)
        val jsonAdapter: JsonAdapter<List<StatEntity>> = moshi.adapter(type)
        return jsonAdapter.toJson(stats)
    }

    @TypeConverter
    fun toStats(json: String): List<StatEntity>? {
        val type = Types.newParameterizedType(List::class.java, StatEntity::class.java)
        val jsonAdapter: JsonAdapter<List<StatEntity>> = moshi.adapter(type)
        return jsonAdapter.fromJson(json)
    }
    //endregion

    //region TypesConverter
    @TypeConverter
    fun fromTypes(types: List<TypeEntity>): String {
        val type = Types.newParameterizedType(List::class.java, TypeEntity::class.java)
        val jsonAdapter: JsonAdapter<List<TypeEntity>> = moshi.adapter(type)
        return jsonAdapter.toJson(types)
    }

    @TypeConverter
    fun toTypes(json: String): List<TypeEntity>? {
        val type = Types.newParameterizedType(List::class.java, TypeEntity::class.java)
        val jsonAdapter: JsonAdapter<List<TypeEntity>> = moshi.adapter(type)
        return jsonAdapter.fromJson(json)
    }
    //endregion

    //region PokemonPagesConverter
    @TypeConverter
    fun fromPokemonPages(pages: List<String>): String {
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val jsonAdapter: JsonAdapter<List<String>> = moshi.adapter(type)
        return jsonAdapter.toJson(pages)
    }

    @TypeConverter
    fun toPokemonPages(json: String): List<String>? {
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        val jsonAdapter: JsonAdapter<List<String>> = moshi.adapter(type)
        return jsonAdapter.fromJson(json)
    }
    //endregion
}
