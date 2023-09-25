package complexity;
import java.util.Arrays;
public class Anagram {

    /**
     * Count the number of occurrences of each letter of the alphabet
     * (from 'A' to 'Z') in the given string. The function must return
     * an array containing 26 elements: The item with index 0 contains
     * the number of 'A' in the string, the item with index 1 contains
     * the number of 'B', and so on.
     * This function must be case-insensitive, i.e. the character 'f'
     * must be considered as the same as character 'F'.
     * Characters that are neither in the range 'a' to 'z', nor in the
     * range 'A' to 'Z' must be ignored.
     *
     * @param s The string of interest.
     * @return An array counting the number of occurrences of each
     * letter.
     **/
    public static int[] countAlphabet(String s) {
        int[] array = new int[26];
        String lower = s.toLowerCase();
        for (int i = 0; i < s.length();i++){
            int num = lower.charAt(i);
            if (num >= 97 && num <= 122){
                array[num-97] += 1;
            }
        }
        return array;
    }


    /**
     * Check whether one string is an anagram of another string. It is
     * strongly advised to use the function "countAlphabet()" as a
     * building block for function "isAnagram()".
     * @param s1 The first string.
     * @param s2 The second string.
     * @return <code>true</code> iff. the two strings are anagrams.
     **/
    public static boolean isAnagram(String s1,String s2) {
        int[] array1 = countAlphabet(s1);
        int[] array2 = countAlphabet(s2);
        return Arrays.equals(array1, array2);
    }
}