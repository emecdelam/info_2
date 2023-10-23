package complexity;

import java.util.Optional;

public class KnapsackBruteForce {
    public static class Item {
        int value;
        int weight;

        public Item(int value, int weight) {
            this.value = value;
            this.weight = weight;
        }
    }

    /**
     * Returns the maximum value that can be put in a knapsack with the given capacity.
     * Each item can only be selected once. If you pack an item it consumes its weight in the capacity
     * Your algorithm should implement a brute-force appraoch with a time comlexity
     * of O(2^n) where n is the number of items.
     *
     * @param items
     * @param capacity
     * @param i
     * @return
     */
    public static int knapsack(Item[] items, int capacity) {
        int max = 0;
        for (Item item1 : items) {
            for (Item item2 : items) {
                if (item2 != item1) {
                    if (item2.weight + item1.weight <= capacity) {
                        if (item2.value + item1.value > max) {
                            max = item2.value + item1.value;
                        }
                    }
                } else {
                    if (item2.weight <= capacity){
                        if (item2.value > max){
                            max = item2.value;
                        }
                    }
                }
            }
        }
        return max;
    }
}