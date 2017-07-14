import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by linbin on 7/13/2017.
 */

public class Solution {
    /**
     * Find the K smallest numbers in an unsorted integer array A. The returned numbers should be in ascending order.
     * @param array
     * @param k
     * @return An array with size K containing the K smallest numbers in ascending order
     */
    public int[] kSmallest(int []array, int k) {
        if (array == null || array.length < k)
            return null;

        int []res = new int[k];

        for (int i = array.length / 2 - 1; i >= 0; --i) {
            heapify(array, i, array.length);
        }

        int indx = array.length - 1;
        for (int j = 0; j < k; ++j) {
            res[j] = array[0];

            swap(array, 0, indx);
            heapify(array, 0, indx);
            --indx;
        }

        return res;
    }

    private void heapify(int []array, int index, int size) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        int smallest = index;

        if (left < size && array[left] < array[smallest]) {
            smallest = left;
        }

        if (right < size && array[right] < array[smallest]) {
            smallest = right;
        }

        if (smallest != index) {
            swap(array, smallest, index);

            heapify(array, smallest, size);
        }
    }

    private void swap(int []array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Given a matrix of size N x M. For each row the elements are sorted in ascending order,
     * and for each column the elements are also sorted in ascending order. Find the Kth smallest number in it.
     * Assumptions: (1) the matrix is not null, N > 0 and M > 0, (2) K > 0 and K <= N * M
     * @param matrix
     * @param k
     * @return
     */
    public int kthSmallest(int [][]matrix, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                pq.offer(matrix[i][j]);
            }
        }

        int count = 1;
        while (count < k) {
            pq.poll();
            ++count;
        }

        return pq.peek();
    }

    public static void main(String []args) {
        int []A = {3, 4, 1, 2, 5};
        int [][]matrix = { {1,  3,   5,  7}, {2,  4,   8,   9},
                            {3,  5, 11, 15}, {6,  8, 13, 18} };

        Solution solu = new Solution();
        int []B = solu.kSmallest(A, 3);
        System.out.println(Arrays.toString(B));

        System.out.println(solu.kthSmallest(matrix, 5));
        System.out.println(solu.kthSmallest(matrix, 8));
    }
}
