package basics;
public class Matrix {

    /**
     * Create a matrix from a String.
     *
     * The string if formatted as follow:
     *  - Each row of the matrix is separated by a newline
     *    character \n
     *  - Each element of the rows are separated by a space
     *  For example, the String "0 1\n2 3" represent the
     *  matrix:
     *      [0 1]
     *      [2 3]
     *
     *  @param s The input String
     *  @return The matrix represented by the String
     */
    public static int[][] buildFrom(String s) {
        String[] rows = s.split("\n");
        String[] lines = rows[0].split(" ");
        int[][] matrix = new int[rows.length][lines.length];

        for (int i = 0; i < rows.length; i++){
            String[] linestring = rows[i].split(" ");
            int[] line = new int[linestring.length];
            for (int j = 0; j < line.length; j++){
                line[j] = Integer.parseInt(linestring[j]);
            }
            matrix[i] = line;
        }

        return matrix;
    }
    /**
     * Compute the sum of the element in a matrix
     *
     * @param matrix The input matrix
     * @return The sum of the element in matrix
     */
    public static int sum(int[][] matrix) {
        int sum = 0;
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                sum += anInt;
            }
        }
        return sum;
    }
    /**
     * Compute the transpose of a matrix
     *
     * @param matrix The input rectangular matrix
     * @return A new matrix that is the transpose of matrix
     */
    public static int[][] transpose(int[][] matrix) {
        int[][] transposed = new int[matrix[0].length][matrix.length];
        for (int i = 0; i < matrix.length; i++){
            for (int j = 0; j < matrix[i].length; j++){
                transposed[j][i] = matrix[i][j];
            }
        }
        return transposed;
    }
    /**
     * Compute the product of two matrix
     *
     * @param matrix1 A n x m matrix
     * @param matrix2 A m x k matrix
     * @return The n x k matrix product of matrix1 and matrix2
     */
    public static int[][] product(int[][] matrix1, int[][] matrix2) {
        int[][] res = new int[matrix1.length][matrix2[0].length];
        for (int n = 0; n < matrix1.length; n++){
            for (int k = 0; k < matrix2[0].length; k++){
                int sum = 0;
                for (int m = 0; m < matrix2.length; m++) {
                    sum += matrix1[n][m] * matrix2[m][k];
                }
                res[n][k] = sum;
            }
        }
        return res;
    }
}