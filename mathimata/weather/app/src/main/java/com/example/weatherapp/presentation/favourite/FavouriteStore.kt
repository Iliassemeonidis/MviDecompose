package com.example.weatherapp.presentation.favourite

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.weatherapp.presentation.favourite.FavouriteStore.Intent
import com.example.weatherapp.presentation.favourite.FavouriteStore.State
import com.example.weatherapp.presentation.favourite.FavouriteStore.Label

internal interface FavouriteStore : Store<Intent, State, Label> {
    sealed interface Intent {}
    data class State(val todo: Unit)
    sealed interface Label {}

}

internal class FavouriteStoreFactory(
    private val storeFactory: StoreFactory
) {
    fun create(): FavouriteStore = object : FavouriteStore,
        Store<Intent, State, Label> by storeFactory.create(
            name = "FavouriteStoreFactory",
            initialState = State(Unit),
            bootstrapper = BootstrapperImpl(),
            reducer = ReducerImpl,
            executorFactory = ::ExecutorImpl,
        ) {}

    sealed interface Action {}

    sealed interface Mgs {}

    inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            TODO("Not yet implemented")
        }
    }

    internal object ReducerImpl : Reducer<State, Mgs> {
        override fun State.reduce(msg: Mgs): State {
            TODO("Not yet implemented")
        }

    }

    internal inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Mgs, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            super.executeIntent(intent, getState)
        }

        override fun executeAction(action: Action, getState: () -> State) {
            super.executeAction(action, getState)
        }
    }


}