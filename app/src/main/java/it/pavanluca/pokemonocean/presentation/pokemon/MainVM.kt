package it.pavanluca.pokemonocean.presentation.pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.pavanluca.pokemonocean.domain.pokemon.repository.Repository
import javax.inject.Inject

/**
 * Created by Luca Pavan on 19/11/21
 */
class MainVM @Inject constructor(
    val repo: Repository
) : ViewModel() {

    private val _state = MutableLiveData<MainState>()
    val state: LiveData<MainState> = _state

    fun execute(action: MainAction) {
        when(action) {
        }
    }
}