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
@SuppressLint("RememberReturnType")
@Composable
fun AnimatedSizeBox() {
    val scope = rememberCoroutineScope()
    var sizeState by remember { mutableStateOf(100.dp) }
    val animatedSize = remember { Animatable(sizeState.value) }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Button(onClick = {
                scope.launch {
                    animatedSize.animateTo(
                        targetValue = if (sizeState == 100.dp) 200f else 100f,
                        animationSpec = tween(durationMillis = 300)
                    )
                    sizeState = if (sizeState == 100.dp) 200.dp else 100.dp
                }
            }) {
                Text("Toggle Size")
            }
            Box(
                modifier = Modifier
                    .size(animatedSize.value.dp)
                    .padding(8.dp)
                    .background(Color.Blue)
            )
        }
    }
}
```