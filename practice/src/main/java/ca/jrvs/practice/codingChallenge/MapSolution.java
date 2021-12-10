package ca.jrvs.practice.codingChallenge;

import java.util.Map;

public class MapSolution {
    /**
     * Big-O:
     * Justification:
     */
    public <K,V> boolean compareMaps(Map<K,V> m1, Map<K,V> m2) {
        return m1.equals(m2);
    }
}
