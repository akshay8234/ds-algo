import java.util.ArrayDeque;
import java.util.Deque;

public class DataStructures {

    public static void understandDeque() {
        Deque<Integer> deque = new ArrayDeque<>();
        deque.offer(1);
        deque.offer(2);
        deque.offer(3);
        deque.offer(4);
        deque.offer(5);
        deque.offer(6);
        deque.offer(7);
        deque.offer(8);
        deque.offer(9);
        deque.offer(10);

        while (!deque.isEmpty()) {

            Integer poll = deque.poll();
            Integer pollFirst = deque.pollFirst();
            Integer pollLast = deque.pollLast();
            Integer peek = deque.peek();
            Integer peekFirst = deque.peekFirst();
            Integer peekLast = deque.peekLast();
            System.out.println();
        }
    }

    public static void main(String[] args) {

        understandDeque();
    }
}
