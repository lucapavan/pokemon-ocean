package it.pavanluca.pokemonocean.di

import android.content.Context
import androidx.room.Room
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import it.pavanluca.pokemonocean.data.pokemon.room.database.PokemonDatabase
import it.pavanluca.pokemonocean.data.pokemon.room.mappers.EntityConverters
import javax.inject.Singleton

/**
 * Created by Luca Pavan on 22/11/21
 */

@InstallIn(SingletonComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun providePokemonDatabase(
        @ApplicationContext context: Context,
        entityConverters: EntityConverters
    ): PokemonDatabase {
        return Room
            .databaseBuilder(
                context,
                PokemonDatabase::class.java,
                PokemonDatabase.DATABASE_NAME
            )
            .addTypeConverter(entityConverters)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePokemonDao(pokemonDatabase: PokemonDatabase) = pokemonDatabase.pokemonDao()

    @Singleton
    @Provides
    fun providePokemonNamesDao(pokemonDatabase: PokemonDatabase) = pokemonDatabase.pokemonNamesDao()

    @Provides
    fun provideEntityConverters(moshi: Moshi) = EntityConverters(moshi)
}