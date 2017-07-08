
import java.util.Stack;

/**
 * Created by linbin on 7/5/2017.
 */

public class Solution {
    // For queue
    private Stack<Integer> store;
    private Stack<Integer> output;
    private int size = 0;

    // For min stack
    private Stack<Integer> minStack;
    private Stack<Integer> data;

    public Solution() {
        store = new Stack<Integer>();
        output = new Stack<Integer>();

        minStack = new Stack<Integer>();
        data = new Stack<Integer>();
    }

    /**
     * Implement a stack with min() function, which will return the smallest number in the stack.
     * It should support push, top, pop and min operation all in O(1) cost.
     */
    public int pop() {
        if (data.empty())
            return -1;
        else {
            minStack.pop();
            return data.pop();
        }
    }

    public void push(int element) {
        if (data.empty()) {
            data.push(element);
            minStack.push(element);
        } else {
            data.push(element);
            minStack.push(minStack.peek() > element ? element : minStack.peek());
        }
    }

    public int top() {
        if (data.empty())
            return -1;
        else
            return data.peek();
    }

    public int min() {
        if (minStack.empty())
            return -1;
        else
            return minStack.peek();
    }

    /**
     * Implement a queue by using two stacks. The queue should provide size(), isEmpty(), offer(),
     * poll(), and peek() operations. When the queue is empty, poll() and peek() should return null.
     * The elements in the queue are all integers.
     * size() should return the number of elements in the queue
     * isEmpty() shuold return true if there is no element buffered in the queue, false otherwise
     */
    public Integer poll() {
        if (isEmpty())
            return null;

        Integer res;

        if (!output.empty()) {
            res = output.pop();
            --size;
        } else if (!store.empty()){
            while (!store.empty()) {
                output.push(store.pop());
            }

            res = output.pop();
            --size;
        } else {
            return null;
        }

        return res;
    }

    public void offer(int element) {
        store.push(element);
        ++size;
    }

    public int size() {
        return size;
    }

    public Integer peek() {
        if (isEmpty())
            return null;

        Integer res;

        if (!output.empty()) {
            res = output.peek();
        } else if (!store.empty()){
            while (!store.empty()) {
                output.push(store.pop());
            }

            res = output.peek();
        } else {
            return null;
        }

        return res;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String []argv) {
        Solution solu = new Solution();

        solu.offer(2);
        solu.offer(5);
        System.out.println(solu.peek());
        solu.offer(1);
        System.out.println(solu.poll());
        System.out.println(solu.peek());
        System.out.println(solu.poll());

        // min stack
        System.out.println("min Stack: ");
        solu.push(2);
        solu.push(5);
        solu.push(1);
        System.out.println(solu.top());
        System.out.println(solu.min());
        System.out.println(solu.pop());
        System.out.println(solu.min());
    }

}
