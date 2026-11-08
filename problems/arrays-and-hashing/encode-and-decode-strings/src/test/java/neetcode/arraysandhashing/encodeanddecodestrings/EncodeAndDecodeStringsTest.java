package neetcode.arraysandhashing.encodeanddecodestrings;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

import org.junit.jupiter.api.Named;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class EncodeAndDecodeStringsTest {

    record Codec(Function<List<String>, String> encode, Function<String, List<String>> decode) {}

    private static final EncodeAndDecodeStrings solution = new EncodeAndDecodeStrings();

    static Stream<Named<Codec>> implementations() {
        return Stream.of(
                Named.of("encode/decode", new Codec(solution::encode, solution::decode)));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void twoWords(Codec codec) {
        assertRoundTrip(codec, List.of("Java", "Gradle"));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void singleEmptyString(Codec codec) {
        assertRoundTrip(codec, List.of(""));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void testEmptyStrings(Codec codec) {
        assertRoundTrip(codec, List.of("", "Hello", "", "a", "", "World", ""));
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void testEmpty(Codec codec) {
        assertRoundTrip(codec, List.of());
    }

    @ParameterizedTest
    @MethodSource("implementations")
    void testRandomInput(Codec codec) {
        final Random random = new Random(42);
        int n = random.nextInt(1, 100);
        final List<String> strs = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            final StringBuilder sb = new StringBuilder();
            int strLen = random.nextInt(0, 200);
            for (int j = 0; j < strLen; j++) {
                char c = (char) random.nextInt(0, 256);
                sb.append(c);
            }
            strs.add(sb.toString());
        }
        assertRoundTrip(codec, strs);
    }

    // test toFixedWidthDigits

    @Test
    void toDigitsRejectsNegativeNumberWidthTwo() {
        assertThrows(IllegalArgumentException.class, () -> solution.toFixedWidthDigits(-1, 2));
    }

    @Test
    void toDigitsRejectsNegativeNumberWidthThree() {
        assertThrows(IllegalArgumentException.class, () -> solution.toFixedWidthDigits(-1, 3));
    }

    @Test
    void toDigitsPadsOneDigitToWidthThree() {
        assertArrayEquals(new char[]{'0','0','3'}, solution.toFixedWidthDigits(3, 3));
    }

    @Test
    void toDigitsPadsTwoDigitsToWidthThree() {
        assertArrayEquals(new char[]{'0','2','0'}, solution.toFixedWidthDigits(20, 3));
    }

    @Test
    void toDigitsFillsWidthThree() {
        assertArrayEquals(new char[]{'1','0','2'}, solution.toFixedWidthDigits(102, 3));
    }

    @Test
    void toDigitsPadsOneDigitToWidthTwo() {
        assertArrayEquals(new char[]{'0','5'}, solution.toFixedWidthDigits(5, 2));
    }

    @Test
    void toDigitsFillsWidthTwo() {
        assertArrayEquals(new char[]{'1','7'}, solution.toFixedWidthDigits(17, 2));
    }

    @Test
    void toDigitsZeroIsAllZeroesWidthThree() {
        assertArrayEquals(new char[]{'0','0','0'}, solution.toFixedWidthDigits(0, 3));
    }

    @Test
    void toDigitsZeroIsAllZeroesWidthTwo() {
        assertArrayEquals(new char[]{'0','0'}, solution.toFixedWidthDigits(0, 2));
    }

    // test fromFixedWidthDigits

    @Test
    void fromDigitsSkipsTwoLeadingZeroes() {
        assertEquals(3, solution.fromFixedWidthDigits("003", 0, 3));
    }

    @Test
    void fromDigitsKeepsTrailingZero() {
        assertEquals(20, solution.fromFixedWidthDigits("020", 0, 3));
    }

    @Test
    void fromDigitsFullWidthThree() {
        assertEquals(102, solution.fromFixedWidthDigits("102", 0, 3));
    }

    @Test
    void fromDigitsSkipsLeadingZeroWidthTwo() {
        assertEquals(5, solution.fromFixedWidthDigits("05", 0, 2));
    }

    @Test
    void fromDigitsFullWidthTwo() {
        assertEquals(17, solution.fromFixedWidthDigits("17", 0, 2));
    }

    @Test
    void fromDigitsAllZeroesWidthThree() {
        assertEquals(0, solution.fromFixedWidthDigits("000", 0, 3));
    }

    @Test
    void fromDigitsAllZeroesWidthTwo() {
        assertEquals(0, solution.fromFixedWidthDigits("00", 0, 2));
    }

    @Test
    void fromDigitsRejectsNonDigitCharacters() {
        assertThrows(IllegalArgumentException.class, () -> solution.fromFixedWidthDigits("1a4", 0, 3));
        assertThrows(IllegalArgumentException.class, () -> solution.fromFixedWidthDigits("/0", 0, 2));
        assertThrows(IllegalArgumentException.class, () -> solution.fromFixedWidthDigits("0:", 0, 2));
    }

    @Test
    void toDigitsRejectsThreeDigitNumberForWidthTwo() {
        assertThrows(IllegalArgumentException.class, () -> solution.toFixedWidthDigits(100, 2));
    }

    @Test
    void toDigitsRejectsFourDigitNumberForWidthTwo() {
        assertThrows(IllegalArgumentException.class, () -> solution.toFixedWidthDigits(1000, 3));
    }

    @Test
    void roundTripEveryNumberWidthThree() {
        for (int i = 0; i < 1000; i++) {
            assertEquals(i, solution.fromFixedWidthDigits(new String(solution.toFixedWidthDigits(i, 3)), 0, 3));
        }
    }

    @Test
    void roundTripEveryNumberWidthTwo() {
        for (int i = 0; i < 100; i++) {
            assertEquals(i, solution.fromFixedWidthDigits(new String(solution.toFixedWidthDigits(i, 2)), 0, 2));
        }
    }

    @Test
    void roundTripEveryDigitStringWidthThree() {
        for (char a = '0'; a <= '9'; a++) {
            for (char b = '0'; b <= '9'; b++) {
                for (char c = '0'; c <= '9'; c++) {
                    char[] value = new char[] {a, b, c};
                    assertArrayEquals(value, solution.toFixedWidthDigits(solution.fromFixedWidthDigits(new String(value), 0, 3), 3));
                }
            }
        }
    }

    @Test
    void roundTripEveryDigitStringWidthTwo() {
        for (char a = '0'; a <= '9'; a++) {
            for (char b = '0'; b <= '9'; b++) {
                char[] value = new char[] {a, b};
                assertArrayEquals(value, solution.toFixedWidthDigits(solution.fromFixedWidthDigits(new String(value), 0, 2), 2));
            }
        }
    }

    private static void assertRoundTrip(Codec codec, List<String> strs) {
        assertEquals(strs, codec.decode().apply(codec.encode().apply(strs)));
    }
}
