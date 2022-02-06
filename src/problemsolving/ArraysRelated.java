package problemsolving;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class ArraysRelated {

    public static void sortArray0s1s2s(int[] a) {
        int start = 0, mid = 0;
        int end = a.length - 1;
        int temp = 0;
        while (mid <= end) {
            switch (a[mid]) {
                case 0:
                    temp = a[start];
                    a[start] = a[mid];
                    a[mid] = temp;
                    start++;
                    mid++;
                    break;
                case 1:
                    mid++;
                    break;
                case 2:
                    temp = a[mid];
                    a[mid] = a[end];
                    a[end] = temp;
                    end--;
                    break;
            }
        }
    }

    public static void maxSumSubArray(int[] a) {
        int start = 0, end = 0;
        int currentMax = a[start], max = a[start];
        for (int i = 1; i < a.length; i++) {
            currentMax = currentMax + a[i];
            if (currentMax < a[i]) {
                start = i;
                currentMax = a[i];
            }
            if (currentMax > max) {
                end = i;
                max = currentMax;
            }
        }
        System.out.println(max + " " + start + " " + end);
    }

    public static int maxProfitOnShares(int[] a) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] < min) {
                min = a[i];
            } else if (a[i] - min > max) {
                max = a[i] - min;
            }
        }
        return max;
    }

    public static int[] twoSum(int[] a, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < a.length; i++) {
            if (map.containsKey(target - a[i])) {
                return new int[]{map.get(target - a[i]), i};
            } else {
                map.put(a[i], i);
            }
        }
        return new int[0];
    }

    public int romanToInt(String s) {
        int number = 0;
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("II", 2);
        map.put("III", 3);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("IX", 9);
        map.put("XL", 40);
        map.put("XC", 90);
        map.put("CD", 400);
        map.put("CM", 900);

        int i = 0;
        while (i < s.length()) {
            if (i != s.length() - 1) {
                String s1 = String.valueOf(s.charAt(i)) + String.valueOf(s.charAt(i + 1));
                if (map.containsKey(s1)) {
                    number += map.get(s1);
                    i += 2;
                } else {
                    number += map.get(String.valueOf(s.charAt(i)));
                    i++;
                }
            } else {
                number += map.get(String.valueOf(s.charAt(i)));
                i++;
            }
        }
        return number;
    }

    public String longestCommonPrefix(String[] strs) {
        String op = "";
        String fs = strs[0];
        for (int i = 0; i < fs.length(); i++) {
            boolean matched = true;
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() < fs.length()) {
                    matched = false;
                    continue;
                }
                if (fs.charAt(i) != strs[j].charAt(i)) {
                    matched = false;
                }
            }
            if (matched) {
                op = op + fs.charAt(i);
            }
        }
        return op;
    }

    public static boolean isValidParenthesis(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character ch = s.charAt(i);
            Character popped;
            switch (s.charAt(i)) {
                case '(':
                case '[':
                case '{':
                    stack.push(ch);
                    break;
                case ')':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    popped = stack.pop();
                    if (popped == '{' || popped == '[') {
                        return false;
                    }
                    break;
                case ']':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    popped = stack.pop();
                    if (popped == '(' || popped == '{') {
                        return false;
                    }
                    break;
                case '}':
                    if (stack.isEmpty()) {
                        return false;
                    }
                    popped = stack.pop();
                    if (popped == '(' || popped == '[') {
                        return false;
                    }
                    break;
                default:
                    break;

            }
        }
        return stack.isEmpty();
    }

    public static int removeDuplicates(int[] a) {
        if (a.length == 1) {
            return 1;
        }
        if (a.length == 0) {
            return 0;
        }
        int op = 0;
        for (int i = 0; i < a.length - 1; i++) {
            if (a[i] == a[i + 1]) {
            } else {
                op++;
            }
        }
        return a.length - op;
    }

    public int strStr(String haystack, String needle) {
        int m = haystack.length();
        int n = needle.length();
        if ((m == 0 && n == 0) || n == 0) {
            return 0;
        }
        for (int i = 0; i < m - n + 1; i++) {
            if (haystack.substring(i, i + n).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    public static int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        if (x < 4) {
            return 1;
        }
        for (int i = 2; i <= x / 2; i++) {
            if (i * i == x || (i + 1) * (i + 1) < 0 || (i * i < x && (i + 1) * (i + 1) > x)) {
                return i;
            }
        }
        return 0;
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (m == 0) {
            System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        } else if (n == 0) {
            return;
        }
        int i = m;
        int j = n;
        int k = nums1.length;
        int i1 = 0, j1 = 0, k1 = 0;
        while (i1 < i && j1 < j) {
            if (nums1[i1] > nums2[j1]) {
                nums1[k1] = nums2[j1];
                j1++;
            } else {
                nums1[k1] = nums1[i1];
                i1++;
            }
            k1++;
        }
        while (i1 < i) {
            nums1[k1] = nums1[i1];
            k1++;
            i1++;
        }
        while (j1 < j) {
            nums1[k1] = nums2[j1];
            k1++;
            j1++;
        }
    }

    public static void reverseString(char[] s) {
        int l = s.length;
        for (int i = 0; i < l / 2; i++) {
            char temp = s[i];
            s[i] = s[l - 1 - i];
            s[l - 1 - i] = temp;
        }
        System.out.println(Arrays.toString(s));
    }

    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int i : nums) {
            result ^= i;
        }
        return result;
    }

    public static void findDuplicate(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int j = Math.abs(a[i]);
            if (a[j] > 0) {
                a[j] = -a[j];
            } else {
                System.out.print(j + " ");
            }
        }
    }

    public static List<List<Integer>> threeSum(int[] a) {
        List<List<Integer>> list = new ArrayList<>();
        // if size of array is less than 3 then return empty list as no triplet is possible
        if (a.length < 3) {
            return list;
        }
        Arrays.sort(a);
        // we will be using 3 pointer approach
        // a[i] will be the first pointer which won't move
        // low and high
        // should satisfy the equation b + c = -a
        // so if a[low] + a[high] = -a[i] <--- then this is the triplet
        // validation and checks
        // 1. a[i] shouldn't be same as a[i-1]
        // 2. similarly a[low], a[low+1] and a[high], a[high+1] shouldn't be same once triplet is found
        for (int i = 0; i < a.length - 2; i++) {
            int num1 = a[i];
            if (i == 0 || (num1 != a[i - 1])) {
                int low = i + 1, high = a.length - 1;
                while (low < high) {
                    int num2 = a[low];
                    int num3 = a[high];
                    if ((num2 + num3) == -num1) {
                        list.add(Arrays.asList(num1, num2, num3));

                        while (a[low + 1] == num2) {
                            low++;
                        }
                        while (a[high - 1] == num3) {
                            high--;
                        }
                        low++;
                        high--;
                    } else if ((num2 + num3) < -num1) {
                        low++;
                    } else {
                        high--;
                    }
                }
            }
        }
        return list;
    }

    // TODO
    public static int[] productExceptSelf(int[] a) {
        int n = a.length;
        int[] res = new int[n];
        int left = 1;
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                left *= a[i - 1];
            }
            res[i] = left;
        }
        int right = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (i < n - 1) {
                right *= a[i + 1];
            }
            res[i] *= right;
        }
        return res;
    }

    static boolean areTheyEqual(int[] a, int[] b) {
        // Write your code here
        int left = 0;
        int right = a.length - 1;
        while (left <= right) {
            if (left == right) {
                return true;
            }
            if (a[left] == b[right]) {
                left++;
                right--;
            } else if (a[left] == b[left]) {
                left++;
            } else if (a[right] == b[right]) {
                right--;
            } else if (a[left] != b[right]) {
                return false;
            }
        }
        return false;
    }

    public static String yeildLargestValue(int[] a) {
        String s = "";
        for (int j : a) {
            s = s + j;
        }
        char[] ch = s.toCharArray();    
        Arrays.sort(ch);
        String ans = "";
        for(int i = ch.length-1;i>=0;i--) {
            ans += ch[i]; 
        }
        return ans;
    }

    public static void main(String[] args) {

        //int[] a = {1, 2, 0, 0, 1, 1, 2, 0, 1, 2};
        //sortArray0s1s2s(a);
        //int[] a = {-2, 1, 4, -1, -2, 1, 5, -3};
        // maxSumSubArray(a);
        //int[] a = {7, 6, 4, 9, 1};
        //System.out.println(maxProfitOnShares(a));
        //int[] a = {3, 3};
        //System.out.println(Arrays.toString(twoSum(a, 9)));
        //System.out.println(isValidParenthesis("(}"));
        //System.out.println(removeDuplicates(new int[]{1, 1, 2}));
        //System.out.println(mySqrt(2147483647));


        //int[] a = {1, 2, 3, 0, 0, 0};
        //int[] b = {2, 5, 6};
        //merge(a, 3, b, 3);

        //reverseString(new char[]{'h', 'e', 'l', 'l', 'o'});

        //System.out.println(singleNumber(new int[]{1, 2, 2, 1, 3}));

//        findDuplicate(new int[]{1, 2, 3, 1, 3, 6, 6, 0, 0});

//        System.out.println(threeSum(new int[]{-1, 0, 1, 2, -1, -4}));

//        int[] a = {1, 2, 3, 4};
//        int[] b = {1, 4, 3, 3};
//        System.out.print(areTheyEqual(a, b));

        int[] numArray = {1, 34, 3, 98, 9, 76, 45, 4};
        System.out.println(Arrays.toString(numArray));
        System.out.println(yeildLargestValue(numArray));
    }
}
