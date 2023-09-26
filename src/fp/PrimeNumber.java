package fp;

import java.util.*;
import java.util.function.BiFunction;
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
        IntStream primeStream = primeStreamFrom(from);
        IntStream shiftedStream = primeStream.skip(1);
        return zip(primeStreamFrom(from).boxed(),shiftedStream.boxed(), (a, b) -> b - a ).mapToInt(Integer::intValue);
    }

    public static <A, B, C> Stream<C> zip(Stream<? extends A> a,
                                          Stream<? extends B> b,
                                          BiFunction<? super A, ? super B, ? extends C> zipper) {
        Objects.requireNonNull(zipper);
        Spliterator<? extends A> aSpliterator = Objects.requireNonNull(a).spliterator();
        Spliterator<? extends B> bSpliterator = Objects.requireNonNull(b).spliterator();

        // Zipping looses DISTINCT and SORTED characteristics
        int characteristics = aSpliterator.characteristics() & bSpliterator.characteristics() &
                ~(Spliterator.DISTINCT | Spliterator.SORTED);

        long zipSize = ((characteristics & Spliterator.SIZED) != 0)
                ? Math.min(aSpliterator.getExactSizeIfKnown(), bSpliterator.getExactSizeIfKnown())
                : -1;

        Iterator<? extends A> aIterator = Spliterators.iterator(aSpliterator);
        Iterator<? extends B> bIterator = Spliterators.iterator(bSpliterator);
        Iterator<C> cIterator = new Iterator<>() {
            @Override
            public boolean hasNext() {
                return aIterator.hasNext() && bIterator.hasNext();
            }

            @Override
            public C next() {
                return zipper.apply(aIterator.next(), bIterator.next());
            }
        };

        Spliterator<C> split = Spliterators.spliterator(cIterator, zipSize, characteristics);
        return (a.isParallel() || b.isParallel())
                ? StreamSupport.stream(split, true)
                : StreamSupport.stream(split, false);
    }
}