package fp;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.IntSupplier;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 *
 */
@SuppressWarnings("unused")
public class PrimeNumber {
    public static BitSet bits; //You should work on this BitSet

    public static LinkedList<Integer> primesUntil(int n){
        bits = new BitSet(n);
        bits.flip(2,n); //2 being the first prime and is at the second place
        for (int i = 2; i * i <= n; i++){
            if(bits.get(i)) {
                for (int j = i * i; j <= n;j += i){
                    bits.clear(j);
                }
            }
        }
        LinkedList<Integer> primes = new LinkedList<>();
        for (int i=0;i<bits.length();i++){
            if (bits.get(i)){
                primes.add(i);
            }
        }
        return primes;
    }
    /**
     * Check that number is prime (can be divided by 1 and himself)
     *
     * @param number a non negative number
     * @return true if number is prime, false otherwise
     */
    public static boolean isPrime(int number) {
        if (number == 0){
            return false;
        } if (number == 1){
            return true;
        }
        LinkedList<Integer> primes = primesUntil(number+1);
        return primes.contains(number);
    }

    /**
     * Generates an infinite stream of consecutive integers starting from a given value
     * @param from the start of the stream
     * @return an infinite stream from, from+1, from+2, ...
     */
    public static IntStream streamFrom(int from) {
        IntUnaryOperator op = a -> a+1;
        return IntStream.iterate(from,op);
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
     * @param from int
     * @return an infinite stream of prime gaps
     */

    public static IntStream primeGapStreamFrom(int from) {
        Iterator<Integer> iterator = primeStreamFrom(from).iterator();
        IntSupplier supplier = new IntSupplier() {
            private int next = iterator.next();
            @Override
            public int getAsInt() {
                int current = next;
                next = iterator.next();
                return next - current;
            }
        };
        return IntStream.generate(supplier);
    }
}