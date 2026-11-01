package neetcode.arraysandhashing.validanagram;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidAnagramTest {

    private final ValidAnagram solution = new ValidAnagram();

    @Test
    void example1() {
        assertTrue(solution.isAnagram("racecar", "carrace"));
    }

    @Test
    void example2() {
        assertFalse(solution.isAnagram("jar", "jam"));
    }

    @Test
    void example3() {
        assertTrue(solution.isAnagram("x", "x"));
    }

    @Test
    void invalidAnagram() {
        assertFalse(solution.isAnagram("a", "aaa"));
    }

    @Test
    void sameLengthDifferentCounts() {
        assertFalse(solution.isAnagram("aab", "abb"));
    }
}
