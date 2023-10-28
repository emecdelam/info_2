package complexity;

import java.util.Arrays;

public class KnapsackBruteForce {

    static class Item {
        int value;
        int weight;

        Item(int value, int weight) {
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

        if (items.length == 0) {
            return 0;
        }

        if (items.length == 1) {
            if (items[0].weight <= capacity) {
                return items[0].value;
            } else {
                return 0;
            }
        }

        Item[] items_without_first = Arrays.copyOfRange(items, 1, items.length);
        
        int valueForItemNotTaken = knapsack(items_without_first, capacity);
        if (capacity - items[0].weight < 0) {return valueForItemNotTaken;}
        int valueForItemTaken = knapsack(items_without_first, capacity - items[0].weight) + items[0].value;
        
        return Math.max(valueForItemTaken, valueForItemNotTaken);

    }
    
}