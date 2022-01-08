package ca.jrvs.practice.codingChallenge;

public class StringToIntSolution {
    /**
     * Big-O: O(n)
     * Justification:
     * Method iterates through the input string to determine the appropriate substring to parse.
     */
    public int parseAtoi(String str) {
        boolean isNegative = false;
        int startIndex;
        int endIndex;
        int i = 0;

        // Strip leading whitespace
        while(i < str.length() && Character.isWhitespace(str.charAt(i))) {
            i++;
        }
        if (i >= str.length()) {
            return 0;
        }

        // Check if the first character is either '-' or '+'
        if (str.charAt(i) == '-') {
            isNegative = true;
            i++;
        }
        else if (str.charAt(i) == '+') {
            i++;
        }

        if (i >= str.length()) {
            return 0;
        }
        else {
            startIndex = i;
        }

        // Iterate through the rest of the string until either a non-digit char is found or the end of the string
        // is reached
        while(i < str.length() && Character.isDigit(str.charAt(i))) {
            i++;
        }
        if (i >= str.length()) {
            return 0;
        }
        else {
            endIndex = i;
        }

        // Parse the substring with Integer.parseInt
        int value = Integer.parseInt(str.substring(startIndex, endIndex));
        if (isNegative) {
            return 0 - value;
        }
        else {
            return value;
        }

    }

    /**
     * Big-O: O(n)
     * Justification:
     * Many of the same operations are being performed but instead of using Integer.parseInt, the string is iterated
     * through a second time. There are still a constant number of loops through the input and so the time
     * complexity remains linear.
     */
    public int manAtoi(String str) {
        boolean isNegative = false;
        int startIndex;
        int endIndex;
        int i = 0;

        // Strip leading whitespace
        while(i < str.length() && Character.isWhitespace(str.charAt(i))) {
            i++;
        }
        if (i >= str.length()) {
            return 0;
        }

        // Check if the first character is either '-' or '+'
        if (str.charAt(i) == '-') {
            isNegative = true;
            i++;
        }
        else if (str.charAt(i) == '+') {
            i++;
        }

        if (i >= str.length()) {
            return 0;
        }
        else {
            startIndex = i;
        }

        // Iterate through the rest of the string until either a non-digit char is found or the end of the string
        // is reached
        while(i < str.length() && Character.isDigit(str.charAt(i))) {
            i++;
        }
        if (i >= str.length()) {
            return 0;
        }
        else {
            endIndex = i;
        }

        // Manually parse the substring to int
        int value = 0;
        int factor = 1;
        String subString = str.substring(startIndex, endIndex);
        for (int j = str.length() - 1; i >= 0; i--) {
            value += (subString.charAt(j) - '0') * factor;
            factor *= 10;
        }

        if (isNegative) {
            return 0 - value;
        }
        else {
            return value;
        }

    }
}


