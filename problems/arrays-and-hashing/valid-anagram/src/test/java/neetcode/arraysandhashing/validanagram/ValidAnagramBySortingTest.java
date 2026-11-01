package neetcode.arraysandhashing.validanagram;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ValidAnagramBySortingTest {

    private final ValidAnagram solution = new ValidAnagram();

    @Test
    void example1() {
        assertTrue(solution.isAnagramBySorting("racecar", "carrace"));
    }

    @Test
    void example2() {
        assertFalse(solution.isAnagramBySorting("jar", "jam"));
    }

    @Test
    void example3() {
        assertTrue(solution.isAnagramBySorting("x", "x"));
    }

    @Test
    void invalidAnagram() {
        assertFalse(solution.isAnagramBySorting("a", "aaa"));
    }

    @Test
    void sameLengthDifferentCounts() {
        assertFalse(solution.isAnagramBySorting("aab", "abb"));
    }
}
