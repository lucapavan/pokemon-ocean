package it.pavanluca.pokemonocean.presentation.extensions

import java.util.*

/**
 * Created by Luca Pavan on 21/11/21
 */
fun String.capitalizeLocale(): String {
    return this.replaceFirstChar {
        if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
    }
}