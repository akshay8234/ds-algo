package problemsolving;

import java.util.ArrayList;
import java.util.List;

public class Matrix {

    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<>();
        if (matrix.length == 0) {
            return list;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        int left = 0;
        int top = 0;
        int right = columns - 1;
        int bottom = rows - 1;
        int total = rows * columns;

        while (list.size() < total) {
            for (int j = left; j <= right && list.size() < total; j++) {
                list.add(matrix[top][j]);
            }
            top++;
            for (int i = top; i <= bottom && list.size() < total; i++) {
                list.add(matrix[i][right]);
            }
            right--;
            for (int j = right; j >= left && list.size() < total; j--) {
                list.add(matrix[bottom][j]);
            }
            bottom--;
            for (int i = bottom; i >= top && list.size() < total; i--) {
                list.add(matrix[i][left]);
            }
            left++;
        }
        return list;
    }

    public static void main(String[] args) {

        int[][] a = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}};
        spiralOrder(a);
    }
}
