package ca.jrvs.practice.codingChallenge;

import java.util.ArrayList;

class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) { this.val = val; }

    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class RemoveNthNode {
    /**
     * Big-O: O(n)
     * Justification:
     * We need to iterate through the entire linked list at least once to locate the "tail" node. Once we know where
     * the "tail" is, removing the specified node can be done in constant time by calculating the correct index and
     * accessing the right node from our ArrayList. Hence, removing the nth node from a linked list takes linear time.
     */
    public ListNode removeNode(ListNode head, int n) {
        // Create list to track each node.
        ArrayList<ListNode> list = new ArrayList<>();
        int tail = 0;

        ListNode current = head;
        list.add(current);
        // Iterate through the linked list to find the tail and track each node.
        while (current.next != null) {
            current = current.next;
            list.add(current);
            tail++;
        }

        // Once the tail is found, remove it by disconnecting it from its adjacent nodes.

        // If there is only one node in the linked list.
        if (tail == 0) {
            return null;
        }
        // If we're removing the last node.
        else if (n == 1) {
            list.get(tail - 1).next = null;
        }
        // If we're removing the first node.
        else if (n - 1 == tail) {
            return head.next;
        }
        else {
            list.get(tail - n - 1).next = list.get(tail - n + 1);
        }

        return head;
    }
}
