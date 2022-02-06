package problemsolving;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class RecursionBased {

    static int[] fib = new int[45];

    public static int calculateFactorial(int n) {
        if (n <= 1) {
            return 1;
        }
        return n * calculateFactorial(n - 1);
    }

    public static int fibonacci(int n) {
        if (n <= 1) {
            return 1;
        }
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static int fibonacciWithMemoization(int n) {
        if (n <= 1) {
            return n;
        }
        if (fib[n] != 0) {
            return fib[n];
        }
        fib[n] = fibonacci(n - 1) + fibonacci(n - 2);
        return fib[n];
    }

    public static int fibonacciWithIterative(int n) {
        int[] fib = new int[n + 1];
        fib[1] = 1;
        for (int i = 2; i < fib.length; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
        }
        return fib[n];
    }

    public static int powerOfNRecursive(int x, int n) {
        if (n == 0) {
            return 1;
        }
        return x * powerOfNRecursive(x, n - 1);
    }

    public static int powerOfNRecursiveImproved(int x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n % 2 == 0) {
            int y = powerOfNRecursiveImproved(x, n / 2);
            return y * y;
        } else {
            return x * powerOfNRecursiveImproved(x, n / 2);
        }
    }

    static long[][] a = new long[19][19];

    public static long gridTravelerMemo(int m, int n) {
        if (m == 1 && n == 1) {
            return 1;
        }
        if (m == 0 || n == 0) {
            return 0;
        }
        if (a[m][n] != 0) {
            return a[m][n];
        }
        a[m][n] = gridTravelerMemo(m, n - 1) + gridTravelerMemo(m - 1, n) + gridTravelerMemo(m - 1, n - 1);
        return a[m][n];
    }

    public static long gridTravelerTab(int m, int n) {
        int[][] gridTraveler = new int[m + 1][n + 1];
        gridTraveler[1][1] = 1;
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                int current = gridTraveler[i][j];
                if ((i + 1) <= m) {
                    gridTraveler[i + 1][j] += current;
                }
                if ((j + 1) <= n) {
                    gridTraveler[i][j + 1] += current;
                }
                if ((i + 1) <= m && (j + 1) <= n) {
                    gridTraveler[i + 1][j + 1] += current;
                }
            }
        }
        return gridTraveler[m][n];
    }

    public static boolean canSum(int targetSum, int[] a) {
        if (targetSum == 0) {
            return true;
        }
        if (targetSum < 0) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            int remainder = targetSum - a[i];
            if (canSum(remainder, a)) {
                return true;
            }
        }
        return false;
    }

    public static boolean canSumMemo(int targetSum, int[] a, Map<Integer, Boolean> memoMap) {
        if (memoMap.containsKey(targetSum)) {
            return memoMap.get(targetSum);
        }
        if (targetSum == 0) {
            return true;
        }
        if (targetSum < 0) {
            return false;
        }
        for (int j : a) {
            int remainder = targetSum - j;
            if (canSumMemo(remainder, a, memoMap)) {
                memoMap.put(remainder, true);
                return true;
            }
        }
        memoMap.put(targetSum, false);
        return false;
    }

    public static boolean canSumTab(int targetSum, int[] a) {
        boolean[] tab = new boolean[targetSum + 1];
        tab[0] = true;

        for (int i = 0; i <= targetSum; i++) {
            if (tab[i]) {
                for (int num : a) {
                    if (i + num <= targetSum) {
                        tab[i + num] = true;
                    }
                }
            }
        }
        return tab[targetSum];
    }

    public static List<Integer> howSum(int targetSum, int[] a) {
        if (targetSum == 0) {
            return new ArrayList<>();
        }
        if (targetSum < 0) {
            return null;
        }
        for (int i : a) {
            int remainder = targetSum - i;
            List<Integer> remainderResult = howSum(remainder, a);
            if (remainderResult != null) {
                remainderResult.add(i);
                return remainderResult;
            }
        }
        return null;
    }

    public static List<Integer> howSumMemo(int targetSum, int[] a, Map<Integer, List<Integer>> memoMap) {
        if (memoMap.containsKey(targetSum)) {
            return memoMap.get(targetSum);
        }
        if (targetSum == 0) {
            return new ArrayList<>();
        }
        if (targetSum < 0) {
            return null;
        }
        for (int i : a) {
            int remainder = targetSum - i;
            List<Integer> remainderResult = howSumMemo(remainder, a, memoMap);
            if (remainderResult != null) {
                memoMap.put(i, remainderResult);
                return remainderResult;
            }
        }
        memoMap.put(targetSum, null);
        return null;
    }

    public static int binary_search_rec(int a[], int key, int left, int right) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (a[mid] > key) {
            return binary_search_rec(a, key, left, mid - 1);
        } else if (a[mid] < key) {
            return binary_search_rec(a, key, mid + 1, right);
        } else {
            return mid;
        }
    }

    public static List<Integer> howSumTab(int targetSum, int[] a) {
        List<Integer>[] table = new ArrayList[targetSum + 1];
        table[0] = new ArrayList<>();
        for (int i = 0; i <= targetSum; i++) {
            if (table[i] != null) {
                for (int j = 0; j < a.length; j++) {
                    if (i + a[j] <= targetSum) {
                        List<Integer> temp = new ArrayList<>(table[i]);
                        temp.add(a[j]);
                        table[i + a[j]] = temp;
                    }
                }
            }
        }
        return table[targetSum];
    }

    public static List<Integer> bestSum(int targetSum, int[] a) {
        if (targetSum == 0) {
            return new ArrayList<>();
        }
        if (targetSum < 0) {
            return null;
        }
        List<Integer> shortestCombination = null;
        for (int i : a) {
            int remainder = targetSum - i;
            List<Integer> remainderResult = bestSum(remainder, a);
            if (remainderResult != null) {
                remainderResult.add(i);
                if (shortestCombination == null || remainderResult.size() < shortestCombination.size()) {
                    shortestCombination = new ArrayList<>(remainderResult);
                }
            }
        }
        return shortestCombination;
    }

    public static List<Integer> bestSumMemo(int targetSum, int[] a, Map<Integer, List<Integer>> memoMap) {
        if (memoMap.containsKey(targetSum)) {
            return memoMap.get(targetSum);
        }
        if (targetSum == 0) {
            return new ArrayList<>();
        }
        if (targetSum < 0) {
            return null;
        }
        List<Integer> shortestCombination = null;
        for (int i : a) {
            int remainder = targetSum - i;
            List<Integer> remainderResult = bestSumMemo(remainder, a, memoMap);
            if (remainderResult != null) {
                remainderResult = new ArrayList<>(remainderResult);
                remainderResult.add(i);
                if (shortestCombination == null || remainderResult.size() < shortestCombination.size()) {
                    shortestCombination = new ArrayList<>(remainderResult);
                }
            }
        }
        memoMap.put(targetSum, shortestCombination);
        return shortestCombination;
    }

    public static List<Integer> bestSumTab(int targetSum, int[] a) {
        List<Integer>[] table = new ArrayList[targetSum + 1];
        table[0] = new ArrayList<>();

        for (int i = 0; i <= targetSum; i++) {
            if (table[i] != null) {
                for (int j = 0; j < a.length; j++) {
                    if ((i + a[j]) <= targetSum) {
                        List<Integer> temp = new ArrayList<>(table[i]);
                        temp.add(a[j]);
                        if (table[i + a[j]] == null || temp.size() < table[i + a[j]].size()) {
                            table[i + a[j]] = temp;
                        }
                    }
                }
            }
        }
        return table[targetSum];
    }

    public static boolean canConstruct(String target, String[] wordBank) {
        if (target.isEmpty()) {
            return true;
        }
        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                String suffix = target.substring(word.length());
                if (canConstruct(suffix, wordBank)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean canConstructMemo(String target, String[] wordBank, Map<String, Boolean> memoMap) {
        if (memoMap.containsKey(target)) {
            return memoMap.get(target);
        }
        if (target.isEmpty()) {
            return true;
        }
        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                String suffix = target.substring(word.length());
                if (canConstructMemo(suffix, wordBank, memoMap)) {
                    memoMap.put(suffix, true);
                    return true;
                }
            }
        }
        memoMap.put(target, false);
        return false;
    }

    public static boolean canConstructTab(String target, String[] wordBank) {
        boolean[] canConstruct = new boolean[target.length() + 1];
        canConstruct[0] = true;

        for (int i = 0; i <= target.length(); i++) {
            if (canConstruct[i]) {
                for (String word : wordBank) {
                    if (target.startsWith(word, i)) {
                        canConstruct[i + word.length()] = true;
                    }
                }
            }
        }
        return canConstruct[target.length()];
    }

    public static int numberOfWaysCanConstruct(String target, String[] wordBank) {
        if (target.isEmpty()) {
            return 1;
        }
        int maxNumberOfWaysCanConstruct = 0;
        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                String suffix = target.substring(word.length());
                maxNumberOfWaysCanConstruct += numberOfWaysCanConstruct(suffix, wordBank);
            }
        }
        return maxNumberOfWaysCanConstruct;
    }

    public static int numberOfWaysCanConstructMemo(String target, String[] wordBank, Map<String, Integer> memoMap) {
        if (memoMap.containsKey(target)) {
            return memoMap.get(target);
        }
        if (target.isEmpty()) {
            return 1;
        }
        int maxNumberOfWaysCanConstruct = 0;
        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                String suffix = target.substring(word.length());
                maxNumberOfWaysCanConstruct += numberOfWaysCanConstructMemo(suffix, wordBank, memoMap);
            }
        }
        memoMap.put(target, maxNumberOfWaysCanConstruct);
        return memoMap.get(target);
    }

    public static int numberOfWaysCanConstructTab(String target, String[] wordBank) {
        int[] table = new int[target.length() + 1];
        table[0] = 1;

        for (int i = 0; i < target.length(); i++) {
            for (String word : wordBank) {
                if (target.startsWith(word, i)) {
                    table[i + word.length()] += table[i];
                }
            }
        }
        return table[target.length()];
    }

    public static List<List<String>> allConstruct(String target, String[] wordBank) {
        if (target.isEmpty()) {
            List<List<String>> list = new ArrayList<>();
            list.add(new ArrayList<>());
            return list;
        }
        List<List<String>> allConstructList = new ArrayList<>();
        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                String suffix = target.substring(word.length());
                List<List<String>> suffixWays = allConstruct(suffix, wordBank);
                List<String> suffixWaysNew;
                for (List<String> ways : suffixWays) {
                    suffixWaysNew = new ArrayList<>(ways);
                    suffixWaysNew.add(0, word);
                    allConstructList.add(suffixWaysNew);
                }
            }
        }
        return allConstructList;
    }

    public static List<List<String>> allConstructMemo(String target, String[] wordBank, Map<String, List<List<String>>> memoMap) {
        if (memoMap.containsKey(target)) {
            return memoMap.get(target);
        }
        if (target.isEmpty()) {
            List<List<String>> list = new ArrayList<>();
            list.add(new ArrayList<>());
            return list;
        }
        List<List<String>> allConstructList = new ArrayList<>();
        for (String word : wordBank) {
            if (target.indexOf(word) == 0) {
                String suffix = target.substring(word.length());
                List<List<String>> suffixWays = allConstructMemo(suffix, wordBank, memoMap);
                List<String> suffixWaysNew;
                for (List<String> ways : suffixWays) {
                    suffixWaysNew = new ArrayList<>(ways);
                    suffixWaysNew.add(0, word);
                    allConstructList.add(suffixWaysNew);
                }
            }
        }
        memoMap.put(target, allConstructList);
        return allConstructList;
    }

    public static List<List<String>> allConstructTab(String target, String[] wordBank) {
        List<List<String>>[] table = new ArrayList[target.length() + 1];
        for (int i = 0; i <= target.length(); i++) {
            table[i] = new ArrayList<>();
        }
        table[0] = new ArrayList<>();
        table[0].add(new ArrayList<>());

        for (int i = 0; i <= target.length(); i++) {
            if (!table[i].isEmpty()) {
                for (String sub : wordBank) {
                    // refactor it
                    if (i + sub.length() <= target.length()) {
                        if (target.startsWith(sub, i)) {
                            for (List temp : table[i]) {
                                temp = new ArrayList<>(temp);
                                temp.add(sub);
                                table[i + sub.length()].add(temp);
                            }
                        }
                    }
                }
            }
        }
        return table[target.length()];
    }

    public static void sortStack(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        int temp = stack.pop();
        sortStack(stack);
        insert(stack, temp);
    }

    public static void insert(Stack<Integer> stack, int x) {
        if (stack.isEmpty() || stack.peek() < x) {
            stack.push(x);
            return;
        }
        int temp = stack.pop();
        insert(stack, x);
        stack.push(temp);
    }

    public static void deleteMiddleFromStack(Stack<Integer> stack, int mid) {
        if (stack.isEmpty() || stack.size() == mid) {
            stack.pop();
            return;
        }
        int temp = stack.pop();
        deleteMiddleFromStack(stack, mid);
        stack.push(temp);
    }

    public static void reverseStack(Stack<Integer> stack) {
        if (stack.size() == 1) {
            return;
        }
        int temp = stack.pop();
        reverseStack(stack);
        insertElement(stack, temp);
    }

    public static void insertElement(Stack<Integer> stack, int x) {
        if (stack.isEmpty()) {
            stack.push(x);
            return;
        }
        int temp = stack.pop();
        insertElement(stack, x);
        stack.push(temp);
    }

    public static int towerOfHanoi(String source, String helper, String destination, int n) {
        if (n == 0) {
            return 1;
        }
        int steps1 = towerOfHanoi(source, destination, helper, n - 1);
        System.out.println("from " + source + " to " + helper);
        int steps2 = towerOfHanoi(destination, helper, source, n - 1);
        return steps1 + steps2;
    }

    public static List<List<String>> subset(String input, String output, int index, List<List<String>> list) {
        int n = input.length();
        if (n == index) {
            list.add(Arrays.asList(output));
        } else {
            subset(input, output, index + 1, list);
            subset(input, output + input.charAt(index), index + 1, list);
        }
        return list;
    }

    public static List<List<String>> subsetWithSpaces(String input, String output, int index, List<List<String>> list) {
        int n = input.length();
        if (n == index) {
            list.add(Arrays.asList(output));
        } else {
            subsetWithSpaces(input, output, index + 1, list);
            subsetWithSpaces(input, output + " " + input.charAt(index), index + 1, list);
        }
        return list;
    }

    public static List<String> permutationWithSpaces(String input) {
        List<String> list = new ArrayList<>();
        char[] chars = input.toCharArray();
        permutationWithSpacesUtil(chars, 0, list);
        return list;
    }

    public static void permutationWithSpacesUtil(char[] input, int index, List<String> list) {
        if (input.length == index) {
            list.add(String.valueOf(input));
            return;
        }
        if (Character.isLetter(input[index])) {
            input[index] = Character.toLowerCase(input[index]);
            permutationWithSpacesUtil(input, index + 1, list);
            input[index] = Character.toUpperCase(input[index]);
        }
        permutationWithSpacesUtil(input, index + 1, list);
    }

    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        generateParenthesisUtil(0, 0, n, "", list);
        return list;
    }

    public static void generateParenthesisUtil(int open, int close, int max, String parenthesis, List<String> list) {
        if (parenthesis.length() == max * 2) {
            list.add(parenthesis);
            return;
        }
        if (open < max) {
            generateParenthesisUtil(open + 1, close, max, parenthesis + "(", list);
        }
        if (close < open) {
            generateParenthesisUtil(open, close + 1, max, parenthesis + ")", list);
        }
    }

    public static void josephusProblem(int n, int k) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(i, i + 1);
        }
        k = k - 1;
        josephusProblemUtil(0, k, list);
    }

    public static void josephusProblemUtil(int index, int k, List<Integer> list) {
        if (list.size() == 1) {
            System.out.println("last standing person is " + list.get(0));
            return;
        }
        // find the person
        index = (index + k) % list.size();
        // kill him
        list.remove(index);
        // repeat the same process
        josephusProblemUtil(index, k, list);
    }

    public static void main(String[] args) {

        long time = System.currentTimeMillis();
//        System.out.println(calculateFactorial(17));
//        System.out.println(binary_search_rec(new int[]{0, 1, 2, 3, 4, 5, 6}, 1, 0, 6));
//        System.out.println(fibonacci(44));
//        System.out.println(fibonacciWithMemoization(44));
//        System.out.println(fibonacciWithIterative(5));
//        System.out.println(System.currentTimeMillis() - time);
//        System.out.println(powerOfNRecursive(2, 16));
//        System.out.println(powerOfNRecursiveImproved(2, 16));
//        System.out.println(System.currentTimeMillis() - time);
//        System.out.println(gridTravelerMemo(18, 18));
//        System.out.println(gridTravelerTab(3, 3));
//        System.out.println(canSumMemo(300, new int[]{7, 14}, new HashMap<>()));
//        System.out.println(canSumTab(300, new int[]{10, 10}));
//        System.out.println(howSum(8, new int[]{2, 3, 5}));
//        System.out.println(howSumMemo(300, new int[]{7, 14}, new HashMap<>()));
//        System.out.println(howSumTab(8, new int[]{2, 3, 5}));
//        System.out.println(bestSum(100, new int[]{1, 2, 5, 25}));
//        System.out.println(bestSumMemo(100, new int[]{1, 2, 5, 25}, new HashMap<>()));
//        System.out.println(bestSumTab(100, new int[]{1, 2, 5, 25}));
//        System.out.println(canConstruct("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[]{"ee", "eeee", "eee", "eeeee", "eeeeee", "eeeeeee", "eeeeeeeee"}));
//        System.out.println(canConstructMemo("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[]{"ee", "eeee", "eee", "eeeee", "eeeeee", "eeeeeee", "eeeeeeeee"}, new HashMap<>()));
//        System.out.println(canConstructTab("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd"}));
//        System.out.println(numberOfWaysCanConstruct("purple", new String[]{"purp", "p", "ur", "le", "purpl"}));
//        System.out.println(numberOfWaysCanConstructMemo("enterapotentpot", new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}, new HashMap<>()));
//        System.out.println(numberOfWaysCanConstructTab("enterapotentpot", new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}));
//        System.out.println(allConstruct("enterapotentpot", new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}));
//        System.out.println(allConstructMemo("enterapotentpot", new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}, new HashMap<>()));
//        System.out.println(allConstructTab("enterapotentpot", new String[]{"a", "p", "ent", "enter", "ot", "o", "t"}));


//        Stack<Integer> stack = new Stack<>();
//        stack.push(3);
//        stack.push(1);
//        stack.push(5);
//        stack.push(0);
//        stack.push(1);
//        sortStack(stack);
//        System.out.println(stack);
//        deleteMiddleFromStack(stack, 2);
//        System.out.println(stack);
//        reverseStack(stack);
//        System.out.println(stack);
//        int steps = towerOfHanoi("A", "B", "C", 3);
//        System.out.println(steps - 1);
//        System.out.println(subset("abc", "", 0, new ArrayList<>()));
//        System.out.println(subsetWithSpaces("abc", "", 0, new ArrayList<>()));
//        System.out.println(permutationWithSpaces("a1b2c3"));
//        System.out.println(generateParenthesis(4));
        josephusProblem(40, 7);
//        System.out.println(System.currentTimeMillis() - time);
    }
}
