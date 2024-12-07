```java
 button.setOnClickListener(new OnClickListener() {
    @Override
    public void onClick (View view){
        //Обработка действия по нажатию
    }
}
```

```kotlin
 button.setOnClickListener {
    //Обработка действия по нажатию
}
 ```

```kotlin 
fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    // проходим по всей длине строки 
    for (index in indices) {
        // получаем по индексу элемент 
        val element = get(index)
        //тут мы вызываем нашу лямбду и если результат ее true то добавляем в наш sb 
        if (predicate(element)) sb.append(element)
    }
    return sb.toString()
}
println("abc3d5".filter { it in 'a'..'n' })
//Результат == abcd
```

```kotlin 
data class Company(val companyName: String) // название компании 
data class Factory(val isImpost: Boolean) // фабрика размещенная за границей 
data class Device(val model: String) // модель с местом производства

fun createDevice(
    company: Company,
    factory: Factory,
    assemble: (Company, Factory) -> Device
): Device {
    return assemble(company, factory)
}
fun factoryCycle(
    company: String,
    isImport: Boolean
) {
    createDevice(Company(company), Factory(isImport)) { _, factory ->
        when (
            factory.isImpost) {
            true -> Device("Chinis model")else -> Device("USA model")
        }
    }
}
```

```kotlin
data class Company(val companyName: String) // название компании 
data class Factory(val isImpost: Boolean) // фабрика размещенная за границей 
data class Device(val model: String) // модельс местом производства 

fun createDevice(
    company: Company, factory: Factory,
    assemble: (Company, Factory) -> Device
): Device {
    return assemble(company, factory)
}
fun factoryCycle(
    company: String,
    isImport: Boolean
) {
    createDevice(Company(company), Factory(isImport)) { _, factory ->
        when (
            factory.isImpost) {
            true -> Device("Chinis model")else -> Device("USA model")
        }
    }
}
```

```java 
public static final void factoryCycle(@NotNull String company, boolean isImport) {
    Intrinsics.checkNotNullParameter(company, "company");

    Company var2 = new Company(company);
    Factory factory$iv = new Factory(isImport);
    int $i$f$createDevice = false;
    int var7 = false;
    boolean var8 = factory$iv.isImpost();
    if (var8) {
        new Device("Chinis model");
    } else {
        new Device("USA model");
    }
}
```

```kotlin 
inline fun printMassageWithPrefix(message: Collection<String>, prefix: String) {
    message.forEach { println("$prefix $it") }
}
//Тут компилятор нам подскажет -
//«Ожидаемое влияние на производительность от встраивания незначительно. Встраивание лучше всего
//работает для функций с параметрами функциональных типов.»
```

```kotlin 
fun inlineFun(body: () -> Unit) {
    body.invoke()
}
fun foo() {
    inlineFun {
        return // тут компилятор нас уведомит, что так делать нельзя!
    }
}
```

```kotlin
 fun inlineFun(body: () -> Unit) {
    body.invoke()
}
fun foo() {
    inlineFun { return@inlineFun }
}
```

```kotlin 
fun inlineFun(body: () -> Unit) {
    body.invoke()
}
fun foo() {
    inlineFun { return@inlineFun }
}
```

```kotlin
 private inline fun crossInlineFun(body: () -> String) {
    val func = { "crossInline func code, " + body.invoke() }
    regularFun(func)
}
```

```kotlin
 private inline fun crossInlineFun(crossinline body: () -> String) {
    val func = { "crossInline func code, " + body.invoke() }
    regularFun(func)
}
fun testCrossInline() {
    crossInlineFun { "external code" }
}
```

```kotlin
 private inline fun crossInlineFun(noinline body: () -> String) {
    val func = {
        "crossInline func code, " + body.invoke()
    } regularFun (func)
}
fun testCrossInline() {
    crossInlineFun { "external code" }
}
```

```kotlin
 private inline fun crossInlineFun(crossinline body: () -> String) {
    val func = { "crossInline func code, " + body.invoke() } regularFun (func)
}
fun testCrossInline() {
    crossInlineFun { "external code" }
}
```

```java 
public final void testCrossInline() {
    Function0 func = (Function0) (new Function0() {
        public final String invoke() {
            return (new StringBuilder())
                    .append("crossInline func code, ")
                    .append("external code").toString();
        }
    });
    regularFun(func);
}
```

```java 
public final void testCrossInline() {
    Function0 func = (Function0) (new Function0() {
        public final String invoke() {
            return (new StringBuilder())
                    .append("crossInline func code, ")
                    .append("external code").toString();
        }
    });
    regularFun(func);
}
```

```kotlin
 @Suppress("UNCHECKED_CAST")
fun <T : ViewGroup> View.findParentOfType(clazz: Class<T>): T? {
    var p = this.parent while (p != null && !clazz.isInstance(p)) {
        p = p.parent
    }
    return p as? T
}
```

```kotlin 
inline fun <reified T : ViewGroup> View.findParentOfType(): T? {
    var p = this.parent while (
        p != null && p !is T) {
        p = p.parent
    }
    return p as? T
}
```

```java
private final void crossInlineFun(final Function0 body) {
    int $i$f$crossInlineFun = false;
    Function0 func = (Function0) (new Function0() {
        public final String invoke() {
            return "crossInline func code, " + (String) body.invoke();
        }

        // $FF: synthetic method
        // $FF: bridge method
        public Object invoke() {
            return this.invoke();
        }
    });
    this.regularFun(func);
}
```

слайд 10

```java

@NotNull
public static final Device createDevice(@NotNull Company company, @NotNull Factory factory, @NotNull Function2 assemble) {
    Intrinsics.checkNotNullParameter(company, "company");
    Intrinsics.checkNotNullParameter(factory, "factory");
    Intrinsics.checkNotNullParameter(assemble, "assemble");
    return (Device) assemble.invoke(company, factory);
}

public static final void factoryCycle(@NotNull String company, boolean isImport) {
    Intrinsics.checkNotNullParameter(company, "company");
    createDevice(new Company(company), new Factory(isImport), (Function2) null.INSTANCE);
}
```

```kotlin
inline fun foo(inlined: () -> Unit, noinline notInlined: () -> Unit) {
}
```