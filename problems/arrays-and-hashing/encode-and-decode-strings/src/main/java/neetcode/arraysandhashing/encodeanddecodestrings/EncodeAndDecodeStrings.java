package neetcode.arraysandhashing.encodeanddecodestrings;

import java.util.ArrayList;
import java.util.List;

class EncodeAndDecodeStrings {

    private static final int NUM_STRINGS_CHARS = 2;
    private static final int LEN_STRINGS_CHAR = 3;

    // Format: a 2-digit count, then a 3-digit length before each string.
    public String encode(List<String> strs) {
        final StringBuilder sb = new StringBuilder();
        int n = strs.size();
        sb.append(toFixedWidthDigits(n, NUM_STRINGS_CHARS));
        for (String str: strs) {
            int len = str.length();
            sb.append(toFixedWidthDigits(len, LEN_STRINGS_CHAR));
            sb.append(str);
        }
        return sb.toString();
    }

    public List<String> decode(String str) {
        final List<String> strs = new ArrayList<>();
        int at = 0;
        int n = fromFixedWidthDigits(str, at, NUM_STRINGS_CHARS);
        at += 2;
        for (int i = 0; i < n; i++) {
            int m = fromFixedWidthDigits(str, at, LEN_STRINGS_CHAR);
            at += 3;
            strs.add(str.substring(at, at + m));
            at += m;
        }
        return strs;
    }

    char[] toFixedWidthDigits(int n, int size) {
        if (n < 0) {
            throw new IllegalArgumentException("invalid value");
        }
        char[] value = new char[size];
        for (int i = size - 1; i >= 0; i--) {
            value[i] = (char) ('0' + n % 10);
            n /= 10;
        }
        if (n > 0) {
            throw new IllegalArgumentException("invalid value");
        }
        return value;
    }

    int fromFixedWidthDigits(final String str, int start, int size) {
        int n = 0;
        for (int i = start; i < start + size; i++) {
            n *= 10;
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                throw new IllegalArgumentException("invalid digit");
            }
            n += c - '0';
        }
        return n;
    }
}
