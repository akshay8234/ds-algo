package searching;

public class SearchingAlgorithms {

    public static int binarySearchIterative(int[] a, int number) {
        int start = 0;
        int end = a.length - 1;
        while (start <= end) {
            int mid = (start + end) / 2;
            if (a[mid] == number) {
                return mid;
            }
            if (a[mid] > a[start]) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(binarySearchIterative(a, 9));
    }
}
