package ca.jrvs.practice.codingChallenge;

public class ValidPalindrome {
    /*
    - Convert all uppercase letters to lowercase letters
    - Remove all non-alphanumeric characters
    - If the length of the string is 0 or 1, return true immediately
    - Otherwise, check if the first and last characters are equal and iterate towards the middle of the string
        - If, at any point, the two characters are not equal, return false immediately
        - Once the middle of the string has been reached, return true
    */
    public boolean twoPointersSolution(String string) {
        String s = string.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        if (s.length() <= 1) {
            return true;
        }

        int i = 0;
        int j = s.length() - 1;
        do {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        } while (j - i > 0);
        return true;
    }

    public boolean recursiveSolution(String string) {
        String s = string.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        if (s.length() <= 1) {
            return true;
        }
        else if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }
        else {
            return recursiveSolution(s.substring(1, s.length() - 1));
        }
    }
}
