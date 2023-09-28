package oop;

import java.util.Comparator;
import java.util.List;


/**
 * Question:
 *
 * You are asked to compute the median of a list of pair (see the TODO below)
 *
 * Once it is done, copy-paste the complete class in Inginious also with the imports
 *
 *
 * Feel free to add methods or fields in the class but do not modify
 * the signature and behavior of existing code
 *
 */
public class MedianOfPairs {

    /**
     * Return the median (=middle) element
     * @param list, a list containing an odd number of distinct pairs
     * @return the pair of the list such that
     *      list.size/2 elements are smaller, and list.size/2 elements are greater
     *      than the returned element using the increasing lexicographic ordering
     * Example: for List((4,1),(3,8),(4,3),(9,1),(3,6)), the median is (4,1)
     *
     * Warning: your algorithm should execute in a time complexity strictly better than O(n^2)
     * where n is the size of the list
     *
     */

    public static Pair median(List<Pair> list) {
        list.sort(new PairComparator());
        int middleIndex = list.size() / 2;
        return list.get(middleIndex);
    }

    public static class PairComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair a, Pair b) {
            if (a.first > b.first) {
                return 1;
            } else if (a.first < b.first) {
                return -1;
            } else {
                return Integer.compare(a.second, b.second);
            }
        }
    }

    public static class Pair {
        public int first;
        public int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
}