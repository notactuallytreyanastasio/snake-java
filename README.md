# Snake (Java)

A terminal snake game written in Java — auto-generated from [Temper](https://temperlang.dev) source code.

## How to Play

**Prerequisites:** Java 17+ with Maven

```bash
git clone https://github.com/notactuallytreyanastasio/snake-java.git
cd snake-java
cd snake-game && mvn compile exec:java
```

Use **w/a/s/d** keys to steer the snake. No Enter key needed — input is raw mode.

## The Story

This code was not written by a human in Java. It was written once in [Temper](https://github.com/temperlang/temper) — a programming language that compiles to 6 other languages — and then automatically compiled and published here by CI.

The same snake game exists in 6 languages, all generated from [one Temper source](https://github.com/notactuallytreyanastasio/temper_snake):

| Language | Repository |
|----------|------------|
| JavaScript | [snake-js](https://github.com/notactuallytreyanastasio/snake-js) |
| Python | [snake-python](https://github.com/notactuallytreyanastasio/snake-python) |
| Lua | [snake-lua](https://github.com/notactuallytreyanastasio/snake-lua) |
| Rust | [snake-rust](https://github.com/notactuallytreyanastasio/snake-rust) |
| C# | [snake-csharp](https://github.com/notactuallytreyanastasio/snake-csharp) |
| **Java** | **snake-java** (you are here) |

## What We Had to Do to the Compiler

Temper had no way to pause execution or read user input. The only I/O primitive was `console.log()`. To write a game loop that ticks every 200ms and reads keyboard input, we added `sleep()` and `readLine()` to the language itself — across all six compilation backends.

The compiler changes live on the [`do-crimes-to-play-snake`](https://github.com/temperlang/temper/tree/do-crimes-to-play-snake) branch ([PR #376](https://github.com/temperlang/temper/pull/376)).

### The Temper Declaration

Two new `@connected` primitives were added to `std/io` in commit [`0f31c89`](https://github.com/temperlang/temper/commit/0f31c89fabc1c938c6a4d2e72c80af658034aa17):

```temper
@connected("stdSleep")
export let sleep(ms: Int): Promise<Empty> { panic() }

@connected("stdReadLine")
export let readLine(): Promise<String?> { panic() }
```

The `@connected` decorator tells the compiler to replace the `panic()` body with a backend-specific native implementation at compile time.

### What Changed for Java

Java maps Temper Promises to `CompletableFuture<T>`. The implementation runs blocking I/O on the `ForkJoinPool`. Three Kotlin compiler files were modified.

**[`StandardNames.kt`](https://github.com/temperlang/temper/blob/0f31c89fabc1c938c6a4d2e72c80af658034aa17/be-java/src/commonMain/kotlin/lang/temper/be/java/StandardNames.kt)** — registered qualified names:

```diff
+// std/io
+val temperStdSleep = temperCore.qualifyKnownSafe("stdSleep")
+val temperStdReadLine = temperCore.qualifyKnownSafe("stdReadLine")
```

**[`JavaSupportNetwork.kt`](https://github.com/temperlang/temper/blob/0f31c89fabc1c938c6a4d2e72c80af658034aa17/be-java/src/commonMain/kotlin/lang/temper/be/java/JavaSupportNetwork.kt)** — added `separateCode` entries and connection map:

```diff
+// std/io support
+val JavaLang.stdSleep by receiver { separateCode(temperStdSleep) }
+val JavaLang.stdReadLine by receiver { separateCode(temperStdReadLine) }
```

```diff
     "stdNetSend" to { it.netCoreStdNetSend },
+    "stdSleep" to { it.stdSleep },
+    "stdReadLine" to { it.stdReadLine },
 )
```

**[`Core.java`](https://github.com/temperlang/temper/blob/0f31c89fabc1c938c6a4d2e72c80af658034aa17/be-java/src/commonMain/resources/lang/temper/be/java/temper-core/src/main/java/temper/core/Core.java)** — the native runtime implementation, added to the monolithic `Core` class that ships with every Java Temper program:

```java
@SuppressWarnings("unchecked")
public static CompletableFuture<Optional<? super Object>> stdSleep(int ms) {
    CompletableFuture<Optional<? super Object>> future = new CompletableFuture<>();
    ForkJoinPool.commonPool().execute(() -> {
        try {
            Thread.sleep(ms);
            future.complete(Optional.empty());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            future.completeExceptionally(e);
        }
    });
    return future;
}

public static CompletableFuture<String> stdReadLine() {
    CompletableFuture<String> future = new CompletableFuture<>();
    ForkJoinPool.commonPool().execute(() -> {
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
            String line = reader.readLine();
            future.complete(line);
        } catch (IOException e) {
            future.complete(null);
        }
    });
    return future;
}
```

The return type of `stdSleep` is `CompletableFuture<Optional<? super Object>>` because Temper's `Empty` type maps to `Tuple<object?>` through the `connectedTypes` map, and the generated Java code expects that signature. `Thread.sleep` runs on the `ForkJoinPool` common pool; the `CompletableFuture` resolves when the sleep completes. `stdReadLine` wraps a `BufferedReader` around `System.in` on a pool thread.

## How It Works

1. The game logic lives in [`temper_snake`](https://github.com/notactuallytreyanastasio/temper_snake) as `.temper.md` files
2. A custom Temper compiler (branch [`do-crimes-to-play-snake`](https://github.com/temperlang/temper/tree/do-crimes-to-play-snake)) adds the `sleep()` and `readLine()` I/O primitives
3. GitHub Actions builds the compiler, compiles the game for all 6 backends, runs tests
4. If tests pass, the compiled output is automatically pushed to this repo

Every push to the source repo triggers a fresh build. This code is always in sync.

## Source

[notactuallytreyanastasio/temper_snake](https://github.com/notactuallytreyanastasio/temper_snake)
