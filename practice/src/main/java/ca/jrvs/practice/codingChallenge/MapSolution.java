package ca.jrvs.practice.codingChallenge;

import java.util.Map;

public class MapSolution {
    /**
     * Big-O: O(n)
     * Justification:
     * `.equals` API implementation loops through each entry in the first map comparing it to the parallel entry in
     * the second map. Each comparison takes constant time, but n comparisons need to be made in the worst case.
     * Hence, the time complexity is linear.
     */
    public <K,V> boolean compareMaps(Map<K,V> m1, Map<K,V> m2) {
        return m1.equals(m2);
    }

    /**
     * Big-O: O(n)
     * Justification:
     * It presumably takes 2n operations to convert both maps into entry sets and another n operations to compare
     * each of them. Hence, the time complexity is linear.
     */
    public <K,V> boolean compareHashMaps(Map<K,V> m1, Map<K,V> m2) {
        return m1.entrySet().equals(m2.entrySet());
    }
}
