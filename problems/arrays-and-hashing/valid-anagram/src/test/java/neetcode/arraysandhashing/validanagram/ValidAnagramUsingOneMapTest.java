package neetcode.arraysandhashing.validanagram;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidAnagramUsingOneMapTest {

    private final ValidAnagram solution = new ValidAnagram();

    @Test
    void example1() {
        assertTrue(solution.isAnagramUsingOneMap("racecar", "carrace"));
    }

    @Test
    void example2() {
        assertFalse(solution.isAnagramUsingOneMap("jar", "jam"));
    }

    @Test
    void example3() {
        assertTrue(solution.isAnagramUsingOneMap("x", "x"));
    }

    @Test
    void invalidAnagram() {
        assertFalse(solution.isAnagramUsingOneMap("a", "aaa"));
    }

    @Test
    void sameLengthDifferentCounts() {
        assertFalse(solution.isAnagramUsingOneMap("aab", "abb"));
    }
}
