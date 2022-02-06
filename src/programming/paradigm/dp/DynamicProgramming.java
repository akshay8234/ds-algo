package programming.paradigm.dp;

import java.util.Arrays;

public class DynamicProgramming {

    public static int[][] knapsackTab = new int[101][101];

    static {
        for (int[] a : knapsackTab) {
            Arrays.fill(a, -1);
        }
    }

    /**
     * 1. Think of the base case <br></br>
     * a. think about the smallest output, in this case it will be 0 whether capacity is not enough or no items in the store <br></br>
     * 2. Start from the top most element and keep subtracting until it reaches the base case <br></br>
     */
    public static int knapsack01Recursive(int[] weight, int[] value, int capacity, int n) {
        if (capacity == 0 || n == 0) {
            return 0;
        }
        // if weight at position n-1 is <= capacity then return max of value if weight is added or not added
        if (weight[n - 1] <= capacity) {
            return Math.max(value[n - 1] + knapsack01Recursive(weight, value, capacity - weight[n - 1], n - 1),
                    knapsack01Recursive(weight, value, capacity - weight[n - 1], n - 1));
        } else {
            // if weight at position n - 1 is > capacity then this item cannot be included in the bag
            return knapsack01Recursive(weight, value, capacity - weight[n - 1], n - 1);
        }
    }

    /**
     * From the above recursive solution build dynamic solution <br></br>
     * find the variable which is keep changing <br></br>
     * Then create 1D array or 2D matrix accordingly <br></br>
     * In this case weight, and  it will be tab[n+1][w+1] <br></br>
     * If value at position != -1 then use that value or else call recursive function and update the value in the matrix <br></br>
     * <br></br>
     */
    public static int knapsack01Memo(int[] weight, int[] value, int capacity, int n) {
        if (knapsackTab[n][capacity] != -1) {
            return knapsackTab[n][capacity];
        }
        if (capacity == 0 || n == 0) {
            return 0;
        }
        // if weight at position n-1 is <= capacity then store max of value if weight is added or not added
        if (weight[n - 1] <= capacity) {
            knapsackTab[n][capacity] = Math.max(value[n - 1] + knapsack01Memo(weight, value, capacity - weight[n - 1], n - 1),
                    knapsack01Memo(weight, value, capacity, n - 1));
        } else {
            // if weight at position n - 1 is > capacity then this item cannot be included in the bag
            knapsackTab[n][capacity] = knapsack01Memo(weight, value, capacity, n - 1);
        }
        return knapsackTab[n][capacity];
    }

