package neetcode.arraysandhashing.containsduplicate;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ContainsDuplicateBySortingTest {

    private final ContainsDuplicate solution = new ContainsDuplicate();

    @Test
    void example1() {
        assertTrue(solution.hasDuplicateBySorting(new int[] {1, 2, 3, 3}));
    }

    @Test
    void example2() {
        assertFalse(solution.hasDuplicateBySorting(new int[] {1, 2, 3, 4}));
    }

    @Test
    void duplicateAtOppositeEnds() {
        assertTrue(solution.hasDuplicateBySorting(new int[] {7, 1, 2, 3, 7}));
    }

    @Test
    void allEqual() {
        assertTrue(solution.hasDuplicateBySorting(new int[] {5, 5, 5, 5}));
    }

    @Test
    void singleElement() {
        assertFalse(solution.hasDuplicateBySorting(new int[] {42}));
    }

    @Test
    void empty() {
        assertFalse(solution.hasDuplicateBySorting(new int[] {}));
    }

    @Test
    void negativeNumbers() {
        assertTrue(solution.hasDuplicateBySorting(new int[] {-1, 0, 1, -1}));
        assertFalse(solution.hasDuplicateBySorting(new int[] {-3, -2, -1, 0}));
    }

    @Test
    void extremeValues() {
        assertTrue(solution.hasDuplicateBySorting(new int[] {-1_000_000_000, 0, -1_000_000_000}));
        assertFalse(solution.hasDuplicateBySorting(new int[] {-1_000_000_000, 1_000_000_000}));
    }

    @Test
    void arrayIsSortedHasNoDuplicate() {
        int[] a = new int[] {1, 10, 7, 4, 5, 8, 9, 12, 3, 0};
        int[] b = a.clone();
        Arrays.sort(b);
        assertFalse(solution.hasDuplicateBySorting(a));
        assertArrayEquals(b, a);
    }

    @Test
    void arrayIsSortedHasDuplicate() {
        int[] a = new int[] {1, 10, 7, 4, 5, 4, 9, 1, 3, 0};
        int[] b = a.clone();
        Arrays.sort(b);
        assertTrue(solution.hasDuplicateBySorting(a));
        assertArrayEquals(b, a);
    }
}
