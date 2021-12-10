package ca.jrvs.practice.codingChallenge;

public class FibonacciSolution {
    /**
     * Big-O: O(2^n)
     * Justification:
     * We know that T(0) = 1 and T(1) = 1. However, as the input n increases, the number of times the function is
     * called doubles due to the structure of the fibonacci sequence. The number of times the function is called can
     * be visualized as a pyramid.
     *
     * n:       *                       (First recursion loop)
     * n-1:     **                      (Second recursion loop)
     * n-2:     ****                    (Third recursion loop)
     * n-3:     ********                (Fourth recursion loop)
     * n-4:     ****************        (Fifth recursion loop)
     *
     * Hence, it is clear that the running time is exponential.
     */
    public int recFibNumber(int n) throws IllegalArgumentException {
        if (n == 0) {
            return 0;
        }
        else if (n == 1) {
            return 1;
        }
        else if (n > 1) {
            return recFibNumber(n - 1) + recFibNumber(n - 2);
        }
        else {
            throw new IllegalArgumentException("Fibonacci sequence is only applicable on non-negative integers");
        }
    }

    /**
     * Big-O: O(n)
     * Justification:
     * Just like in the recursive implementation, T(0) = T(1) = 1. But now, the result is stored in the memo array.
     * Hence, this function is only called once for each number between 0 and n; if the result has already been
     * computed, it is returned from the memo array. It follows that the function is called at most n times. Since
     * all other operations in the function is constant, we know that the time complexity is linear.
     */
    public int dynFibNumber(int n, int[] memo) throws IllegalArgumentException {
        if (!java.util.Objects.isNull(memo[n])) {
            return memo[n];
        }
        else if (n == 0) {
            return 0;
        }
        else if (n == 1) {
            return 1;
        }
        else if (n > 1) {
            int res = dynFibNumber(n - 1, memo) + dynFibNumber(n - 2, memo);
            memo[n] = res;
            return res;
        }
        else {
            throw new IllegalArgumentException("Fibonacci sequence is only applicable on non-negative integers");
        }
    }
}
