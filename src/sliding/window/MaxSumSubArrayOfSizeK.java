package sliding.window;

public class MaxSumSubArrayOfSizeK {

    public static int maxSum(int[] a, int k) {
        int maxSum = 0;
        int windowSum = 0;
        int start = 0;
        for (int i = 0; i < k; i++) {
            windowSum += a[i];
        }
        maxSum = windowSum;
        int end = k - 1;
        for (int i = k; i < a.length; i++) {
            windowSum += a[i] - a[i - k];
            if (windowSum > maxSum) {
                maxSum = windowSum;
                start = i - k + 1;
                end = i;
            }
        }
        System.out.println(start + " " + end);
        return maxSum;
    }

    public static void main(String[] args) {

        int[] a = {1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(maxSum(a, 3));
    }
}
