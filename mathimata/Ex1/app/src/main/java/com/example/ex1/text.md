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
fun LoadDataButton() {
    val scope = rememberCoroutineScope()  // Получаем CoroutineScope, //связанный с этим Composable

    Button(onClick = {
        scope.launch {
            val data = fetchDataFromNetwork()  // Асинхронно загружаем данные
// Обработка загруженных данных
        }
    }) {
        Text("Загрузить данные")
    }
}

suspend fun fetchDataFromNetwork(): String {
// Реализация загрузки данных из сети
    return "Некоторые данные"
}
```