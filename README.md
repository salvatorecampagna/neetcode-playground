# neetcode-playground

**Data structures and algorithms in Java, following NeetCode.**

Java solutions to NeetCode problems, each with JUnit 5 tests and a short
write-up of the approach and its complexity.

## Problems

| Topic | Problem | Difficulty |
|---|---|---|
| Arrays | [Design Dynamic Array](problems/arrays/design-dynamic-array) | Easy |
| Arrays & Hashing | [Contains Duplicate](problems/arrays-and-hashing/contains-duplicate) | Easy |

## Layout

Each problem is a standalone Gradle project (Java 25, JUnit 5). The root holds
only docs and the template.

```
problems/
  <topic>/                          # NeetCode section, kebab-case (arrays-and-hashing)
    <problem>/                      # problem name, kebab-case (contains-duplicate)
      README.md                     # summary, approach, complexity
      gradlew, gradle/wrapper/, settings.gradle.kts, build.gradle.kts
      src/main/java/neetcode/<topic>/<problem>/<ClassName>.java
      src/test/java/neetcode/<topic>/<problem>/<ClassName>Test.java
templates/
  java-problem/                     # skeleton for a new problem
```

Packages drop the dashes: `arrays-and-hashing/contains-duplicate` is package
`neetcode.arraysandhashing.containsduplicate`.

## Starting a problem

```sh
T=arrays-and-hashing P=contains-duplicate      # topic and problem folders
D=problems/$T/$P
mkdir -p "$D" && cp -R templates/java-problem/. "$D"
rm "$D"/src/main/java/.gitkeep "$D"/src/test/java/.gitkeep
```

Then add the package folders, a stub class whose methods
`throw new UnsupportedOperationException("TODO")`, and a test class annotated
`@Disabled("not started")`. Fill in the problem's `README.md` and add a row to
the table above.

**Unfinished problems** keep their tests `@Disabled`, so the build stays green.
Remove the annotation when you start the problem. To try a single test early,
move `@Disabled` from the class to the remaining test methods.

## Running

```sh
cd problems/<topic>/<problem>
./gradlew test
```

Every problem at once:

```sh
for p in problems/*/*/; do (cd "$p" && ./gradlew -q test) || echo "FAILED: $p"; done
```
