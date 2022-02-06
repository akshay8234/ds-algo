package problemsolving;

import java.util.Arrays;
import java.util.Objects;
import java.util.Stack;

public class StackRelated {

    public static int[] ngrBruteForce(int[] a) {
        int[] result = new int[a.length];
        result[a.length - 1] = -1;
        for (int i = 0; i < a.length - 1; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] < a[j]) {
                    result[i] = a[j];
                    break;
                }
            }
        }
        return result;
    }

    public static int[] ngrOptimal(int[] a) {
        int[] result = new int[a.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = a.length - 1; i >= 0; i--) {
            if (stack.isEmpty()) {
                result[i] = -1;
            } else if (stack.peek() > a[i]) {
                result[i] = stack.peek();
            } else {
                while (!stack.isEmpty() && a[i] >= stack.peek()) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    result[i] = -1;
                } else {
                    result[i] = stack.peek();
                }
            }
            stack.push(a[i]);
        }
        return result;
    }

    public static int[] nglBruteForce(int[] a) {
        int[] result = new int[a.length];
        result[0] = -1;
        for (int i = a.length - 1; i > 0; i--) {
            for (int j = i - 1; j >= 0; j--) {
                if (a[j] > a[i]) {
                    result[i] = a[j];
                    break;
                } else {
                    result[i] = -1;
                }
            }
        }
        return result;
    }

    public static int[] nglOptimal(int[] a) {
        int[] result = new int[a.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < a.length; i++) {
            if (stack.isEmpty()) {
                result[i] = -1;
            } else if (a[i] < stack.peek()) {
                result[i] = stack.peek();
            } else {
                while (!stack.isEmpty() && a[i] >= stack.peek()) {
                    stack.pop();
                }
                if (stack.isEmpty()) {
                    result[i] = -1;
                } else {
                    result[i] = stack.peek();
                }
            }
            stack.push(a[i]);
        }
        return result;
    }

    public static int[] stockSpanBruteforce(int[] a) {
        int[] result = new int[a.length];
        result[0] = 1;
        for (int i = a.length - 1; i >= 1; i--) {
            int current = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (a[j] <= a[i]) {
                    current++;
                } else {
                    result[i] = current;
                }
            }
        }
        return result;
    }

    public static int[] stockSpanOptimal(int[] a) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            if (stack.isEmpty()) {
                result[i] = 1;
            } else if (a[stack.peek()] > a[i]) {
                result[i] = i - stack.peek();
            } else {
                while (!stack.isEmpty() && a[stack.peek()] < a[i]) {
                    stack.pop();
                }
                if (!stack.isEmpty()) {
                    result[i] = i - stack.peek();
                } else {
                    result[i] = i + 1;
                }
            }
            stack.push(i);
        }
        return result;
    }

    public static int[] contiguousSubArray(int[] a) {
        int[] l = new int[a.length];
        int[] r = new int[a.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        l[0] = 1;
        String s= Objects.isNull(null) ? "null" : "test"; 
        // find next greater to left
        for (int i = 1; i < a.length; i++) {
            while (!stack.isEmpty() && a[stack.peek()] < a[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                l[i] = i + 1;
            } else {
                l[i] = i - stack.peek();
            }
            stack.push(i);
        }

        stack.clear();
        r[r.length - 1] = l[l.length - 1];
        stack.push(a.length - 1);
        for (int i = a.length - 2; i >= 0; i--) {
            while (!stack.isEmpty() && a[stack.peek()] < a[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                r[i] = (a.length - i) + l[i] - 1;
            } else {
                r[i] = (stack.peek() - i) + l[i] - 1;
            }
            stack.push(i);
        }
        return r;
    }

    public static void main(String[] args) {

//        int[] a = {1, 3, 2, 4};
//        System.out.println(Arrays.toString(ngrBruteForce(a)));
//        System.out.println(Arrays.toString(ngrOptimal(a)));
//        System.out.println(Arrays.toString(nglBruteForce(a)));
//        System.out.println(Arrays.toString(nglOptimal(a)));
//        int[] b = {10, 4, 5, 90, 120, 80};
//        System.out.println(Arrays.toString(stockSpanBruteforce(b)));
//        System.out.println(Arrays.toString(stockSpanOptimal(b)));
        int[] contiguousSubArray = {3, 4, 1, 6, 2};
        System.out.print(Arrays.toString(contiguousSubArray(contiguousSubArray)));
    }
}
