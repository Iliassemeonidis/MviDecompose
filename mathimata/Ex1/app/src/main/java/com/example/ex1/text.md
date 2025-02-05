```kotlin
@Composable
fun LocationListener(locationManager: LocationManager, listener: LocationListener) {
    DisposableEffect(locationManager) {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0L, 0f, listener)
        onDispose {
            locationManager.removeUpdates(listener)
        }
    }
}
```

```kotlin
@Composable
fun TimerExample() {
    DisposableEffect(Unit) {
        val timer = Timer()
        timer.schedule(timerTask {
// Выполнение действия
        }, 1000L)

        onDispose {
            timer.cancel()
        }
    }
}
```

```kotlin
@Composable
fun AutoFocusTextField() {
    val focusRequester = remember { FocusRequester() }
    val isFocused = remember { mutableStateOf(false) }

    Column {
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Введите текст") },
            modifier = Modifier
                .focusRequester(focusRequester)
                .padding(16.dp)
        )

        Button(
            onClick = { isFocused.value = true },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Фокусируй")
        }
    }

// Побочный эффект для управления фокусом
    SideEffect {
        if (isFocused.value) {
            focusRequester.requestFocus()
            isFocused.value = false // Сброс после фокусировки
        }
    }
}
```

```kotlin
@Composable
fun UpdateExternalState(someState: MutableState<Int>) {
    SideEffect {
// Предположим, что externalDataStorage - это внешнее хранилище данных
        externalDataStorage.updateData("count", someState.value)
    }
}
```

```kotlin
@Composable
fun ActiveTasksList(tasks: List<Task>) {
    val activeTasks = remember(tasks) {
        derivedStateOf { tasks.filter { !it.isCompleted } }
    }

    LazyColumn {
        items(activeTasks.value) { task ->
            Text("Задание: ${task.name}")
        }
    }
}
```

```kotlin
@Composable
fun UserProfile(userId: String) {
    val userData = produceState<Resource<User>>(initialValue = Resource.Loading(), key = userId) {
        val userResource = try {
            Resource.Success(loadUserFromNetwork(userId))
        } catch (e: Exception) {
            Resource.Error("Не удалось загрузить данные")
        }
        value = userResource
    }

    when (val result = userData.value) {
        is Resource.Success -> Text("Пользователь: ${result.data.name}")
        is Resource.Error -> Text("Ошибка: ${result.message}")
        is Resource.Loading -> CircularProgressIndicator()
    }
}
```