package it.pavanluca.pokemonocean.di

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
inline fun <reified VM : ViewModel> Fragment.provideViewModels(
    noinline ownerProducer: () -> ViewModelStoreOwner = { this },
    crossinline factoryProducer: () -> Provider<VM>
): Lazy<VM> = viewModels(
    ownerProducer,
    factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>) =
                factoryProducer().get() as T
        }
    }
)