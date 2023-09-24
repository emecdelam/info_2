package basics;
import java.util.LinkedList;
@SuppressWarnings("unused")
public class MagicSquare {
    /*
      A magic square is an (n x n) matrix such that:

      - all the positive numbers 1,2, ..., n*n are present (thus each number appears exactly once)
      - the sums of the numbers in each row, each column and both main diagonals are the same

        For instance a 3 x 3 magic square is the following

        2 7 6
        9 5 1
        4 3 8

        You have to implement the method that verifies if a matrix is a valid magic square
     */

    /**
     *
     * @param matrix a square matrix of size n x n
     * @return true if matrix is a n x n magic square, false otherwise
     */



    public static boolean isMagicSquare(int [][] matrix) {
        return containsEveryInt(matrix) && sumRows(matrix) && sumColumns(matrix) && sumDiagonals(matrix);
    }
    public static int sum(int[] arr) {int total = 0;for (int element : arr) {total += element;}return total;}
    public static boolean sumRows(int[][] matrix){
        if (matrix.length == 0) {
            return true;
        }
        int first_sum = sum(matrix[0]);
        for (int i = 1; i < matrix.length; i++) {
            if (sum(matrix[i]) != first_sum) {
                return false;
            }
        }
        return true;
    }
    public static boolean sumColumns(int[][] matrix){
        if (matrix.length == 0) {
            return true;
        }
        int first_sum = 0;
        for (int[] ints : matrix) {
            first_sum += ints[0];
        }
        for (int i=0;i<matrix[0].length;i++){
            int column_sum = 0;
            for (int[] row : matrix) {
                column_sum+=row[i];
            }
            if (column_sum != first_sum){
                return false;
            }
        }
        return true;
    }
    public static boolean sumDiagonals(int[][] matrix){
        if (matrix.length == 0 || matrix.length!=matrix[0].length) {
            return true;
        }
        int first_sum = 0;
        for (int i =0;i< matrix.length;i++){
            first_sum += matrix[i][i];
        }
        int second_sum = 0;
        for (int i = 0;i< matrix.length;i++){
            second_sum += matrix[i][matrix.length-1-i];
        }
        return first_sum == second_sum;
    }
    public static boolean containsEveryInt(int[][] matrix){
        LinkedList<Integer> linked_list = new LinkedList<>();
        for (int i = 1; i <= Math.pow(matrix.length,2); i++) {
            linked_list.add(i);
        }
        for (int[] row:matrix){
            for (int element:row){
                if (!linked_list.contains(element)){
                    return false;
                }
                linked_list.remove((Integer) element);
            }
        }
        return true;
    }
}