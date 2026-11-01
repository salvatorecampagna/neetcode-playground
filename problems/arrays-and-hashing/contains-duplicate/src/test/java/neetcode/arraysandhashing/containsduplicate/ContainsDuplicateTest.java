package neetcode.arraysandhashing.containsduplicate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

// Remove @Disabled when you start the problem.
@Disabled("not started")
class ContainsDuplicateTest {

    private final ContainsDuplicate solution = new ContainsDuplicate();

    @Test
    void adjacentDuplicate() {
        assertTrue(solution.hasDuplicate(new int[] {1, 2, 3, 3}));
    }

    @Test
    void allDistinct() {
        assertFalse(solution.hasDuplicate(new int[] {1, 2, 3, 4}));
    }

    @Test
    void duplicateAtOppositeEnds() {
        assertTrue(solution.hasDuplicate(new int[] {7, 1, 2, 3, 7}));
    }

    @Test
    void allEqual() {
        assertTrue(solution.hasDuplicate(new int[] {5, 5, 5, 5}));
    }

    @Test
    void singleElement() {
        assertFalse(solution.hasDuplicate(new int[] {42}));
    }

    @Test
    void empty() {
        assertFalse(solution.hasDuplicate(new int[] {}));
    }

    @Test
    void negativeNumbers() {
        assertTrue(solution.hasDuplicate(new int[] {-1, 0, 1, -1}));
        assertFalse(solution.hasDuplicate(new int[] {-3, -2, -1, 0}));
    }

    @Test
    void extremeValues() {
        assertTrue(solution.hasDuplicate(new int[] {Integer.MIN_VALUE, 0, Integer.MIN_VALUE}));
        assertFalse(solution.hasDuplicate(new int[] {Integer.MIN_VALUE, Integer.MAX_VALUE}));
    }
}
