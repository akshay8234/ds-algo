package problemsolving;

import java.util.ArrayList;
import java.util.List;

public class NumbersRelated {

    static int ans = 1;

    public static boolean isPowerOfThree(int n) {
        if (ans == n) {
            return true;
        }
        if (ans > n) {
            return false;
        }
        ans *= 3;
        return isPowerOfThree(n);
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> list1 = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            list1.add(0, 1);
            for (int j = 1; j < list1.size() - 1; j++) {
                list1.set(j, list1.get(j) + list1.get(j + 1));
            }
            list.add(new ArrayList<>(list1));
        }
        return list;
    }

    public static void main(String[] args) {

        //System.out.println(isPowerOfThree(243));
        //System.out.println(generate(3));
    }
}
