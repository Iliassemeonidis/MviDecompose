```kotlin
@Preview(showBackground = true)
@Composable
fun Example1() {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .size(100.dp)
    )
}
```

```kotlin
@Preview(showBackground = true)
@Composable
fun Example2() {

    Box(
        modifier = Modifier
            .size(100.dp)
            .padding(10.dp)
    )

}
```

```kotlin
@Preview(showBackground = true)
@Composable
fun Example3() {
    Text(
        "Hello",
        modifier = Modifier
            .background(Color.Gray)
            .padding(10.dp)
            .size(100.dp)
    )
}
```

```kotlin
@Composable
fun FlowCollectorExample(viewModel: MyViewModel = viewModel()) {
    val flowData: Flow<MyData> = viewModel.dataFlow
    val data: State<MyData> = flowData.collectAsState(initial = MyData())

    DisposableEffect(Unit) {
        val job = flowData.collect { newData ->
// Обработка данных
        }

        onDispose {
            job.cancel()
        }
    }

// Отображение данных...
    Text("Data: ${data.value}")
}
```