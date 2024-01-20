package fp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.*;

public class LambdaExpressions {
    /**
     * Return a binary operator that computes the sum of two Integer objects.
     */
    public static Object sumOfIntegers() {
        return (BinaryOperator<Integer>) Integer::sum;
    }

    /**
     * Return a predicate that tests whether a String is empty.
     */
    public static Object isEmptyString() {
        return (Predicate<String>) String::isEmpty;
    }

    /**
     * Return a predicate that tests whether an Integer is an odd number.
     */
    public static Object isOddNumber() {
        return (Predicate<Integer>) integer -> integer%2 == 1;
    }

    /**
     * Return a function that computes the mean of a List of Double objects.
     * If the list is empty, an IllegalArgumentException must be thrown.
     */
    public static Object computeMeanOfListOfDoubles() {
        return (Function<List<Double>,Double>)doubles -> {
            if (doubles.isEmpty()){
                throw new IllegalArgumentException("empty list");
            }
            Double sum = 0.0;
            for (Double d : doubles){
                sum += d;
            }
            return sum / (double) doubles.size();
        };
    }

    /**
     * Remove the even numbers from a list of Integer objects.
     */
    public static void removeEvenNumbers(List<Integer> lst) {
        lst.removeIf(x -> x % 2 == 0);
    }

    /**
     * Return a function that computes the factorial of an Integer.
     * If the number is zero, the factorial equals 1 by convention.
     * If the number is negative, an IllegalArgumentException must be thrown.
     */
    public static Object computeFactorial() {
        return new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) {
                if (integer < 0){
                    throw new IllegalArgumentException("negative fact");
                } else if (integer == 1 || integer == 0) {
                    return 1;
                } else {
                    return apply(integer-1) * integer;
                }
            }
        };
    }

    /**
     * Return a function that converts a list of String objects to lower case.
     */
    public static Object listOfStringsToLowerCase() {

        return (Function<List<String>, List<String>>) strings -> {
            List<String> res = new ArrayList<>();
            for (String s:strings){
                res.add(s.toLowerCase());
            }
            return res;
        };
    }

    /**
     * Return a function that concatenates two String objects.
     */
    public static Object concatenateStrings() {
        return (BiFunction<String,String,String>) (s, s2) -> s+s2;
    }

    public static class MinMaxResult {
        private int minValue;
        private int maxValue;

        MinMaxResult(int minValue,
                     int maxValue) {
            this.minValue = minValue;
            this.maxValue = maxValue;
        }

        int getMinValue() {
            return minValue;
        }

        int getMaxValue() {
            return maxValue;
        }
    }

    /**
     * Return a function that computes the minimum and maximum values in a list.
     * The content of the Optional must be present if and only if the list is non-empty.
     */
    public static Function<List<Integer>, Optional<MinMaxResult>> computeMinMax() {
        return integers -> {
            if (integers.isEmpty()){
                return Optional.empty();
            } else {
                int min = integers.get(0);
                int max = integers.get(0);
                for (Integer i : integers){
                    if (i < min){min = i;}
                    if (i > max){max = i;}
                }
                return Optional.of(new MinMaxResult(min,max));
            }
        };
    }

    /**
     * Return a function that, given a String object and a character, counts
     * the number of occurrences of the character inside the string.
     */
    public static Object countInstancesOfLetter() {
        return (BiFunction<String, Character, Integer>) (s, character) -> {
            int count = 0;
            for ( Character c : s.toCharArray()){
                if (c == character){
                    count++;
                }
            }
            return count;
        };
    }
}