package neetcode.arraysandhashing.validanagram;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class ValidAnagramUsingTwoMapsTest {

    record TestCase(String s, Map<Character, Integer> charmap) {}

    private final ValidAnagram solution = new ValidAnagram();

    @Test
    void example1() {
        assertTrue(solution.isAnagramUsingTwoMaps("racecar", "carrace"));
    }

    @Test
    void example2() {
        assertFalse(solution.isAnagramUsingTwoMaps("jar", "jam"));
    }

    @Test
    void example3() {
        assertTrue(solution.isAnagramUsingTwoMaps("x", "x"));
    }

    @Test
    void invalidAnagram() {
        assertFalse(solution.isAnagramUsingTwoMaps("a", "aaa"));
    }

    @Test
    void sameLengthDifferentCounts() {
        assertFalse(solution.isAnagramUsingTwoMaps("aab", "abb"));
    }

    @Test
    void testCharMap() {
        var tests = List.of(
                new TestCase("ab", Map.of(
                        'a', 1,
                        'b', 1
                )),
                new TestCase("abba", Map.of(
                        'a', 2,
                        'b', 2
                )),
                new TestCase("abb", Map.of(
                        'a', 1,
                        'b', 2
                )),
                new TestCase("AAaBbbb", Map.of(
                        'A', 2,
                        'a', 1,
                        'B', 1,
                        'b', 3
                ))
        );

        for (var test: tests) {
            Assertions.assertEquals(test.charmap, solution.charmap(test.s));
        }
    }
}
