package ca.jrvs.practice.codingChallenge;

import java.util.EmptyStackException;
import java.util.Stack;

public class ValidParentheses {

    /**
     * Big-O: O(n)
     * Justification:
     * Checking for valid parentheses will require iterating through the string. In the worst case, we will need to
     * iterate through the entire string. Hence, validating the parentheses in a given string will take linear time.
     */
    public boolean validParentheses(String s) {
        Stack<Character> openingBrackets = new Stack<>();
        Character currentOpenBracket = null;

        // Iterate through the string checking for opening brackets.
        for (int i = 0; i < s.length(); i++) {
            // When there is an opening bracket, add it to the 'openingBrackets' stack. If another opening bracket
            // appears, add it to the 'openingBrackets' stack and replace the current opening bracket with it.
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                openingBrackets.push(s.charAt(i));
                currentOpenBracket = s.charAt(i);
            }
            // While there is an opening bracket in the 'openingBrackets' stack
            else if (s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}') {
                // If a closing bracket appears while there are no opening brackets, return 'false' immediately.
                // If a closing bracket appears that doesn't match the current opening bracket, return 'false'
                // immediately.
                if ((currentOpenBracket == null) ||
                    (s.charAt(i) == ')' && currentOpenBracket != '(') ||
                    (s.charAt(i) == ']' && currentOpenBracket != '[') ||
                    (s.charAt(i) == '}' && currentOpenBracket != '{')) {
                    return false;
                }
                // If a closing bracket appears that matches the current opening bracket, remove the current opening
                // bracket from the 'openingBrackets' stack. If there are still brackets in the 'openingBrackets' stack,
                // the next bracket in the stack replaces the current opening bracket.
                else {
                    openingBrackets.pop();
                    try {
                        currentOpenBracket = openingBrackets.peek();
                    } catch (EmptyStackException e) {
                        currentOpenBracket = null;
                    }
                }
            }
        }

        // If the 'openingBrackets' stack is not empty when we reach the end of the string, return 'false'. Otherwise,
        // return 'true'
        return openingBrackets.empty();
    }
}