    public static int knapsack01Tab(int[] weight, int[] value, int capacity, int n) {
        int[][] tab = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= capacity; j++) {
                if (i == 0 || j == 0) {
                    tab[i][j] = 0;
                } else if (weight[i - 1] <= j) {
                    tab[i][j] = Math.max(value[i - 1] + tab[i - 1][j - weight[i - 1]], tab[i - 1][j]);
                } else {
                    tab[i][j] = tab[i - 1][j];
                }
            }
        }
        return tab[n][capacity];
    }

    public static boolean subsetSumRec(int targetSum, int[] a, int n) {
        if (targetSum == 0) {
            return true;
        }
        if (n == 0) {
            return false;
        }
        if (a[n - 1] <= targetSum) {
            return subsetSumRec(targetSum, a, n - 1) || subsetSumRec(targetSum - a[n - 1], a, n - 1);
        } else {
            return subsetSumRec(targetSum, a, n - 1);
        }
    }

    public static boolean subsetSumMemoized(int targetSum, int[] a, int n, boolean[][] b) {
        if (b[n][targetSum]) {
            return true;
        }
        if (targetSum == 0) {
            return true;
        }
        if (n == 0) {
            return false;
        }
        if (a[n - 1] <= targetSum) {
            b[n][targetSum] = subsetSumMemoized(targetSum, a, n - 1, b) || subsetSumMemoized(targetSum - a[n - 1], a, n - 1, b);
        } else {
            b[n][targetSum] = subsetSumMemoized(targetSum, a, n - 1, b);
        }

        return b[n][targetSum];
    }

    public static boolean subsetSumTab(int targetSum, int[] a, int n, boolean[][] b) {
        for (int i = 0; i <= n; i++) { // number of elements
            for (int j = 0; j <= targetSum; j++) { // target sum
                if (i == 0 || j == 0) {
                    b[i][j] = false;
                } else if (a[i - 1] > j) {
                    b[i][j] = b[i - 1][j];
                } else if (j == a[i - 1]) {
                    b[i][j] = true;
                } else {
                    b[i][j] = b[i - 1][j] || b[i - 1][j - a[i - 1]];
                }
            }
        }
        return b[n][targetSum];
    }

    public static boolean equalSumSubset(int[] a, int n, boolean[][] b) {
        int sum = 0;
        for (int i : a) {
            sum += i;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int targetSum = sum / 2;
        return subsetSumMemoized(targetSum, a, n, b);
    }

    public static boolean equalSumSubsetTab(int[] a, int n) {
        int sum = 0;
        for (int i : a) {
            sum += i;
        }
        if (sum % 2 != 0) {
            return false;
        }
        int targetSum = sum / 2;
        return subsetSumTab(targetSum, a, n, new boolean[n + 1][targetSum + 1]);
    }

    public static int countSubsetSumRec(int[] a, int n, int targetSum) {
        if (targetSum == 0) {
            return 1;
        }
        if (n == 0) {
            return 0;
        }
        if (targetSum >= a[n - 1]) {
            // exclude + include
            return countSubsetSumRec(a, n - 1, targetSum) + countSubsetSumRec(a, n - 1, targetSum - a[n - 1]);
        } else {
            return countSubsetSumRec(a, n - 1, targetSum);
        }
    }

    public static int countSubsetSumMemo(int[] a, int n, int targetSum, int[][] t) {
        if (targetSum == 0) {
            return 1;
        }
        if (n == 0) {
            return 0;
        }
        if (t[n][targetSum] != 0) {
            return t[n][targetSum];
        }
        if (targetSum >= a[n - 1]) {
            // exclude + include
            t[n][targetSum] = countSubsetSumRec(a, n - 1, targetSum) + countSubsetSumRec(a, n - 1, targetSum - a[n - 1]);
        } else {
            t[n][targetSum] = countSubsetSumRec(a, n - 1, targetSum);
        }
        return t[n][targetSum];
    }

    public static int countSubsetSumTab(int[] a, int targetSum) {
        int[][] tab = new int[a.length + 1][targetSum + 1];
        tab[0][0] = 1;
        for (int i = 0; i <= a.length; i++) {
            tab[i][0] = 1;
        }
        for (int i = 1; i <= a.length; i++) {
            for (int j = 1; j <= targetSum; j++) {
                if (a[i - 1] > j) {
                    tab[i][j] = tab[i - 1][j];
                } else {
                    tab[i][j] = tab[i - 1][j] + tab[i - 1][j - a[i - 1]];
                }
            }
        }
        return tab[a.length][targetSum];
    }

    public static int minimumSubsetSumDiff(int[] a) {
        // get the sum of all elements of given array
        int range = 0;
        for (int i : a) {
            range += i;
        }
        // find subset with given range
        boolean[][] tab = new boolean[a.length + 1][range + 1];
        for (int i = 0; i < tab.length; i++) {
            tab[i][0] = true;
        }
        for (int i = 1; i < tab.length; i++) {
            for (int j = 1; j < tab[0].length; j++) {
                // can't be included
                if (j < a[i - 1]) {
                    tab[i][j] = tab[i - 1][j];
                } else if (j > a[i - 1]) { // either can be included or not
                    tab[i][j] = tab[i - 1][j] || tab[i - 1][j - a[i - 1]];
                } else if (j == a[i - 1]) {
                    tab[i][j] = true;
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= (tab[0].length) / 2; i++) {
            if (tab[a.length][i]) {
                min = Math.min(min, Math.abs(range - 2 * i));
            }
        }
        return min;
    }

    public static int countNoOfSubsetWithGivenDiff(int[] a, int diff) {
        // sub1 + sub2 = sum ---(i)
        // sub1 - sub2 = diff ---(ii)
        // (i) + (ii)
        // sub1 = (sum + diff)/2
        // so basically if we would be able to get the count of first subset which is (sum - diff)
        // get the sum of all elements of given array
        int sum = 0;
        for (int i : a) {
            sum += i;
        }
        if (sum < diff || (sum - diff) % 2 == 1) {
            return 0;
        }
        // find subset with given range
        int range = Math.abs(sum + diff) / 2;
        int[][] tab = new int[a.length + 1][range + 1];
        for (int i = 0; i < tab.length; i++) {
            tab[i][0] = 1;
        }
        for (int i = 1; i < tab.length; i++) {
            for (int j = 1; j < tab[0].length; j++) {
                // can't be included
                if (j < a[i - 1]) {
                    tab[i][j] = tab[i - 1][j];
                } else if (j >= a[i - 1]) { // either can be included or not
                    tab[i][j] = tab[i - 1][j] + tab[i - 1][j - a[i - 1]];
                }
            }
        }
        return tab[a.length][range];
    }

    public static int coinChangeUnboundedKnapsack(int[] a, int sum) {
        int arraySum = 0;
        for (int i = 0; i < a.length; i++) {
            arraySum += a[i];
        }
        if (sum < 0) {
            return -1;
        }
        if (sum == 0) {
            return 0;
        }
        int[][] tab = new int[a.length + 1][sum + 1];
        for (int i = 0; i < tab.length; i++) {
            tab[i][0] = 1;
        }
        for (int i = 1; i < tab.length; i++) {
            for (int j = 1; j < tab[0].length; j++) {
                if (a[i - 1] <= j) {
                    tab[i][j] = tab[i - 1][j] + tab[i][j - a[i - 1]];
                } else {
                    tab[i][j] = tab[i - 1][j];
                }
            }
        }
        return tab[a.length][sum];
    }

    public static int minCoinChangeUnboundedKnapsack(int[] a, int sum) {
        int arraySum = 0;
        for (int i = 0; i < a.length; i++) {
            arraySum += a[i];
        }
        if (sum < 0) {
            return -1;
        }
        if (sum == 0) {
            return 0;
        }
        double[][] tab = new double[a.length + 1][sum + 1];
        for (int j = 0; j <= sum; j++) {
            tab[0][j] = Double.POSITIVE_INFINITY;
        }
        for (int i = 1; i < tab.length; i++) {
            for (int j = 1; j < tab[0].length; j++) {
                if (a[i - 1] <= j) {
                    tab[i][j] = Math.min(tab[i - 1][j], 1 + tab[i][j - a[i - 1]]);
                } else {
                    tab[i][j] = tab[i - 1][j];
                }
            }
        }
        return (tab[a.length][sum] == Double.POSITIVE_INFINITY) || (tab[a.length][sum] < 0)
                ? -1 : (int) tab[a.length][sum];
    }

    public static int longestCommonSubsequence(String s1, String s2, int m, int n) {
        // base case
        if (m == 0 || n == 0) {
            return 0;
        }
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            // char matches at position m-1 and n-1
            return 1 + longestCommonSubsequence(s1, s2, m - 1, n - 1);
        } else {
            // char does not match, there are two choices: either consider rest of char from string s1 or consider string s2
            return Math.max(longestCommonSubsequence(s1, s2, m - 1, n), longestCommonSubsequence(s1, s2, m, n - 1));
        }
    }

    public static int longestCommonSubsequenceMemo(String s1, String s2, int m, int n, int[][] tab) {
        // base case
        if (m == 0 || n == 0) {
            return 0;
        }
        if (tab[m][n] != 0) {
            return tab[m][n];
        }
        if (s1.charAt(m - 1) == s2.charAt(n - 1)) {
            // char matches at position m-1 and n-1
            tab[m][n] = 1 + longestCommonSubsequenceMemo(s1, s2, m - 1, n - 1, tab);
        } else {
            // char does not match, there are two choices: either consider rest of char from string s1 or consider string s2
            tab[m][n] = Math.max(longestCommonSubsequenceMemo(s1, s2, m - 1, n, tab), longestCommonSubsequenceMemo(s1, s2, m, n - 1, tab));
        }
        return tab[m][n];
    }

    public static int longestCommonSubsequenceTab(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] tab = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    tab[i][j] = 1 + tab[i - 1][j - 1];
                } else {
                    tab[i][j] = Math.max(tab[i - 1][j], tab[i][j - 1]);
                }
            }
        }

        printLongestCommonSubsequence(s1, s2, tab);
        return tab[m][n];
    }

    public static void printLongestCommonSubsequence(String s1, String s2, int[][] tab) {
        StringBuilder longestCommonSubsequence = new StringBuilder();
        int i = tab.length - 1, j = tab[0].length - 1;
        while (i > 0 && j > 0) {
            if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                longestCommonSubsequence.insert(0, s1.charAt(i - 1));
                i--;
                j--;
            } else {
                if (tab[i - 1][j] > tab[i][j - 1]) {
                    i--;
                } else {
                    j--;
                }
            }
        }
        System.out.println(longestCommonSubsequence);
    }

    public static int longestCommonSubstringTab(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();
        int[][] tab = new int[m + 1][n + 1];
        int max = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    tab[i][j] = 1 + tab[i - 1][j - 1];
                    max = Math.max(tab[i][j], max);
                } else {
                    tab[i][j] = 0;
                }
            }
        }
        return max;
    }

    public static String shortestCommonSupersequence(String str1, String str2) {
        int m = str1.length();
        int n = str2.length();
        int[][] tab = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 0) {
                    tab[i][j] = j;
                } else if (j == 0) {
                    tab[i][j] = i;
                } else if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    tab[i][j] = 1 + tab[i - 1][j - 1];
                } else {
                    tab[i][j] = Math.max(tab[i][j - 1], tab[i - 1][j]);
                }
            }
        }

        int superSequenceLength = m + n - tab[m][n];
        char[] superSequence = new char[superSequenceLength];
        int i = m;
        int j = n;
        while (i > 0 && j > 0) {
            char ch;
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                ch = str1.charAt(i - 1);
                i--;
                j--;
            } else {
                if (tab[i - 1][j] > tab[i][j - 1]) {
                    ch = str1.charAt(i - 1);
                    i--;
                } else {
                    ch = str2.charAt(j - 1);
                    j--;
                }
            }
            superSequence[superSequenceLength - 1] = ch;
            superSequenceLength--;
        }
        while (i > 0) {
            superSequence[superSequenceLength - 1] = str1.charAt(i - 1);
            i--;
            superSequenceLength--;
        }
        while (j > 0) {
            superSequence[superSequenceLength - 1] = str2.charAt(j - 1);
            j--;
            superSequenceLength--;
        }
        return new String(superSequence);
    }

    public static int mcm(int[] a, int i, int j) {
        // base condition
        if (i >= j) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int temp = mcm(a, i, k) + mcm(a, k + 1, j) + a[i - 1] * a[k] * a[j];
            if (temp < min) {
                min = temp;
            }
        }
        return min;
    }

    static int[][] b = new int[100][100];

    static {
        for (int[] row : b)
            Arrays.fill(row, -1);
    }

    public static int mcmMemoized(int[] a, int i, int j) {
        // base condition
        if (i >= j) {
            return 0;
        }
        if (b[i][j] != -1) {
            return b[i][j];
        }
        b[i][j] = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            b[i][j] = Math.min(b[i][j], mcmMemoized(a, i, k) + mcmMemoized(a, k + 1, j) + a[i - 1] * a[k] * a[j]);
        }
        return b[i][j];
    }

    public static int palindromicPartitioningRecursive(String s, int i, int j) {
        if (i >= j || isPalindrome(s, i, j)) {
            return 0;
        }
        int min = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int temp = palindromicPartitioningRecursive(s, i, k) + palindromicPartitioningRecursive(s, k + 1, j) + 1;
            if (temp < min) {
                min = temp;
            }
        }
        return min;
    }

    public static int palindromicPartitioningMemo(String s, int i, int j) {
        if (i >= j || isPalindrome(s, i, j)) {
            return 0;
        }
        b[i][j] = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            b[i][j] = Math.min(palindromicPartitioningMemo(s, i, k) + palindromicPartitioningMemo(s, k + 1, j) + 1, b[i][j]);
        }
        return b[i][j];
    }

    public static boolean isPalindrome(String s, int i, int j) {
        while (i <= j) {
            if (s.charAt(i++) != s.charAt(j--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        int[] weight = {1, 2, 3};
        int[] value = {10, 15, 40};
        int capacity = 6;
        int n = 3;
//        System.out.println(knapsack01Tab(weight, value, capacity, n));
//        System.out.println(knapsack01Memo(weight, value, capacity, n));
//        System.out.println(knapsack01Recursive(weight, value, capacity, n));
//        System.out.println(subsetSumRec(58, new int[]{1, 2, 5}, 3));
//        System.out.println(subsetSumMemoized(0, new int[]{1, 5, 5, 11}, 4, new boolean[5][12]));
//        System.out.println(subsetSumTab(11, new int[]{1, 5, 5, 11}, 4, new boolean[5][12]));
//        System.out.println(equalSumSubset(new int[]{1, 2, 5}, 3, new boolean[4][12]));
//        System.out.println(equalSumSubsetTab(new int[]{1, 2, 5}, 3));
//        System.out.println(countSubsetSumRec(new int[]{1, 5, 5, 11}, 4, 11));
//        System.out.println(countSubsetSumMemo(new int[]{1, 5, 5, 11}, 4, 11, new int[5][12]));
//        System.out.println(countSubsetSumTab(new int[]{1, 5, 5, 11}, 11));
//        System.out.println(minimumSubsetSumDiff(new int[]{31, 26, 33, 21, 40}));
//        System.out.println(countNoOfSubsetWithGivenDiff(new int[]{1, 0, 0, 1}, 0));
//        System.out.println(coinChangeUnboundedKnapsack(new int[]{2, 5, 10, 1}, 27));
//        System.out.println(minCoinChangeUnboundedKnapsack(new int[]{2}, 3));
//        System.out.println(longestCommonSubsequence("abcdf", "abbbcdef", 5, 8));
//        System.out.println(longestCommonSubsequenceMemo("abcdf", "abbbcdef", 5, 8, new int[6][9]));
//        System.out.println(longestCommonSubsequenceTab("abac", "cab"));
//        System.out.println(longestCommonSubstringTab("abbcdabachj", "abcfabacj"));
//        System.out.println(shortestCommonSupersequence("abac", "cab"));
//        System.out.println(mcm(new int[]{1, 2, 3, 4, 3}, 1, 4));
//        System.out.println(mcmMemoized(new int[]{1, 2, 3, 4}, 1, 3));
        String s = "ababbbabbababa";
//        System.out.println(palindromicPartitioningRecursive(s, 0, s.length() - 1));
        System.out.println(palindromicPartitioningMemo(s, 0, s.length() - 1));
    }
}
