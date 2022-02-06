package problemsolving;

import java.util.HashMap;
import java.util.Map;

public class Hashing {

    static int numberOfWays(int[] a, int k) {
        // Write your code here
        Map<Integer, Integer> map = new HashMap<>();
        int pair = 0;
        for(int i = 0; i<a.length; i++) {
            if(map.containsKey(a[i])) {
                map.put(a[i], map.get(a[i]) + 1);
            } else {
                map.put(a[i], 1);
            }
        }

        for(int i = 0; i<a.length; i++) {
            int checkFor = Math.abs(a[i] - k);
            int count = map.getOrDefault(checkFor, 0);
            if(count>0) {
                pair += count;
                if(a[i] == checkFor) {
                    pair--;
                }
            }
        }
        return pair/2;
    }

    public static void main(String[] args) {
        
        int[] a = {1, 2, 3, 4, 3};
        System.out.println(numberOfWays(a, 6));
    }
}
