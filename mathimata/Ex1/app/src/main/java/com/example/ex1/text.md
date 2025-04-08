```kotlin
fun main() = runBlocking {
    val flow = flow {
        emit(1)
        throw RuntimeException("Error!")
    }

    flow
        .catch { e -> println("Caught exception: $e") } // Ловим ошибку
        .collect { value -> println("Received: $value") }
}
```

```
Вывод:
Received: 1
Caught exception : java.lang.RuntimeException: Error!
```