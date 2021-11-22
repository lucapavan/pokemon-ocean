package it.pavanluca.pokemonocean.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import it.pavanluca.pokemonocean.R
import it.pavanluca.pokemonocean.common.Constants
import it.pavanluca.pokemonocean.data.pokemon.remote.api.PokemonApi
import it.pavanluca.pokemonocean.data.pokemon.repository.RepositoryImpl
import it.pavanluca.pokemonocean.domain.pokemon.repository.Repository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Luca Pavan on 19/11/21
 */

@Module
@InstallIn(SingletonComponent::class)
object AppComponent {

    @Provides
    @Singleton
    fun provideGlideApp(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
    )

    @Provides
    fun provideFragmentFactory(glide: RequestManager) = PokemonFragmentFactory(glide)
}