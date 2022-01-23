package com.axell.pokedex.core.exception

sealed class Failure {
    object NetworkConnectionError : Failure()
    object ServerError : Failure()
    object Unknown : Failure()
}

