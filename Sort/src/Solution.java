/**
 * Created by linbin on 6/29/2017.
 */
public class Solution {
    public Solution() {

    }
    // Selection sort
    public int[] SelectionSort(int []array) {
        if (array == null || array.length == 0)
            return array;

        for (int i = 0; i < array.length; ++i) {
            int currMin = array[i];
            int currMinIn = i;

            for (int j = i + 1; j < array.length; ++j) {
                if (currMin > array[j]) {
                    currMin = array[j];
                    currMinIn = j;
                }
            }

            if (currMinIn != i) {
                array[currMinIn] = array[i];
                array[i] = currMin;
            }
        }

        display(array);

        return array;
    }

    // Quick sort
    public int[] QuickSort(int []array) {
        if (array == null || array.length == 0)
            return array;

        Quick_sort(array, 0, array.length - 1);

        display(array);

        return array;
    }

    public  void Quick_sort(int []array, int left, int right) {
        if (left >= right)
            return;

        int pivot = partition(array, left, right);

        Quick_sort(array, left, pivot - 1);
        Quick_sort(array,pivot + 1, right);
    }

    public int partition(int []array, int left, int right) {
        int pval = array[right];
        int i = left;

        for (int j = left; j < right; ++j) {
            if (array[j] < pval) {
                swap(array, i, j);
                ++i;
            }
        }

        swap(array, i, right);

        return i;
    }

    public void swap(int []array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    // Merge sort
    public void MergeSort(int []array) {
        if (array == null || array.length == 0)
            return;

        MergeSortHelper(array, 0, array.length - 1);

        display(array);

        return;
    }

    public void MergeSortHelper(int []array, int low, int high) {
        if (low >= high) {
            return;
        }

        int mid = (low + high) / 2;
        MergeSortHelper(array, low, mid);
        MergeSortHelper(array, mid + 1, high);

        Merge(array, low, mid, high);
    }

    public void Merge(int []A, int low, int mid, int high) {
        int []first = new int[mid - low + 1];
        int []second = new int[high - mid];

        for (int i = low; i <= mid; ++i)
            first[i - low] = A[i];

        for (int j = mid + 1; j <= high; ++j)
            second[j - mid - 1] = A[j];

        int i = low, j = mid + 1, index = low;
        while ((i <= mid) && (j <= high))
        {
            if (first[i - low] < second[j - mid - 1])
            {
                A[index] = first[i - low];
                ++i;
            } else {
                A[index] = second[j - mid - 1];
                ++j;
            }

            ++index;
        }

        while (i <= mid)
        {
            A[index++] = first[i - low];
            ++i;
        }

        while (j <= high)
        {
            A[index++] = second[j - mid - 1];
            ++j;
        }
    }

    public void display(int []array) {
        for (int i : array) {
            System.out.print(" " + i);
        }
        System.out.println();
    }

    public static void main(String []argv) {
        Solution solu = new Solution();

        int []arr1 = {1};
        int []arr2 = {1,2,3};
        int []arr3 = {3,2,1};
        int []arr4 = {4,2,-3,6,1};

        System.out.println("Selection sort: ");
        solu.SelectionSort(arr1);
        solu.SelectionSort(arr2);
        solu.SelectionSort(arr3);
        solu.SelectionSort(arr4);

        System.out.println("Merge sort: ");
        solu.MergeSort(arr1);
        solu.MergeSort(arr2);
        solu.MergeSort(arr3);
        solu.MergeSort(arr4);

        System.out.println("Quick sort: ");
        solu.QuickSort(arr1);
        solu.QuickSort(arr2);
        solu.QuickSort(arr3);
        solu.QuickSort(arr4);
    }
}
