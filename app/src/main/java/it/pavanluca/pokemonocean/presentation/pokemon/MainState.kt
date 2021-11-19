package it.pavanluca.pokemonocean.presentation.pokemon

/**
 * Created by Luca Pavan on 19/11/21
 */
sealed class MainState {
    object Empty : MainState()
    object Loading : MainState()
    class Error(val message: String): MainState()
}
