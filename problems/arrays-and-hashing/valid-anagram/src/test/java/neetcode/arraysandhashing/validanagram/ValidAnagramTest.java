package neetcode.arraysandhashing.validanagram;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Stream;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ValidAnagramTest {

    record CharMapCase(String s, Map<Character, Integer> charmap) {}

    record CharMapArrayCase(String s, int[] charmap) {}

    private static final ValidAnagram solution = new ValidAnagram();

    static Stream<Named<BiPredicate<String, String>>> implementations() {
        return Stream.of(
                Named.of("isAnagram", solution::isAnagram),
                Named.of("isAnagramUsingTwoMaps", solution::isAnagramUsingTwoMaps),
                Named.of("isAnagramBySorting", solution::isAnagramBySorting),
                Named.of("isAnagramUsingOneMap", solution::isAnagramUsingOneMap),
                Named.of("isAnagramUsingTwoCountArrays", solution::isAnagramUsingTwoCountArrays),
                Named.of("isAnagramUsingOneCountArray", solution::isAnagramUsingOneCountArray));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void example1(BiPredicate<String, String> isAnagram) {
        assertTrue(isAnagram.test("racecar", "carrace"));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void example2(BiPredicate<String, String> isAnagram) {
        assertFalse(isAnagram.test("jar", "jam"));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void example3(BiPredicate<String, String> isAnagram) {
        assertTrue(isAnagram.test("x", "x"));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void invalidAnagram(BiPredicate<String, String> isAnagram) {
        assertFalse(isAnagram.test("a", "aaa"));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void sameLengthDifferentCounts(BiPredicate<String, String> isAnagram) {
        assertFalse(isAnagram.test("aab", "abb"));
    }

    @Test
    void testCharMap() {
        var tests = List.of(
                new CharMapCase("ab", Map.of(
                        'a', 1,
                        'b', 1
                )),
                new CharMapCase("abba", Map.of(
                        'a', 2,
                        'b', 2
                )),
                new CharMapCase("abb", Map.of(
                        'a', 1,
                        'b', 2
                )),
                new CharMapCase("AAaBbbb", Map.of(
                        'A', 2,
                        'a', 1,
                        'B', 1,
                        'b', 3
                ))
        );

        for (var test: tests) {
            assertEquals(test.charmap, solution.charmap(test.s));
        }
    }

    @Test
    void testCharMapArray() {
        var tests = List.of(
                new CharMapArrayCase("abcdefghi", new int[] {
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0
                }),
                new CharMapArrayCase("abba", new int[] {
                        2, 2, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0
                }),
                new CharMapArrayCase("zza", new int[] {
                        1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 2
                }),
                new CharMapArrayCase("abcdefghijklmnopqrstuvwxyz", new int[] {
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1
                })
        );

        for (var test: tests) {
            assertArrayEquals(test.charmap, solution.charMapArray(test.s));
        }
    }
}
