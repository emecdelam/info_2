package basics;
import java.util.BitSet;

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
        int n = (int) Math.pow(matrix.length,2);
        BitSet set = new BitSet(n);
        set.flip(1,n+1);
        int first_sum = 0;
        int second_sum = 0;
        int first_diag_sum = 0;
        int second_diag_sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            second_sum += matrix[i][0];
            first_sum += matrix[0][i];
        }
        for (int i = 0; i < matrix.length; i++) {
            first_diag_sum += matrix[i][i];
            second_diag_sum += matrix[i][matrix.length-1-i];
            int column_sum = 0;
            int row_sum = 0;
            for (int j = 0; j < matrix.length; j++) {
                column_sum+=matrix[j][i];
                row_sum+=matrix[i][j];
                if (set.get(matrix[j][i])){
                    set.flip(matrix[j][i]);
                }
            }
            if (column_sum != second_sum){return false;}
            if (row_sum != first_sum){return false;}
        }
        if (!(set.cardinality() == 0)){return false;}
        return first_diag_sum == second_diag_sum;
    }
}