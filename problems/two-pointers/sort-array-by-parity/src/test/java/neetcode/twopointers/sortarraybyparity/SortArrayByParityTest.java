package neetcode.twopointers.sortarraybyparity;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class SortArrayByParityTest {

    private static final SortArrayByParity solution = new SortArrayByParity();

    static Stream<Named<UnaryOperator<int[]>>> implementations() {
        return Stream.of(
                Named.of("sortArrayByParity", solution::sortArrayByParity));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void example1(UnaryOperator<int[]> sortArrayByParity) {
        assertEvensBeforeOdds(new int[] {3, 1, 2, 4}, sortArrayByParity);
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void example2(UnaryOperator<int[]> sortArrayByParity) {
        assertEvensBeforeOdds(new int[] {0}, sortArrayByParity);
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void emptyArray(UnaryOperator<int[]> sortArrayByParity) {
        assertEvensBeforeOdds(new int[] {}, sortArrayByParity);
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void oddThenEven(UnaryOperator<int[]> sortArrayByParity) {
        assertEvensBeforeOdds(new int[] {1, 2}, sortArrayByParity);
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void evenThenOdd(UnaryOperator<int[]> sortArrayByParity) {
        assertEvensBeforeOdds(new int[] {2, 1}, sortArrayByParity);
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void alternatingParityAscending(UnaryOperator<int[]> sortArrayByParity) {
        assertEvensBeforeOdds(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, sortArrayByParity);
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void alternatingParityDescending(UnaryOperator<int[]> sortArrayByParity) {
        assertEvensBeforeOdds(new int[] {10, 9, 8, 7, 6, 5, 4, 3, 2, 1}, sortArrayByParity);
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void singleEvenAtTheEnd(UnaryOperator<int[]> sortArrayByParity) {
        assertEvensBeforeOdds(new int[] {1, 3, 5, 7, 9, 2}, sortArrayByParity);
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void allOddValues(UnaryOperator<int[]> sortArrayByParity) {
        assertEvensBeforeOdds(new int[] {1, 3, 5, 7, 9}, sortArrayByParity);
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void allEvenValues(UnaryOperator<int[]> sortArrayByParity) {
        assertEvensBeforeOdds(new int[] {0, 2, 4, 6, 8, 10}, sortArrayByParity);
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void allEqualEvenValues(UnaryOperator<int[]> sortArrayByParity) {
        assertEvensBeforeOdds(new int[] {2, 2, 2}, sortArrayByParity);
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void allEqualOddValues(UnaryOperator<int[]> sortArrayByParity) {
        assertEvensBeforeOdds(new int[] {3, 3, 3, 3}, sortArrayByParity);
    }

    private static void assertEvensBeforeOdds(int[] input, UnaryOperator<int[]> sortArrayByParity) {
        int[] result = sortArrayByParity.apply(input.clone());

        int[] sortedInput = input.clone();
        int[] sortedResult = result.clone();
        Arrays.sort(sortedInput);
        Arrays.sort(sortedResult);
        assertArrayEquals(sortedInput, sortedResult, "result must be a rearrangement of the input");

        boolean seenOdd = false;
        for (int value : result) {
            if (value % 2 != 0) {
                seenOdd = true;
            } else {
                assertTrue(!seenOdd, "even value " + value + " after an odd value in " + Arrays.toString(result));
            }
        }
    }
}
