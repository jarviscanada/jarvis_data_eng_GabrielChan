package ca.jrvs.practice.codingChallenge;

public class ValidPalindrome {
    /**
     * Big-O: O(n)
     * Justification:
     * We are iterating through the string from both ends towards the middle and thus, we make approximately 'n/2'
     * comparisons. Therefore, checking for a valid palindrome takes linear time.
     */
    public boolean twoPointersSolution(String string) {
        // Convert all uppercase letters to lowercase letters and remove all non-alphanumeric characters.
        String s = string.replaceAll("[^A-Za-z0-9]", "").toLowerCase();

        // If the length of the string is 0 or 1, return true immediately.
        if (s.length() <= 1) {
            return true;
        }

        int i = 0;
        int j = s.length() - 1;
        // Check if the first and last characters are equal and iterate towards the middle of the string.
        do {
            // If, at any point, the two characters are not equal, return false immediately.
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        } while (j - i > 0);

        // If we have reached the middle of the string without encountering any two characters that are not equal, then
        // the string is a valid palindrome.
        return true;
    }

    /**
     * Big-O: O(n)
     * Justification:
     * The recursive implementation effectively follows the same steps as "two pointers". Hence, the analysis and
     * justification remains the same.
     */
    public boolean recursiveSolution(String string) {
        // Convert all uppercase letters to lowercase letters and remove all non-alphanumeric characters.
        String s = string.replaceAll("[^A-Za-z0-9]", "").toLowerCase();

        // Base case. Once the length of the string is 0 or 1, return 'true' immediately.
        if (s.length() <= 1) {
            return true;
        }
        // Check if the first and last characters are equal. Return 'false' immediately, if they are not
        else if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }
        // Recurse on a substring of the original string with the first and last characters truncated.
        else {
            return recursiveSolution(s.substring(1, s.length() - 1));
        }
    }
}
