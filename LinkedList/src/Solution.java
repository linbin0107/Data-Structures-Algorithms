import java.util.*;

/**
 * Created by linbin on 7/5/2017.
 */
class ListNode{
    public int value;
    public ListNode next;
    public ListNode(int value) {
        this.value = value;
        this.next = null;
    }
}

public class Solution {
    /**
     * Reverse a linked list: Traversal
     */
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        head = head.next;
        dummy.next.next = null;

        while (head != null) {
            ListNode temp = dummy.next;
            ListNode nextHead = head.next;
            dummy.next = head;
            head.next = temp;
            head = nextHead;
        }

        return dummy.next;
    }

    /**
     * Reverse a linked list: Recursion
      */
    public ListNode Reverse(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode currHead = Reverse(head.next);
        head.next.next = head;
        head.next = null;

        return currHead;
    }

    /**
     * Find the middle node of a linked list
      */
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /**
     *  Check if a given linked list has a cycle. Retrun true if it does, otherwise return false
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null)
            return false;

        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            if (fast == slow)
                return true;

            slow = slow.next;
            fast = fast.next.next;
        }

        return false;
    }

    /**
     *  Insert in a sorted linked list
     */
    public ListNode insertNode(ListNode head, int value) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        ListNode node = new ListNode(value);
        if (head == null)
            return node;

        while (head != null && head.value < value) {
            prev = head;
            head= head.next;
        }

        prev.next = node;
        node.next = head;

        return dummy.next;
    }

    /**
     *  Merge two sorted linked list into one large sorted list
     */
    public ListNode merge2SortedList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;

        while (l1 != null && l2 != null) {
            if (l1.value < l2.value) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }

            prev = prev.next;
        }

        while (l1 != null) {
            prev.next = l1;
            l1 = l1.next;
            prev = prev.next;
        }

        while (l2 != null) {
            prev.next = l2;
            l2 = l2.next;
            prev = prev.next;
        }
        return dummy.next;
    }

    /**
     *  Reorder the given singly linked list N1->N2->N3>-...->Nn->null to be N1->Nn->N2->Nn-1->...->null
     */
    public ListNode reorder(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = prevMiddle(head);
        ListNode temp = prev.next;
        prev.next = null;

        ListNode tail = Reverse(temp);

        return mergeTwo(head, tail);
    }

    private ListNode prevMiddle(ListNode head) {
        if (head == null || head.next == null)
            return head;

        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        return prev;
    }

    private ListNode mergeTwo(ListNode first, ListNode second) {
        if (first == null)
            return second;

        if (second == null)
            return first;

        ListNode head = first;
        while (first != null && second != null) {
            ListNode nextF = first.next;
            ListNode nextS = second.next;
            first.next = second;

            if (nextF == null)
                break;

            second.next = nextF;
            first = nextF;
            second = nextS;
        }

        return head;
    }

    /**
     * Given a linked list and a target value T, partition it such that all nodes less than
     * T are listed before the nodes larger than or equal to the target value. The original
     * relative order of the nodes in each of the two partitions should be reserved.
     * @param head
     * @param target
     * @return
     */
    public ListNode partitionLinkedList(ListNode head, int target) {
        if (head == null || head.next == null)
            return head;

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode curr = head;
        ListNode prev = dummy;

        ArrayList<ListNode> first = new ArrayList<ListNode>();

        while (curr != null) {
            if (curr.value >= target) {
                prev = curr;
                curr = curr.next;
            } else {
                first.add(curr);
                prev.next = curr.next;
                curr = curr.next;
            }
        }

        if (first.size() == 0)
            return head;

        ListNode newHead = first.get(0);
        prev = newHead;
        for (int i = 1; i < first.size(); ++i) {
            prev.next = first.get(i);
            prev = prev.next;
        }

        prev.next = dummy.next;

        return newHead;
    }

    private void display(ListNode head) {
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    public Solution() {

    }

    public  static void main(String []argvs) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        ListNode node = new ListNode(3);
        node.next = new ListNode(5);
        node.next.next = new ListNode(1);
        head.next.next = node;

        Solution solu = new Solution();
        solu.display(head);
        head = solu.partitionLinkedList(head, 3);
        System.out.println("After partition: ");
        solu.display(head);
        head = solu.reverse(head);
        System.out.println("After reverse: ");
        solu.display(head);
    }
}
