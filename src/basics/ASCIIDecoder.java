package basics;
import java.util.ArrayList;

public class ASCIIDecoder {

    /*
     * Forbidden characters are passed as an array of int.
     * Each element of this array correspond to the decimal ASCII code
     * of a forbidden character OR null if there's no forbidden character
     * If you encounter one of these forbidden character
     * you must ignore it when you translate your sentence.
     *
     * the 2D array "sentences" contain a set of decimal ASCII code we want you
     * to translate. Each sub-element of this array is a different sentence.
     * Ex : if we pass this array : [ ["42", "72", "88"], ["98", "99", "111", "47", "55"]]
     * to your decode method, you should return : [ "*HX", "bco/7" ]
     *
     * You should NEVER return null or an array containing null
     */
    public static String [] decode(int[] forbidden, String[][] sentences) {
        ArrayList<StringBuilder> arraylist = new ArrayList<>();
        for (String[] line : sentences){
            StringBuilder build = new StringBuilder();
            for (String element : line){
                int num = Integer.parseInt(element);
                boolean isForbidden = false;
                if (forbidden != null){
                    for (int letter : forbidden){
                        if (num == letter){
                            isForbidden = true;
                            break;
                        }
                    }
                }
                if (!isForbidden){
                    build.append((char) num);
                }
            }
            arraylist.add(build);
        }
        String[] array = arraylist.stream()
                .map(String::valueOf)
                .toArray(String[]::new);
        //You can also use :
        /*
        String[] array = new String[sentences.length];
        for (int i = 0; i < arraylist.size(); i++) {
            array[i] = String.valueOf(arraylist.get(i));
        }
        */
        return array;
    }
    // Another solution without using Arraylist and StringBuilder
    /*

    public static String [] decode2(int[] forbidden, String[][] sentences) {
        String[] array = new String[sentences.length];
        for (int line = 0; line < sentences.length; line++) {
            String str = "";
            for (String element : sentences[line]) {
                int num = Integer.parseInt(element);
                boolean isForbidden = false;
                if (forbidden != null) {
                    for (int letter : forbidden) {
                        if (num == letter) {
                            isForbidden = true;
                            break;
                        }
                    }
                }
                if (!isForbidden) {
                    str += Character.toString((char) num);
                }
            }
            array[line] = str;
        }
        return array;
    }

     */

}