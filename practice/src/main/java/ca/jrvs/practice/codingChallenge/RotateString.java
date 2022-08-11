package ca.jrvs.practice.codingChallenge;

public class RotateString {
    /**
     * Big-O: O(n)
     * Justification:
     * We iterate through every character in the string 's' to determine if the string 'goal' can be formed. In the
     * worst case, we have to iterate through the entire string, resulting in linear time execution.
     */
    public boolean rotateString(String s, String goal) {
        // Check if 's' and 'goal' have the same length. Return 'false' if they don't.
        if (s.length() != goal.length()) {
            return false;
        }
        int i = 0;
        String string = s;
        do {
            // Check if 's' is equal to 'goal'. If so, return 'true'.
            if (string.equals(goal)) {
                return true;
            }
            // Otherwise, "shift" 's' once.
            else {
                string = string.substring(1) + string.charAt(0);
                i++;
            }
            // Repeat the second and third steps at most 's.length()' times. Then return 'false', if the function hasn't
            // returned yet.
        } while (i < s.length());

        return false;
    }
}
