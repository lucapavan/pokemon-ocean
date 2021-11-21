package it.pavanluca.pokemonocean.common

/**
 * Created by Luca Pavan on 19/11/21
 */
class PokemonError(
    messageToSend: String = "",
    var messageToShow: Int? = null,
    isSilentError: Boolean = false
) : Throwable(messageToSend) {

    var code: ErrorCode = ErrorCode.GENERIC
        private set

    var isSilent: Boolean = isSilentError
        private set

    companion object {
        fun asNoData(): PokemonError {
            val error = PokemonError(
                messageToSend = "There is no data",
                isSilentError = true
            )
            error.code = ErrorCode.NO_DATA
            return error
        }
    }

    override fun toString(): String {
        return "PokemonError: { code: $code, isSilent: $isSilent, message: $message}"
    }
}

enum class ErrorCode {
    GENERIC,
    NO_DATA
}