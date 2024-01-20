package fp;

import java.util.Comparator;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * Various exercises with streams.
 **/
public class VariousStreams {

    /**
     * Count the number of strings in a stream that start with the provided character.
     */
    static public long countStringsWithFirstLetter(Stream<String> stream,
                                                   char firstLetter) {

        return stream.filter((i)->i.charAt(0)==firstLetter).count();
    }


    /**
     * Convert a stream of strings either to uppercase or to
     * lowercase, depending on the value of the "uppercase" argument.
     */
    static public Stream<String> changeCase(Stream<String> stream,
                                            boolean uppercase) {
        if (uppercase){
            return stream.map(String::toUpperCase);
        }
        return stream.map(String::toLowerCase);
    }


    /**
     * Compute the sum of all the even numbers inside the provided
     * stream if "isEven" is "true", or the sum of all the odd numbers
     * inside the provided stream if "isEven" is "false".
     */
    static public int getSumOfEvenOrOddNumbers(Stream<Integer> stream,
                                               boolean isEven) {
        if (isEven){
            return stream.filter((i) -> (i % 2 == 0)).mapToInt(Integer::intValue).sum();
        }
        return stream.filter((i) -> (i % 2 != 0)).mapToInt(Integer::intValue).sum();
    }


    /**
     * Remove the duplicates out of a stream of integers.
     *
     * Hint: Check out the JavaDoc of "Stream<T>", there is one method
     * that is especially well suited!
     */
    static public Stream<Integer> removeDuplicates(Stream<Integer> stream) {
        return stream.distinct();
    }


    /**
     * Sort a stream of strings, either in ascending order (if
     * "isAscending" is "true"), or in descending order (if
     * "isAscending" is "false").
     */
    static public Stream<String> sortAscendingOrDescending(Stream<String> stream,
                                                           boolean isAscending) {
        if (isAscending){
            return stream.sorted(String::compareTo);
        }
        return stream.sorted(Comparator.reverseOrder());
    }


    /**
     * Compute the average value of a stream of integer numbers. If
     * the stream is empty, return 0.0.
     *
     * Hint: Check out the "average()" method of specialized class
     * DoubleStream, and of the "orElse()" method of OptionalDouble
     * class.
     */
    static public double computeAverage(Stream<Integer> stream) {
        return stream.mapToDouble(full -> (double) full).average().orElse(0);
    }


    /**
     * Class that wraps a pair of integers corresponding to a minimum
     * value and to a maximum value.
     */
    static public class MinMaxValue {
        private int minValue;
        private int maxValue;

        public MinMaxValue(int minValue,
                           int maxValue) {
            this.minValue = minValue;
            this.maxValue = maxValue;
        }

        public int getMinValue() {
            return minValue;
        }

        public int getMaxValue() {
            return maxValue;
        }
    }


    /**
     * Compute the minimum and the maximum value in a stream of
     * integers.  If the stream is empty, the resulting Optional must
     * have "isPresent()" answer "false".
     *
     * Hint: Use "map()" to create "MinMaxValue", then use "reduce()".
     */
    static public Optional<MinMaxValue> computeMinMaxValue(Stream<Integer> stream) {

        return stream.map((i)->Optional.of(new MinMaxValue(i,i))).reduce(Optional.empty(),
                (a,b)->{
                    return a.map(minMaxValue -> new MinMaxValue(
                            Math.min(minMaxValue.getMinValue(), b.get().getMinValue()),
                            Math.max(minMaxValue.getMaxValue(), b.get().getMaxValue()))).or(() -> b);
                });
    }


    /**
     * Generate the infinite stream of Fibonacci numbers, starting at 2.
     * This sequence corresponds to: [ 2, 3, 5, 8, 13, 21, 34, 55, 89, 144... ]
     *
     * Hint: Use the "generator()" method of "Stream<T>" with a supplier.
     */
    public static Stream<Integer> generateFibonacci() {

        return Stream.generate(new Supplier<Integer>() {
            int prev = 1;
            int cur = 1;
            int old;
            @Override
            public Integer get() {
                old = cur;
                cur = cur+prev;
                prev = old;
                return cur;
            }
        });
    }
}