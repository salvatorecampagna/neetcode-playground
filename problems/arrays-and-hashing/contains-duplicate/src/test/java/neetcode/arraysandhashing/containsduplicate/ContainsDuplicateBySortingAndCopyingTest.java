package neetcode.arraysandhashing.containsduplicate;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ContainsDuplicateBySortingAndCopyingTest {

    private final ContainsDuplicate solution = new ContainsDuplicate();

    @Test
    void example1() {
        assertTrue(solution.hasDuplicateBySortingAndCopying(new int[] {1, 2, 3, 3}));
    }

    @Test
    void example2() {
        assertFalse(solution.hasDuplicateBySortingAndCopying(new int[] {1, 2, 3, 4}));
    }

    @Test
    void duplicateAtOppositeEnds() {
        assertTrue(solution.hasDuplicateBySortingAndCopying(new int[] {7, 1, 2, 3, 7}));
    }

    @Test
    void allEqual() {
        assertTrue(solution.hasDuplicateBySortingAndCopying(new int[] {5, 5, 5, 5}));
    }

    @Test
    void singleElement() {
        assertFalse(solution.hasDuplicateBySortingAndCopying(new int[] {42}));
    }

    @Test
    void empty() {
        assertFalse(solution.hasDuplicateBySortingAndCopying(new int[] {}));
    }

    @Test
    void negativeNumbers() {
        assertTrue(solution.hasDuplicateBySortingAndCopying(new int[] {-1, 0, 1, -1}));
        assertFalse(solution.hasDuplicateBySortingAndCopying(new int[] {-3, -2, -1, 0}));
    }

    @Test
    void extremeValues() {
        assertTrue(solution.hasDuplicateBySortingAndCopying(new int[] {-1_000_000_000, 0, -1_000_000_000}));
        assertFalse(solution.hasDuplicateBySortingAndCopying(new int[] {-1_000_000_000, 1_000_000_000}));
    }

    @Test
    void preserveInputArray() {
        int[] a = new int[] {1, 10, 3, 4, 7, 4, 1};
        int[] b = a.clone();
        assertTrue(solution.hasDuplicateBySortingAndCopying(a));
        assertArrayEquals(b, a);
    }
}
