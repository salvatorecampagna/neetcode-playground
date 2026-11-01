package neetcode.arraysandhashing.validanagram;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.util.List;

class ValidAnagramUsingTwoCountArraysTest {

    record TestCase(String s, int[] charmap) {}

    private final ValidAnagram solution = new ValidAnagram();

    @Test
    void example1() {
        assertTrue(solution.isAnagramUsingTwoCountArrays("racecar", "carrace"));
    }

    @Test
    void example2() {
        assertFalse(solution.isAnagramUsingTwoCountArrays("jar", "jam"));
    }

    @Test
    void example3() {
        assertTrue(solution.isAnagramUsingTwoCountArrays("x", "x"));
    }

    @Test
    void invalidAnagram() {
        assertFalse(solution.isAnagramUsingTwoCountArrays("a", "aaa"));
    }

    @Test
    void sameLengthDifferentCounts() {
        assertFalse(solution.isAnagramUsingTwoCountArrays("aab", "abb"));
    }

    @Test
    void testCharMapArray() {
        var tests = List.of(
                new TestCase("abcdefghi", new int[] {
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0
                }),
                new TestCase("abba", new int[] {
                        2, 2, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0
                }),
                new TestCase("zza", new int[] {
                        1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 2
                }),
                new TestCase("abcdefghijklmnopqrstuvwxyz", new int[] {
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
