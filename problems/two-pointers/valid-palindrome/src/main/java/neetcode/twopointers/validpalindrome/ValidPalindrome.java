package neetcode.twopointers.validpalindrome;

class ValidPalindrome {

    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
            throw new IllegalArgumentException("invalid empty string");
        }
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            while (i < j && isNotAlphaNumeric(s, i)) {
                i++;
            }
            while (i < j && isNotAlphaNumeric(s, j)) {
                j--;
            }

            if (i >= j) {
                return true;
            }

            if (!isEqualChar(s, i, j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    boolean isNotAlphaNumeric(String s, int i) {
        return !Character.isLetterOrDigit(s.charAt(i));
    }

    boolean isEqualChar(String s, int i, int j) {
        return Character.toLowerCase(s.charAt(i)) == Character.toLowerCase(s.charAt(j));
    }
}
