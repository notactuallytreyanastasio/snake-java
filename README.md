# Snake (Java)

A terminal snake game written in Java — auto-generated from [Temper](https://temperlang.dev) source code.

## How to Play

**Prerequisites:** Java 17+ with Maven

```bash
git clone https://github.com/notactuallytreyanastasio/snake-java.git
cd snake-java
bash run.sh
```

Use **w/a/s/d** keys to steer the snake. No Enter key needed — input is raw mode.

## What Is This?

This code was not written by a human in Java. It was written once in [Temper](https://temperlang.dev) — a programming language that compiles to JavaScript, Python, Lua, Rust, Java, and C# — and then automatically compiled to Java and published here by CI.

Temper had no way to pause execution or read input. The only I/O was `console.log()`. To play snake, we had to add `sleep(ms)` and `readLine()` to the language itself — modifying the Temper compiler across all six backends.

## What Changed in the Temper Compiler for Java

Java maps Temper Promises to `CompletableFuture<T>`. Blocking I/O runs on the `ForkJoinPool.commonPool()`.

**Compiler changes:**
- `JavaSupportNetwork.kt`: `separateCode` entries for `stdSleep` and `stdReadLine`
- `StandardNames.kt`: qualified names `temperStdSleep` and `temperStdReadLine`
- `Core.java`: `waitUntilTasksComplete()` originally had a hard 10-second timeout (`awaitQuiescence(10L, TimeUnit.SECONDS)`) that killed any program after 10 seconds. Fixed to loop until the pool is truly quiescent

**Runtime** (`Core.java`): `stdSleep` submits `Thread.sleep(ms)` to the fork-join pool. `stdReadLine` uses `stty raw -echo` via `ProcessBuilder` for single-keypress input on Unix, with Ctrl+C detection. The return type `CompletableFuture<Optional<? super Object>>` matches Temper's `Promise<Empty>` through the connected types map.

## All 6 Backends

The same snake game exists in 6 languages, all generated from [one Temper source](https://github.com/notactuallytreyanastasio/temper_snake):

| Language | Repository |
|----------|------------|
| JavaScript | [snake-js](https://github.com/notactuallytreyanastasio/snake-js) |
| Python | [snake-python](https://github.com/notactuallytreyanastasio/snake-python) |
| Lua | [snake-lua](https://github.com/notactuallytreyanastasio/snake-lua) |
| Rust | [snake-rust](https://github.com/notactuallytreyanastasio/snake-rust) |
| C# | [snake-csharp](https://github.com/notactuallytreyanastasio/snake-csharp) |
| Java | [snake-java](https://github.com/notactuallytreyanastasio/snake-java) |

## Source

- Game source: [notactuallytreyanastasio/temper_snake](https://github.com/notactuallytreyanastasio/temper_snake)
- Compiler branch: [`do-more-crimes-to-play-snake-multiplayer`](https://github.com/temperlang/temper/tree/do-more-crimes-to-play-snake-multiplayer)

---

*Auto-generated from commit [`43c00a9c8607047854c74f427095d22fb00b36f9`](https://github.com/notactuallytreyanastasio/temper_snake/commit/43c00a9c8607047854c74f427095d22fb00b36f9)*
