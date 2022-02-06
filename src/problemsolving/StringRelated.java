package problemsolving;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class StringRelated {

    public static String reverseVowels(String s) {
        int start = 0, end = s.length() - 1;
        String check = "aeiou";
        char[] sChar = s.toCharArray();
        while (start < end) {
            if (check.contains(Character.toString(s.charAt(start)).toLowerCase()) && check.contains(Character.toString(s.charAt(end)).toLowerCase())) {
                char temp = s.charAt(start);
                sChar[start] = sChar[end];
                sChar[end] = temp;
                start++;
                end--;
            } else {
                if (!check.contains(Character.toString(s.charAt(start)).toLowerCase(Locale.ROOT))) {
                    start++;
                }
                if (!check.contains(Character.toString(s.charAt(end)).toLowerCase(Locale.ROOT))) {
                    end--;
                }
            }
        }
        return String.valueOf(sChar);
    }

    public static boolean isPalindrome(String s) {
        boolean isPalindrome = true;
        String s1 = s.replaceAll("[^a-zA-Z]", "").toLowerCase();
        int start = 0, end = s1.length() - 1;
        while (start <= end) {
            if (s1.charAt(start) != s1.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return isPalindrome;
    }

    public static List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] sorted = new char[26];
            for (char ch : s.toCharArray()) {
                sorted[ch - 'a']++;
            }
            String sortedString = String.valueOf(sorted);
            map.computeIfAbsent(sortedString, list -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public static boolean isAnagram(String s, String t) {
        int[] alphabet = new int[26];
        for (int i = 0; i < s.length(); i++) alphabet[s.charAt(i) - 'a']++;
        for (int i = 0; i < t.length(); i++) alphabet[t.charAt(i) - 'a']--;
        for (int i : alphabet) if (i != 0) return false;
        return true;
    }

    public static String test(String input, int rotationFactor) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < input.length(); i++) {
            char x = input.charAt(i);
            if (Character.isLowerCase(x)) {
                char ch = (char) ((x + rotationFactor - 97) % 26 + 97);
                sb.append(ch);
            } else if (Character.isUpperCase(x)) {
                char ch = (char) ((x + rotationFactor - 65) % 26 + 65);
                sb.append(ch);
            } else if (Character.isDigit(x)) {
                char ch = (char) ((x + rotationFactor - 48) % 10 + 48);
                sb.append(ch);
            } else {
                sb.append(x);
            }

        }
        return sb.toString();
    }

    public static int getSubstringCount(String s) {
        // Write your code here
        int ans = 0;
        int prev = 0;
        int cur = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - 1)) {
                ans += Math.min(cur, prev);
                prev = cur;
                cur = 1;
            } else {
                cur++;
            }
        }
        return ans + Math.min(prev, cur);
    }

    public static int longestSubstringWithoutRepeatingChars(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int ans = 0;
        int i = 0;
        int j = 0;
        while (true) {
            boolean flag1 = false;
            boolean flag2 = false;
            while (i < s.length()) {
                flag1 = true;
                char ch = s.charAt(i);
                i++;
                map.put(ch, map.getOrDefault(ch, 0) + 1);
                if (map.get(ch) == 2) {
                    break;
                } else {
                    int length = i - j;
                    if (length > ans) {
                        ans = length;
                    }
                }
            }
            while (j < i) {
                flag2 = true;
                char ch = s.charAt(j);
                j++;
                map.put(ch, map.get(ch) - 1);
                if (map.get(ch) == 1) {
                    break;
                }
            }
            if (!flag1 && !flag2) {
                break;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

        //System.out.println(reverseVowels("aA"));
        //System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
//        System.out.println(isAnagram("anagram","nagaram"));
//        System.out.println(groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
//        System.out.print(test("abcdefghijklmNOPQRSTUVWXYZ0123456789", 3));
//        System.out.print(getSubstringCount("00110011"));
        System.out.print(longestSubstringWithoutRepeatingChars("abbacbcdbadbdbbdcb"));
    }
}
