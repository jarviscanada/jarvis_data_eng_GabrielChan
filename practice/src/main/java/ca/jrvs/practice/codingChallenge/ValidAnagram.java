package ca.jrvs.practice.codingChallenge;

public class ValidAnagram {
    /**
     * Big-O: O(n^2)
     * Justification:
     * We need to iterate through 't' for each letter in 's' to determine if 't' contains the same letters as 's'. In
     * the worst case, we will need to iterate through a string of length 'n' 'n' times. Hence, the time complexity
     * of this solution is quadratic.
     */
    public boolean iterValidAnagram(String s, String t) {
        // Check if 's' and 't' have the same length. If they don't, then 't' is not an anagram of 's'.
        if (s.length() != t.length()) {
            return false;
        }

        // Convert 's' and 't' into Arrays.
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        int index;

        // Iterate through 's', checking if 't' contains each character in 's'.
        for (char c : sArray) {
            index = indexOf(c, tArray);
            // If an identical character is found in 't', replace that character with a "null" value character.
            if (index >= 0) {
                tArray[index] = '/';
            }
            // If there is no identical character found in 't', then we know that 't' is not an anagram of 's'.
            else {
                return false;
            }
        }

        // If the function manages to iterate through the entire array 's', then 't' is an anagram of 's'.
        return true;
    }

    /**
     * Big-O: O(n)
     * Justification:
     * For each letter in the alphabet, we need to iterate through a string of length 'n' twice. Since the number of
     * letters in the alphabet is fixed and independent of the length of the strings, we are iterating through a
     * string of length 'n' a fixed number of times. Hence, the time complexity of this solution is linear.
     */
    public boolean countValidAnagram(String s, String t) {
        // Check if 's' and 't' have the same length. If they don't, then 't' is not an anagram of 's'.
        if (s.length() != t.length()) {
            return false;
        }

        // Convert 's' and 't' into Arrays.
        char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
                's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        char[] sArray = s.toCharArray();
        char[] tArray = t.toCharArray();
        int sCount, tCount;

        // Count the number of occurrences of each letter in 's' and 't'.
        for (char c : letters) {
            sCount = 0;
            tCount = 0;
            for (char d : sArray) {
                if (c == d) {
                    sCount++;
                }
            }
            for (char d : tArray) {
                if (c == d) {
                    tCount++;
                }
            }
            // If the number of occurrences of a letter for both 's' and 't' don't match, then 't' is not an anagram
            // of 's'.
            if (sCount != tCount) {
                return false;
            }
        }

        // If the 's' and 't' have the same number of occurrences of each letter, then 't' is an anagram of 's'
        return true;
    }

    // Custom 'indexOf' method for primitive arrays found online
    public static int indexOf(char needle, char[] haystack)
    {
        for (int i = 0; i < haystack.length; i++)
        {
            if (haystack[i] == needle) {
                return i;
            }
        }

        return -1;
    }
}
