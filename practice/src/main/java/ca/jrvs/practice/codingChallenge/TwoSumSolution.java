package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class TwoSumSolution {
    /**
     * Big-O: O(n^2)
     * Justification:
     * We need to loop through the given array once for each element to find all possible combinations. That is, we
     * need to loop through an array containing n values n times. Hence, the time complexity of this solution is
     * quadratic.
     */
    public List<List<Integer>> loopTwoSum(int[] arr, int sum) {
        List<List<Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] + arr[j] == sum) {
                    List<Integer> p = new ArrayList<>();
                    p.add(i);
                    p.add(j);
                    pairs.add(p);
                }
            }
        }
        return pairs;

    }

    /**
     * Big-O: O(n)
     * Justification:
     * Within the 'for' loop, we calculate the complement value needed to obtain the target sum, check if our hash
     * table of previous values contains the complement value, add the current value and its complement to the list
     * of pairs if it does and populate the hash table with the current value. All these operations run in constant
     * time and the 'for' loop only runs once. Hence, the time complexity of this solution is linear.
     */
    public List<List<Integer>> mapTwoSum(int[] arr, int sum) {
        Hashtable<Integer, Integer> table = new Hashtable<> ();
        List<List<Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            int comp = sum - arr[i];
            if (table.get(comp) != null) {
                List<Integer> p = new ArrayList<>();
                p.add(arr[i]);
                p.add(comp);
                pairs.add(p);
            }
            table.put(arr[i], arr[i]);
        }
        return pairs;
    }
}
