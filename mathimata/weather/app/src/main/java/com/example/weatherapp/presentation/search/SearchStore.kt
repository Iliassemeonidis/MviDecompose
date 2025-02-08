package com.example.weatherapp.presentation.search

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.weatherapp.presentation.search.SearchStore.Intent
import com.example.weatherapp.presentation.search.SearchStore.Label
import com.example.weatherapp.presentation.search.SearchStore.State

internal interface SearchStore : Store<Intent, State, Label> {

    sealed interface Intent {}

    data class State(val todo: Unit)

    sealed interface Label {}
}

internal class SearchStoreFactory(
    private val storeFactory: StoreFactory
) {
    fun create(): SearchStore =
        object : SearchStore, Store<Intent, State, Label> by storeFactory.create(
            name = "SearchStoreFactory",
            initialState = State(Unit),
            bootstrapper = BootstrapperImpl(),
            reducer = ReducerImpl,
            executorFactory = ::ExecutorIml
        ) {}

    sealed interface Action {}
    sealed interface Mgs {}

    internal inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            TODO("Not yet implemented")
        }
    }

    internal object ReducerImpl : Reducer<State, Mgs> {
        override fun State.reduce(msg: Mgs): State {
            TODO("Not yet implemented")
        }
    }

    internal class ExecutorIml : CoroutineExecutor<Intent, Action, State, Mgs, Label>() {
        override fun executeAction(action: Action, getState: () -> State) {
            super.executeAction(action, getState)
        }

        override fun executeIntent(intent: Intent, getState: () -> State) {
            super.executeIntent(intent, getState)
        }
    }

}
