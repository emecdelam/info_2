package basics;
public class StringUtils {
    /**
     * Split a string according to a delimiter
     *
     * @param str The string to split
     * @param delimiter The delimiter
     * @return An array containing the substring which fall
     *          between two consecutive occurence of the delimiter.
     *          If there is no occurence of the delimiter, it should
     *          return an array of size 1 with the string at element 0
     */
    public static String [] split(String str, char delimiter){
        int count = 1;
        for (int i = 0; i<str.length();i++)
            if (str.charAt(i) == delimiter){
                count++;
            }
        // count becomes the number of delimitted section in the array
        String[] array = new String[count];
        int index = 0;
        String current_string = "";
        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) == delimiter){
                array[index] = current_string;
                current_string = "";
                index++;
            }
            else{
                current_string =current_string.concat(String.valueOf(str.charAt(i)));
            }
            array[index] = current_string;
        }
        return array;
    }

    /**
     * Find the first occurence of a substring in a string
     *
     * @param str The string to look in
     * @param sub The string to look for
     * @return The index of the start of the first appearance of
     *          the substring in str or -1 if sub does not appear
     *          in str
     */
    public static int indexOf(String str, String sub){
        for (int i=0; i < str.length(); i++){
            if (str.charAt(i) == sub.charAt(0)){
                if (str.startsWith(sub,i)){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Convert a string to lowercase
     *
     * @param str The string to convert
     * @return A new string, same as str but with every
     *          character put to lower case.
     */
    public static String toLowerCase(String str){
        StringBuilder low = new StringBuilder();
        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) >= 65 && str.charAt(i) <= 90){
                low.append((char) (str.charAt(i) + 32));
            } else {
                low.append((char) str.charAt(i));
            }
        }
        return low.toString();
    }
    // Alternatively
    /*
    public static String toLowerCase(String str){
        return str.toLowerCase();
    */


    /**
     * Check if a string is a palyndrome
     *
     * A palyndrome is a sequence of character that is the
     * same when read from left to right and from right to
     * left.
     *
     * @param str The string to check
     * @return true if str is a palyndrome, false otherwise
     */
    public static boolean palindrome(String str){
        // Handling of str = ""
        if (str.length() < 1){
            return true;
        }
        int iterations = str.length()/2;
        for (int i = 0; i < iterations+1 ; i++){
            if (str.charAt(i) != str.charAt(str.length()-1-i)){
                return false;
            }
        }
        return true;
    }
}