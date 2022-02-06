package sliding.window;

import java.util.HashMap;
import java.util.Map;

public class ShortestSubStringContainingAllDistinctCharOfGivenString {

    public static void main(String[] args) {

        String s = "aabcbcdbca";
        String distinctChar = "abcd";
        Map<Character, Integer> distinctCharMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (distinctCharMap.containsKey(s.charAt(i))) {
                distinctCharMap.put(s.charAt(i), distinctCharMap.get(s.charAt(i)) + 1);
            } else {
                distinctCharMap.put(s.charAt(i), 1);
            }
        }

        int start, end = 0;
        int size = distinctCharMap.size();
        for (int i = 0; i < s.length(); i++) {
            if(distinctCharMap.containsKey(s.charAt(i))) {
                distinctCharMap.put(s.charAt(i), distinctCharMap.get(s.charAt(i)) - 1);
                if(distinctCharMap.get(s.charAt(i)) == 0) {
                    size--;
                }
            }
        }
    }
}
