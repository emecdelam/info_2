package parallelization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
public class FindInMatrix {
    // You are allowed to add methods or class members, but do not change the signature
    // of the existing methods and class members.

    public static class Result {
        public int row;
        public List<Integer> columns;
        public Result(int row,List<Integer> columns){
            this.row = row;
            this.columns = columns;
        }
        public int numberOfOccurence(){
            return this.columns.size();
        }
    }

    /**
     * This method finds the row that contains the most number of occurrences of a
     * certain value and returns the row index of that row and the column indexes
     * (in increasing order) where the value occurs in that row.
     * The matrix is represented by a two-dimensional array.
     *
     * Example: if the method is called with the value "3" and the following matrix
     *     (1   3  2  -4)          // <- there is a "3" in column 1
     *     (-3  4  5  -2)
     *     (3   0  3   2)          // <- there is a "3" in column 0 and column 2
     * then the result of the search is:
     *      row=2 , columns=[0,2]
     * because row 2 contains the most number of occurrences of "3" (in columns 0 and 2).
     *
     * Your solution MUST use a thread pool to accelerate the operation.
     *
     * @param matrix a rectangular matrix
     * @param value the value to find
     * @param poolSize the thread pool size
     * @return the result of the search
     *
     * You can assume that there is exactly one row in the matrix with the
     * most number of occurrences of the value.
     * Catch exceptions and ignore them.
     */

    public static Result findValue(int[][] matrix, int value, int poolSize) {
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        List<Future<Result>> futureresults = new ArrayList<>();

        for (int index=0;index<matrix.length;index++){
            final int currentRow = index;
            Callable<Result> task = () -> {
                List<Integer> list = new ArrayList<>();
                for (int j= 0; j<matrix[currentRow].length;j++){
                    if (matrix[currentRow][j] == value){
                        list.add(j);
                    }
                }
                return new Result(currentRow,list);
            };
            Future<Result> future = executorService.submit(task);
            futureresults.add(future);
        }
        executorService.shutdown();
        Result maxres = null;
        int max = 0;
        for (Future<Result> future : futureresults){
            try {
                Result res = future.get();
                if (res.numberOfOccurence() > max){
                    max = res.numberOfOccurence();
                    maxres = res;

                }
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return maxres;
    }
}