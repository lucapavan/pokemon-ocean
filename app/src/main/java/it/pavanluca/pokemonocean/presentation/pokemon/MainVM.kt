package it.pavanluca.pokemonocean.presentation.pokemon

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Luca Pavan on 19/11/21
 */
class MainVM : ViewModel() {

    private val _state = MutableLiveData<MainState>()
    val state: LiveData<MainState> = _state

    fun execute(action: MainAction) {
        when(action) {
        }
    }
}