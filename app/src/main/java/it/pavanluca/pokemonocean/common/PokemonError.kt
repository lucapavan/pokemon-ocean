package it.pavanluca.pokemonocean.common

/**
 * Created by Luca Pavan on 19/11/21
 */
class PokemonError(message: String = "", isSilentError: Boolean = false) : Throwable(message) {

    var code: ErrorCode = ErrorCode.GENERIC
        private set

    var isSilent: Boolean = isSilentError
        private set

    companion object {

        fun asNoData(): PokemonError {
            val error = PokemonError("", true)
            error.code = ErrorCode.NO_DATA
            return error
        }

        fun asNoMoreData(): PokemonError {
            val error = PokemonError("", true)
            error.code = ErrorCode.NO_MORE_DATA
            return error
        }

        fun asInvalidUrl(): PokemonError {
            val error = PokemonError("", true)
            error.code = ErrorCode.INVALID_URL
            return error
        }
    }
}

enum class ErrorCode {
    GENERIC,
    NO_DATA,
    NO_MORE_DATA,
    INVALID_URL
}