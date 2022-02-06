package problemsolving;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueRelated {

    static class CustomComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            if (Objects.equals(o1, o2)) {
                return 0;
            }
            return o1 < o2 ? 1 : -1;
        }
    }

    static class NaturalComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
//            if (Objects.equals(o1, o2)) {
//                return 0;
//            }
            return o2 < o1 ? 1 : -1;
        }
    }

    public static void sortKSortedArray(int[] a, int k) {
        int index = 0;
        Queue<Integer> q = new PriorityQueue<>();
        for (int num : a) {
            q.add(num);
            if (q.size() > k) {
                a[index++] = q.poll();
            }
        }

        while (!q.isEmpty()) {
            a[index++] = q.poll();
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, Comparator.comparingInt(a -> a.val));
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        for (ListNode node : lists)
            if (node != null)
                queue.add(node);

        while (!queue.isEmpty()) {
            tail.next = queue.poll();
            tail = tail.next;

            if (tail.next != null)
                queue.add(tail.next);
        }
        return dummy.next;
    }

    static class Pair {
        int num;
        int count;

        Pair() {
        }

        Pair(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    public static List<Integer> topKFrequentNumbers(int[] a, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : a) {
            map.put(i, map.getOrDefault(i, 1) + 1);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(map::get));
        for (int i : map.keySet()) {
            queue.add(i);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        return new ArrayList<>(queue);
    }

    public static int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        // count frequency of each number
        Arrays.stream(nums).forEach(n -> map.put(n, map.getOrDefault(n, 0) + 1));
        int[] a = new int[nums.length];
        int[] count = {0};
        map.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(m -> {
            int i = m.getValue();
            while (i-- > 0) {
                a[count[0]++] = m.getKey();
            }
        });
        return a;
    }
    
    public static int connectRopesToMinimizeTheCost(int[] a) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i : a) {
            pq.add(i);
        }
        int cost = 0;
        while (pq.size() > 1) {
            int sum = pq.poll() + pq.poll();
            cost += sum;
            pq.add(sum);
        }
        return cost;
    }

    public static void main(String[] args) {

//        PriorityQueue<Integer> queue = new PriorityQueue<>();
//        queue.add(1);
//        queue.add(2);
//        queue.add(5);
//        queue.add(6);
//
//        System.out.println(queue);
//
//        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(new CustomComparator());
//        maxQueue.add(1);
//        maxQueue.add(-1);
//        maxQueue.add(5);
//        maxQueue.add(4);
//        maxQueue.add(-1);
//        maxQueue.add(68);
//
//        System.out.println(maxQueue);
//
//        PriorityQueue<Integer> pq = new PriorityQueue<>();
//        pq.add(1);
//        pq.add(2);
//        pq.add(5);
//        pq.add(6);
//        System.out.println("PriorityQueue: " + pq);
//
//
//        int[] a = {6, 1, 1, 2, 8, 10, 9};
//        sortKSortedArray(a, 3);
//        System.out.println(Arrays.toString(a));

//        int[] a = {12, 16, 22, 30, 35, 39, 42, 45, 48, 50, 53, 55, 56};
//        System.out.println(findKClosestNumber(a, 4, 35));
//        System.out.println(topKFrequentNumbers(new int[]{1, 1, 1, 2, 2, 3}, 2));
//        System.out.println(Arrays.toString(frequencySort(new int[]{1, 1, 1, 2, 2, 3})));
        System.out.println(connectRopesToMinimizeTheCost(new int[]{1, 2, 3, 4, 5}));
    }
}
