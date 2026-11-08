package neetcode.arraysandhashing.productsofarrayexceptself;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.function.UnaryOperator;
import java.util.stream.Stream;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ProductsOfArrayExceptSelfTest {

    private static final ProductsOfArrayExceptSelf solution = new ProductsOfArrayExceptSelf();

    static Stream<Named<UnaryOperator<int[]>>> implementations() {
        return Stream.of(
                Named.of("productExceptSelf", solution::productExceptSelf),
                Named.of("productExceptSelfWithoutDivision", solution::productExceptSelfWithoutDivision),
                Named.of("productExceptSelfUsingDivision", solution::productExceptSelfUsingDivision));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void example1(UnaryOperator<int[]> productExceptSelf) {
        assertArrayEquals(new int[] {48, 24, 12, 8}, productExceptSelf.apply(new int[] {1, 2, 4, 6}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void example2(UnaryOperator<int[]> productExceptSelf) {
        assertArrayEquals(new int[] {0, -6, 0, 0, 0}, productExceptSelf.apply(new int[] {-1, 0, 1, 2, 3}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void twoZeroesMakeEveryProductZero(UnaryOperator<int[]> productExceptSelf) {
        assertArrayEquals(new int[] {0, 0, 0, 0, 0}, productExceptSelf.apply(new int[] {-1, 0, 0, 2, 3}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void lengthTwoSwapsTheElements(UnaryOperator<int[]> productExceptSelf) {
        assertArrayEquals(new int[] {7, 3}, productExceptSelf.apply(new int[] {3, 7}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void lengthTwoWithOneZero(UnaryOperator<int[]> productExceptSelf) {
        assertArrayEquals(new int[] {7, 0}, productExceptSelf.apply(new int[] {0, 7}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void lengthTwoWithTwoZeroes(UnaryOperator<int[]> productExceptSelf) {
        assertArrayEquals(new int[] {0, 0}, productExceptSelf.apply(new int[] {0, 0}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void allNegativeValues(UnaryOperator<int[]> productExceptSelf) {
        assertArrayEquals(new int[] {-24, -12, -8, -6}, productExceptSelf.apply(new int[] {-1, -2, -3, -4}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void mixedSignsWithoutZero(UnaryOperator<int[]> productExceptSelf) {
        assertArrayEquals(new int[] {-6, 6, 2, -3}, productExceptSelf.apply(new int[] {-1, 1, 3, -2}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void allOnes(UnaryOperator<int[]> productExceptSelf) {
        assertArrayEquals(new int[] {1, 1, 1}, productExceptSelf.apply(new int[] {1, 1, 1}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void singleZeroAtLastPosition(UnaryOperator<int[]> productExceptSelf) {
        assertArrayEquals(new int[] {0, 0, 12}, productExceptSelf.apply(new int[] {3, 4, 0}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void rejectsSingleElement(UnaryOperator<int[]> productExceptSelf) {
        assertThrows(IllegalArgumentException.class, () -> productExceptSelf.apply(new int[] {10}));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void rejectsEmptyArray(UnaryOperator<int[]> productExceptSelf) {
        assertThrows(IllegalArgumentException.class, () -> productExceptSelf.apply(new int[] {}));
    }
}
