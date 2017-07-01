import static java.lang.Math.abs;

/**
 * Created by linbin on 6/29/2017.
 */
public class Solution {
    // Classical binary search
    public int binarySearchI(int [] array, int target) {
        if (array == null || array.length == 0)
            return -1;

        int start = 0, end = array.length - 1;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (array[end] == target)
            return end;

        if (array[start] == target)
            return start;

        return -1;
    }

    // Find the first occurrence
    public int binarySearchII(int [] array, int target) {
        if (array == null || array.length == 0)
            return -1;

        int start = 0, end = array.length - 1;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (array[mid] == target) {
                end = mid;
            } else if (array[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (array[start] == target)
            return start;

        if (array[end] == target)
            return end;

        return -1;
    }

    // Find the last occurrence
    public int binarySearchIII(int [] array, int target) {
        if (array == null || array.length == 0)
            return -1;

        int start = 0, end = array.length - 1;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (array[mid] == target) {
                start = mid;
            } else if (array[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (array[end] == target)
            return end;

        if (array[start] == target)
            return start;

        return -1;
    }

	// Find the closest element to the target
    public int closestValue(int []array, int target) {
        if (array == null || array.length == 0)
            return -1;

        int start = 0, end = array.length - 1;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            if (array[mid] == target) {
                return mid;
            } else if (array[mid] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (Math.abs(array[end] - target) < Math.abs(array[start] - target))
            return end;
        else
            return start;
    }

	// Find the K closest numbers to T in an array
    public int[] Kclosest(int []array, int t, int k) {
        if (array == null || array.length == 0 || k == 0)
            return null;

        int indx = closestValue(array, t);
        int []res = new int[k];

        res[0] = array[indx];

        int start = indx - 1, end = indx + 1;
        int index = 1;
        while (start >= 0 && end < array.length) {
            if (Math.abs(array[start] - t) > Math.abs(array[end] - t)) {
                res[index++] = array[end++];
            } else {
                res[index++] = array[start--];
            }
        }

        while (start < 0 && index < k) {
            res[index++] = array[end++];
        }

        while (end >= array.length  && index < k) {
            res[index++] = array[start--];
        }

        for (int num : res)
            System.out.println(num);

        return res;
    }

	// Search in a sorted matrix
    public boolean searchMatrix(int [][]matrix, int target){
        if (matrix == null || matrix[0] == null)
            return false;

        int row = matrix.length;
        int col = matrix[0].length;

        // Binary search: O(lg(m + n))
        int start = 0, end = row * col - 1;
        while (start + 1 < end) {
            int mid = (start + end) / 2;
            int i = mid / col, j = mid % col;

            if (matrix[i][j] == target) {
                return true;
            } else if (matrix[i][j] < target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (matrix[start / col][start % col] == target)
            return true;

        if (matrix[end / col][end % col] == target)
            return true;

        // Time complexity: O(m + n)
//        int i = row - 1, j = 0;
//        while (i >=0 && j < col) {
//            if (matrix[i][j] == target) {
//                return true;
//            } else if (matrix[i][j] < target) {
//                ++j;
//            } else {
//                --i;
//            }
//        }

        return false;
    }

    public static void main(String []argv) {
        Solution solu = new Solution();

        int []arr1 = {1,2,3,4,5};
        int t1 = 3;
        int []arr2 = {1,2,2,2,3,4};
        int t2 = 6, t3 = 2;
        System.out.println("Classical binary search:");
        System.out.println(solu.binarySearchI(arr1, t1));
        System.out.println(solu.binarySearchI(arr1, t2));
        System.out.println(solu.binarySearchI(arr2, t3));

        System.out.println("Find the first occurrence:");
        System.out.println(solu.binarySearchII(arr1, t1));
        System.out.println(solu.binarySearchII(arr1, t2));
        System.out.println(solu.binarySearchII(arr2, t3));

        System.out.println("Finde the last occurrence:");
        System.out.println(solu.binarySearchIII(arr1, t1));
        System.out.println(solu.binarySearchIII(arr1, t2));
        System.out.println(solu.binarySearchIII(arr2, t3));

        int []arr3 = {1,2,3};
        int []arr4 = {1,4,6};
        int []arr5 = {1,3,3,4};

        System.out.println("Closest in sorted array:");
        System.out.println(solu.closestValue(arr3, 2));
        System.out.println(solu.closestValue(arr4, 3));
        System.out.println(solu.closestValue(arr4, 5));
        System.out.println(solu.closestValue(arr5, 2));

        int [][]matrix = {{1,2,3}, {4,5,6}, {7,8,9}};
        System.out.println("Find target in 2D arrary: ");
        System.out.println(solu.searchMatrix(matrix, 4));
        System.out.println(solu.searchMatrix(matrix, 10));
        System.out.println(solu.searchMatrix(matrix, 1));
        System.out.println(solu.searchMatrix(matrix, 9));

        int []arr6 = {1,5};
        System.out.println("Find K closest values: ");
        solu.Kclosest(arr3, 2, 3);
        solu.Kclosest(arr6, 10, 2);
    }
}
