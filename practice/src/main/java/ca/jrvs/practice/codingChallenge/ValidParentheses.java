package ca.jrvs.practice.codingChallenge;

import java.util.EmptyStackException;
import java.util.Stack;

public class ValidParentheses {
    public boolean validParentheses(String s) {
        /*
        Iterate through the string checking for opening brackets. When there is an opening bracket, add it to the
        'openingBrackets' stack.

        While there is an opening bracket in the 'openingBrackets' stack:
            - If another opening bracket appears, add it to the 'openingBrackets' stack and replace the current opening
            bracket with it.
            - If a closing bracket appears that doesn't match the current opening bracket, return 'false' immediately.
            - If a closing bracket appears that matches the current opening bracket, remove the current opening
            bracket from the 'openingBrackets' stack. If there are still brackets in the 'openingBrackets' stack, the
            next bracket in the stack replaces the current opening bracket.

        If a closing bracket appears while there are no opening brackets, return 'false' immediately.
        If the 'openingBrackets' stack is not empty when we reach the end of the string, return 'false'.
        */
        Stack<Character> openingBrackets = new Stack<>();
        Character currentOpenBracket = null;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
                openingBrackets.push(s.charAt(i));
                currentOpenBracket = s.charAt(i);
            }
            else if (s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}') {
                if ((currentOpenBracket == null) ||
                    (s.charAt(i) == ')' && currentOpenBracket != '(') ||
                    (s.charAt(i) == ']' && currentOpenBracket != '[') ||
                    (s.charAt(i) == '}' && currentOpenBracket != '{')) {
                    return false;
                }
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

        return openingBrackets.empty();
    }
}
