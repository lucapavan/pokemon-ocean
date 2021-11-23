package it.pavanluca.pokemonocean.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import it.pavanluca.pokemonocean.R
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