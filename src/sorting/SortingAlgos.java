package sorting;

import java.util.Arrays;

public class SortingAlgos {

    /**
     * Explanation in short <br></br>
     * 1. (a) start should be less than end, find mid index <br></br>
     * (b) sort first halves <br></br>
     * (c) sort second halves <br></br>
     * (d) merge both sorted arrays, follow the step 2 for merging <br></br><br></br>
     * 2. (a) Calculate the length of both arrays, n1 = mid - start + 1; n2 = end - mid; <br></br>
     * (b) Create temp sub-array with the above defined length <br></br>
     * (c) copy data into the temp sub-array from the given array <br></br>
     * (d) merge the temp sub array by comparing the element in sorted order <br></br>
     * (e) copy the remaining element of both array if any <br></br>
     * (f) resulted array is the sorted array
     */
    public static void mergeSort(int[] a, int start, int end) {
        if (start < end) {
            // find mid
            int mid = (start + end) / 2;
            // sort first half
            mergeSort(a, start, mid);
            // sort second half
            mergeSort(a, mid + 1, end);
            // merge the sorted half
            merge(a, start, mid, end);
        }
    }

    public static void merge(int[] a, int start, int mid, int end) {
        // find size of two sub arrays to be merged
        int n1 = mid - start + 1;
        int n2 = end - mid;

        // create temp arrays
        int[] a1 = new int[n1];
        int[] a2 = new int[n2];

        // copy data to temp arrays
        for (int i = 0; i < n1; i++) {
            a1[i] = a[start + i];
        }
        for (int i = 0; i < n2; i++) {
            a2[i] = a[mid + 1 + i];
        }

        // merge the temp subarrays
        // initial index of first and second subarray
        int i = 0, j = 0;

        // initial index of merged subarray
        int k = start;

        while (i < n1 && j < n2) {
            if (a1[i] <= a2[j]) {
                a[k] = a1[i];
                i++;
            } else {
                a[k] = a2[j];
                j++;
            }
            k++;
        }

        // copy remaining elements of both array if any
        while (i < n1) {
            a[k] = a1[i];
            i++;
            k++;
        }
        while (j < n2) {
            a[k] = a2[j];
            j++;
            k++;
        }
    }

    public static void bubbleSort(int[] a) {
        boolean swapped;
        for (int i = 0; i < a.length - 1; i++) {
            swapped = false;
            for (int j = 0; j < a.length - i - 1; j++) {
                if (a[j] > a[j + 1]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
    }

    public static void recursiveBubbleSort(int[] a, int length) {
        if (length == 1) {
            return;
        }
        for (int i = 0; i < length - 1; i++) {
            if (a[i] > a[i + 1]) {
                int temp = a[i];
                a[i] = a[i + 1];
                a[i + 1] = temp;
            }
        }
        recursiveBubbleSort(a, length - 1);
    }

    public static void insertionSort(int[] a) {
        for (int i = 1; i < a.length; i++) {
            int j = i - 1;
            while (j >= 0) {
                if (a[i] < a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
                j--;
            }
        }
    }

    public static void selectionSort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }
    }

    /**
     * 1. initialize i as neagtive number, choose pivot element, traverse from start to end <br></br>
     * (a). check if a[j] <= pivot -> true -> increment i and swap a[i], a[j] <br></br>
     * 2. once loop is over then place the pivot element at correct position <br></br>
     * (a). increment i <br></br>
     * (b). swap a[i], a[j] <br></br>
     *
     * @param a
     * @param start
     * @param end
     * @return
     */
    public static int partition(int[] a, int start, int end) {
        int pivot = a[end];
        int i = start - 1;
        for (int j = start; j < end; j++) {
            if (a[j] <= pivot) {
                i++;
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
            }
        }
        i = i + 1;
        int temp = a[i];
        a[i] = a[end];
        a[end] = temp;
        return i;
    }

    public static void quickSort(int[] a, int start, int end) {
        if (start < end) {
            int partition = partition(a, start, end);
            quickSort(a, start, partition - 1);
            quickSort(a, partition + 1, end);
        }
    }

    public static void heapify(int[] a, int size, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && a[left] > a[largest]) {
            largest = left;
        } else if (right < size && a[right] > a[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = a[i];
            a[i] = a[largest];
            a[largest] = temp;
            // recursively heapify the affected subtree
            heapify(a, size, largest);
        }
    }

    public static void heapSort(int[] a) {
        // build max heap
        for (int i = a.length / 2 - 1; i >= 0; i--) {
            heapify(a, a.length, i);
        }
        // extract largest one by one from heap
        for (int i = a.length - 1; i > 0; i--) {
            // move current to end
            int temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            heapify(a, i, 0);
        }
    }

    public static void main(String[] args) {

        int[] a = {64, 25, 12, 22, 11};
        System.out.println(Arrays.toString(a));
        //mergeSort(a, 0, a.length - 1);
        //bubbleSort(a);
        //recursiveBubbleSort(a, 6);
        //insertionSort(a);
        //selectionSort(a);
        //quickSort(a, 0, 4);
        heapSort(a);
        System.out.println(Arrays.toString(a));
    }
}
