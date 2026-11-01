package neetcode.arraysandhashing.validanagram;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidAnagramUsingOneCountArrayTest {

    private final ValidAnagram solution = new ValidAnagram();

    @Test
    void example1() {
        assertTrue(solution.isAnagramUsingOneCountArray("racecar", "carrace"));
    }

    @Test
    void example2() {
        assertFalse(solution.isAnagramUsingOneCountArray("jar", "jam"));
    }

    @Test
    void example3() {
        assertTrue(solution.isAnagramUsingOneCountArray("x", "x"));
    }

    @Test
    void invalidAnagram() {
        assertFalse(solution.isAnagramUsingOneCountArray("a", "aaa"));
    }

    @Test
    void sameLengthDifferentCounts() {
        assertFalse(solution.isAnagramUsingOneCountArray("aab", "abb"));
    }
}
