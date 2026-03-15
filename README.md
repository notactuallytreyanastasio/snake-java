# Snake (Java)

A terminal snake game written in [Java] — auto-generated from [Temper](https://temperlang.dev) source code.

## How to Play

**Prerequisites:** Java 17+ with Maven

```bash
git clone https://github.com/notactuallytreyanastasio/snake-java.git
cd snake-java
bash run.sh
```

Use **w/a/s/d** keys to steer the snake. No Enter key needed — input is raw mode.

## The Story

This code was not written by a human in Java. It was written once in [Temper](https://temperlang.dev) — a programming language that compiles to 6 other languages — and then automatically compiled and published here by CI.

The same snake game exists in 6 languages, all generated from [one Temper source](https://github.com/notactuallytreyanastasio/temper_snake):

| Language | Repository |
|----------|------------|
| JavaScript | [snake-js](https://github.com/notactuallytreyanastasio/snake-js) |
| Python | [snake-python](https://github.com/notactuallytreyanastasio/snake-python) |
| Lua | [snake-lua](https://github.com/notactuallytreyanastasio/snake-lua) |
| Rust | [snake-rust](https://github.com/notactuallytreyanastasio/snake-rust) |
| C# | [snake-csharp](https://github.com/notactuallytreyanastasio/snake-csharp) |
| Java | [snake-java](https://github.com/notactuallytreyanastasio/snake-java) |

## How It Works

1. The game logic lives in [`temper_snake`](https://github.com/notactuallytreyanastasio/temper_snake) as `.temper.md` files
2. A custom Temper compiler (branch [`do-crimes-to-play-snake`](https://github.com/temperlang/temper/tree/do-crimes-to-play-snake)) adds `sleep()` and `readLine()` I/O primitives
3. GitHub Actions builds the compiler, compiles the game for all 6 backends, runs tests
4. If tests pass, the compiled output is automatically pushed to this repo

Every push to the source repo triggers a fresh build. This code is always in sync.

## Source

[notactuallytreyanastasio/temper_snake](https://github.com/notactuallytreyanastasio/temper_snake)

---

*Auto-generated from commit [`d27a2fddc11e33daafd386ab9534e4084cb1d29b`](https://github.com/notactuallytreyanastasio/temper_snake/commit/d27a2fddc11e33daafd386ab9534e4084cb1d29b)*
