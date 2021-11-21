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

fun Int.formattedId(): String {
    return when {
        this < 10 -> "#000$this"
        this in 10..99 -> "#00$this"
        this in 100..999 -> "#0$this"
        else -> "#$this"
    }
}