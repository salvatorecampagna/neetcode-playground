# neetcode-playground

**Data structures and algorithms in Java, following the NeetCode curriculum.**

[![Java 25](https://img.shields.io/badge/Java-25-ED8B00?logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/25/)
[![Gradle 9.8.2](https://img.shields.io/badge/Gradle-9.8.2-02303A?logo=gradle&logoColor=white)](https://docs.gradle.org/9.8.2/userguide/userguide.html)
[![JUnit 5](https://img.shields.io/badge/JUnit-5-25A162?logo=junit5&logoColor=white)](https://docs.junit.org/5.13.4/user-guide/)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

Java solutions to NeetCode problems. Each problem has its own folder with the
solution, JUnit 5 tests, and a short write-up covering the approach, why it is
correct, and its time and space complexity.

## Problems

**Implemented:** solution and enabled tests. **Scaffold:** problem summary, a
stub, and tests that are disabled until the solution is written.

### Arrays

| Problem | Difficulty | Status |
|---|---|---|
| [Design Dynamic Array](problems/arrays/design-dynamic-array) | Easy | Implemented |

### Arrays & Hashing

| Problem | Difficulty | Status |
|---|---|---|
| [Contains Duplicate](problems/arrays-and-hashing/contains-duplicate) | Easy | Implemented |
| [Valid Anagram](problems/arrays-and-hashing/valid-anagram) | Easy | Implemented |
| [Two Sum](problems/arrays-and-hashing/two-sum) | Easy | Implemented |
| [Top K Frequent Elements](problems/arrays-and-hashing/top-k-frequent-elements) | Medium | Implemented |
| [Group Anagrams](problems/arrays-and-hashing/group-anagrams) | Medium | Implemented |
| [Encode and Decode Strings](problems/arrays-and-hashing/encode-and-decode-strings) | Medium | Implemented |

## Running the tests

Requires JDK 25. The Gradle wrapper downloads Gradle on first use.

```sh
./gradlew test --continue                  # every problem
./gradlew :design-dynamic-array:test       # one problem, by folder name
```

Gradle exits nonzero if any test fails, and `--continue` keeps running the
remaining problems. Each test is listed as `PASSED`, `SKIPPED`, or `FAILED`;
add `--rerun` to see the list again when nothing has changed.

## Layout

One multi-project Gradle build: every `problems/<topic>/<problem>` folder is a
subproject named after the problem folder, picked up automatically by
`settings.gradle.kts`. Shared configuration lives in the root
`build.gradle.kts`.

```
build.gradle.kts, settings.gradle.kts, gradlew, gradle/wrapper/
problems/
  <topic>/                          # NeetCode section, kebab-case (arrays-and-hashing)
    <problem>/                      # problem name, kebab-case (contains-duplicate)
      README.md                     # summary, approach, correctness, complexity
      src/main/java/neetcode/<topic>/<problem>/<ClassName>.java
      src/test/java/neetcode/<topic>/<problem>/<ClassName>Test.java
templates/
  java-problem/                     # skeleton for a new problem
```

Packages drop the dashes: `arrays-and-hashing/contains-duplicate` is package
`neetcode.arraysandhashing.containsduplicate`. Problem folder names must be
unique across topics, because they become project names.

## Adding a problem

```sh
D=problems/arrays-and-hashing/contains-duplicate   # problems/<topic>/<problem>
mkdir -p "$D" && cp -R templates/java-problem/. "$D"
rm "$D"/src/main/java/.gitkeep "$D"/src/test/java/.gitkeep
```

Then add the package folders, a stub class whose methods
`throw new UnsupportedOperationException("TODO")`, and a test class annotated
`@Disabled("not started")`. Fill in the problem's `README.md` and add it to the
index above as a scaffold.

Scaffolds keep their tests `@Disabled`, so the build stays green. Remove the
annotation when starting the implementation, and mark the problem as
implemented once all its tests are enabled and passing.

## License

[MIT](LICENSE). Problems are summarized in my own words; the original problem
statements belong to NeetCode.
