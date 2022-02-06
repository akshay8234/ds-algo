package sliding.window;

public class MaxSumSubArray {

    public static int maxSum(int[] a) {
        int window = 0;
        int max = Integer.MIN_VALUE;
        int start = 0;
        int end = 0;
        for (int i = 0; i < a.length; i++) {
            window += a[i];
            if (window <= a[i]) {
                start = i;
                window = a[i];
            }
            if (max < window) {
                max = window;
                end = i;
            }
        }
        System.out.println(start + " " + end);
        return max;
    }

    public static void main(String[] args) {

        int[] a = {-2, -3, 4, -1, -2, 1, 5, -3};
        System.out.println(maxSum(a));
    }
}
