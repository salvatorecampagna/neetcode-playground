# neetcode-playground

**Data structures and algorithms in Java, following the NeetCode curriculum.**

[![Java 25](https://img.shields.io/badge/Java-25-ED8B00?logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/25/)
[![Gradle 9.8.2](https://img.shields.io/badge/Gradle-9.8.2-02303A?logo=gradle&logoColor=white)](https://docs.gradle.org/9.8.2/userguide/userguide.html)
[![JUnit 5](https://img.shields.io/badge/JUnit-5-25A162?logo=junit5&logoColor=white)](https://docs.junit.org/5.13.4/user-guide/)

Java solutions to NeetCode problems. Each problem is a small, self-contained
Gradle project with JUnit 5 tests and a short write-up covering the approach,
why it is correct, and its time and space complexity.

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
| [Contains Duplicate](problems/arrays-and-hashing/contains-duplicate) | Easy | Scaffold |

## Running the tests

Requires JDK 25. Each problem ships its own Gradle wrapper, so nothing else
needs to be installed.

One problem:

```sh
cd problems/arrays/design-dynamic-array
./gradlew test
```

Every problem. The command exits nonzero if any project fails, and lists every
test as `PASSED`, `SKIPPED`, or `FAILED`:

```sh
( status=0
  for p in problems/*/*/; do
    echo "== $p"
    (cd "$p" && ./gradlew test --rerun --console=plain) || { echo "FAILED: $p"; status=1; }
  done
  exit $status )
```

## Layout

```
problems/
  <topic>/                          # NeetCode section, kebab-case (arrays-and-hashing)
    <problem>/                      # problem name, kebab-case (contains-duplicate)
      README.md                     # summary, approach, correctness, complexity
      gradlew, gradle/wrapper/, settings.gradle.kts, build.gradle.kts
      src/main/java/neetcode/<topic>/<problem>/<ClassName>.java
      src/test/java/neetcode/<topic>/<problem>/<ClassName>Test.java
templates/
  java-problem/                     # skeleton for a new problem
```

Packages drop the dashes: `arrays-and-hashing/contains-duplicate` is package
`neetcode.arraysandhashing.containsduplicate`.

## Adding a problem

```sh
T=arrays-and-hashing P=contains-duplicate      # topic and problem folders
D=problems/$T/$P
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
