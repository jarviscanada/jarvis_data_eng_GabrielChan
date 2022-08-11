package ca.jrvs.practice.codingChallenge;

import java.util.HashSet;
import java.util.LinkedList;

public class LinkedListDuplicates {
    /**
     * Big-O: O(n)
     * Justification:
     * We have to iterate through the entire list to determine if there are duplicates. Hence, removing duplicates
     * from the linked list will take linear time.
     */
    public void removeDuplicates(LinkedList<Integer> list) {
        // Store each unique element in a Set. If there are any duplicates, they cannot be added to this Set.
        HashSet<Integer> tempSet = new HashSet<> ();

        // Iterate through each element of 'list'
        list.forEach(i -> {
                // If the element cannot be added to the Set, then it is a duplicate.
                if (!tempSet.add(i)) {
                    list.remove(i);
                }});
    }
}
