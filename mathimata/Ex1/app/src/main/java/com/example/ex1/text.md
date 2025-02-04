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
@Preview(showBackground = true)
@Composable
fun Example4() {
    Text(
        "Hello",
        modifier = Modifier
            .padding(10.dp)
            .background(Color.Gray)
            .size(100.dp)
    )

}
```