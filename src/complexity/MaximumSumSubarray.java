package complexity;

public class MaximumSumSubarray {

    /**
     * Class representing a sub-array in an array. It is defined by the start
     * and end position (both inclusive) of the sub-array in the array
     */
    public static class ArrayIndex {
        public int start;
        public int end;

        public ArrayIndex(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public boolean equals(Object other) {
            if (other instanceof ArrayIndex o) {
                return o.start == this.start && o.end == this.end;
            }
            return false;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", this.start, this.end);
        }
    }

    /**
     * Finds the contiguous sub-array for which the sum of its elements is maximal.
     * If multiple sub-arrays have the same maximal sum, returns the one that starts at
     * the lowest index.
     *
     * For example, in the sub-array [1 ,1 , 3, -10, 3, 4, -5, -3, 2, 1], the methods returns
     * (4, 5).
     *
     * @param array A non-empty array filled with non-zero integers (which might be negative)
     * @return The position of the array for which the sum of its element is maximal (if there
     *          is a tie, the one that starts the earliest is returned)
     */
    // Kadane's algorithm
    public static ArrayIndex maximumSumSubarray(int[] array) {
        int maxEnding = array[0];
        int max = array[0];
        int start = 0;
        int tempStart = 0;
        int end = 0;
        for (int i=1; i< array.length;i++){
            // Start new sequnced array
            if (array[i] > maxEnding + array[i]){
                maxEnding = array[i];
                tempStart = i;
            } else{
                // Stop the sequence
                maxEnding = maxEnding + array[i];
            }
            if (maxEnding > max){
                max = maxEnding;
                start = tempStart;
                end = i;
            }
        }
        return new ArrayIndex(start,end);
    }
}