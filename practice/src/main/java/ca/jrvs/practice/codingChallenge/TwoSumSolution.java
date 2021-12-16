package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;
import java.util.List;

public class TwoSumSolution {
    /**
     * Big-O: O(n^2)
     * Justification:
     * We need to loop through the given array once for each element to find all possible combinations
     */
    public List<List<Integer>> loopTwoSum(List<Integer> arr, int sum) {
        List<List<Integer>> pairs = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                if (arr.get(i) + arr.get(j) == sum) {
                    List<Integer> p = new ArrayList<>();
                    p.add(arr.get(i));
                    p.add(arr.get(j));
                    pairs.add(p);
                }
            }
        }
        return pairs;
    }
}
