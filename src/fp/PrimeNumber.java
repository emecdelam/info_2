package fp;

import java.util.Iterator;
import java.util.function.IntSupplier;
import java.util.stream.IntStream;

/**
 *
 */
public class PrimeNumber {

    /**
     * Check that number is prime (can be divided by 1 and himself)
     *
     * @param number a non negative number
     * @return true if number is prime, false otherwise
     */
    public static boolean isPrime(int number) {
        for (int i = 2; i <= Math.sqrt(number); i += 1) {
            if (number % i == 0) {
                return false;
            }
        }
        return number != 0;
    }

    /**
     * Generates an infinite stream of consecutive integers starting from a given value
     * @param from the start of the stream
     * @return an infinite stream from, from+1, from+2, ...
     */
    public static IntStream streamFrom(int from) {
         return IntStream.iterate(from, n -> n+1);
    }

    public static IntStream primeStreamFrom(int from) {
        return streamFrom(from).filter(PrimeNumber::isPrime);
    }

    /**
     * Generate an infinite stream of prime gaps (difference between two successive prime numbers)
     * computed on the stream of prime numbers
     * starting at the first prime number larger or equal to from.
     * Example: from = 5 (5, 7, 11, 13, 17, 19, ...) , the stream of prime gaps is thus 2, 4, 2, 4, 2, ...
     *
     * @param from
     * @return an infinite stream of prime gaps
     */
    public static IntStream primeGapStreamFrom(int from) {
        //Converting to iterator because then we have next() method.
        Iterator<Integer> iterator = primeStreamFrom(from).iterator();
        class GapSupplier implements IntSupplier {
            int next = iterator.next();
            @Override
            public int getAsInt() {
                int current = next;
                next = iterator.next();
                return next - current;
            }
        }
        GapSupplier supplier = new GapSupplier();
        return IntStream.generate(supplier);



    }

}
