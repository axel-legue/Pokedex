package com.axell.pokedex.core.interactor

import com.axell.pokedex.core.di.DefaultDispatcher
import com.axell.pokedex.core.exception.Failure
import com.axell.pokedex.core.functional.Either
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class UseCase<out Type, in Params> where Type : Any {

    abstract suspend fun run(params: Params): Either<Failure, Type>

    operator fun invoke(
        params: Params,
        scope: CoroutineScope,
        dispatcher: CoroutineDispatcher,
        onResult: (Either<Failure, Type>) -> Unit = {}
    ) {
        scope.launch {
            withContext(dispatcher) {
                onResult(run(params = params))
            }
        }
    }

    class None
}
