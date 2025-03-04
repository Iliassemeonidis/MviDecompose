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
fun FadeSizeAnimationExample() {
    var isVisible by remember { mutableStateOf(true) }

    // Создаём транзицию
    val transition = updateTransition(targetState = isVisible, label = "FadeSizeAnimation")

    // Анимация прозрачности
    val alpha by transition.animateFloat(
        label = "AlphaAnimation"
    ) { state ->
        if (state) 1f else 0f
    }

    // Анимация размера
    val size by transition.animateDp(
        label = "SizeAnimation"
    ) { state ->
        if (state) 200.dp else 100.dp
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = { isVisible = !isVisible }
        ) {
            Text("Показать / Скрыть Блок")
        }

        Box(
            modifier = Modifier
                .size(size)
                .background(
                    Color.Blue.copy(alpha = alpha)
                )
        ) {
            Text(
                "Анимированный блок",
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = 15.dp),
                color = Color.White
            )
        }
    }
}
```