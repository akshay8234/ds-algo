package problemsolving;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class SlidingWindow {

    public static int[] maxSlidingWindow(int[] a, int w) {
        int n = a.length;
        int[] result = new int[n - w + 1];
        int index = 0;
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < a.length; i++) {
            // remove numbers out of range k
            while (!deque.isEmpty() && deque.peek() < i-w+1) {
                deque.poll();
            }
            // remove smaller numbers in window range as they are useless
            while (!deque.isEmpty() && a[deque.peekLast()] <= a[i]) {
                deque.pollLast();
            }
            deque.offer(i);
            if (i >= (w - 1)) {
                result[index++] = a[deque.peek()];
            }
        }
        return result;
    }

    public static void main(String[] args) {

        int[] a = {1, 3, 1, 2, 0, 5};//{1, 3, -1, -3, 5, 3, 6, 7};
        System.out.println(Arrays.toString(maxSlidingWindow(a, 3)));
    }
}
