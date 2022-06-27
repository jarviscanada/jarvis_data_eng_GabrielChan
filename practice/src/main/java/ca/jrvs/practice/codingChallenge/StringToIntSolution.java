package ca.jrvs.practice.codingChallenge;

public class StringToIntSolution {
    /**
     * Big-O: O(n)
     * Justification:
     * Method iterates through the input string to determine the appropriate substring to parse.
     */
    public int parseAtoi(String str) {
        String s = "";
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

        int value = Integer.parseInt(str.substring(startIndex, endIndex));
        if (isNegative) {
            return 0 - value;
        }
        else {
            return value;
        }

    }
}
