package com.example.weatherapp.presentation.details

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.weatherapp.presentation.details.DetailsStore.Intent
import com.example.weatherapp.presentation.details.DetailsStore.Label
import com.example.weatherapp.presentation.details.DetailsStore.State

internal interface DetailsStore : Store<Intent, State, Label> {
    sealed interface Intent {}

    data class State(val todo: Unit)

    sealed interface Label {}
}

internal class DetailsStoreFactory(
    private val storeFactory: StoreFactory
) {

    fun create(): DetailsStore = object : DetailsStore,
        Store<Intent, State, Label> by storeFactory.create(
            name = "DetailsStoreFactory",
            initialState = State(Unit),
            bootstrapper = BootstrapperImpl(),
            reducer = ReducerImpl,
            executorFactory = ::ExecutorImpl,
        ) {}

    sealed interface Action {}

    sealed interface Mgs {}

    internal inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {}

    }

    internal object ReducerImpl : Reducer<State, Mgs> {
        override fun State.reduce(msg: Mgs) = State(Unit)
    }

    internal inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Mgs, Label>() {
        override fun executeAction(action: Action, getState: () -> State) {
            super.executeAction(action, getState)
        }

        override fun executeIntent(intent: Intent, getState: () -> State) {
            super.executeIntent(intent, getState)
        }
    }
}

