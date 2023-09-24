package basics;

@SuppressWarnings("unused")
public class CommonElements {
    /**
     *
     * @param tab1 is a non null array
     * @param tab2 is a non null array
     * @return the number of elements that are the same at the same index
     *         more exactly the size of set {i such that tab1[i] == tab2[i]}
     *         for instance count([1,3,5,5],[1,2,5,5,6]) = 3
     */
    public static int count(int [] tab1, int [] tab2) {
        int count = 0;
        if (tab1.length < tab2.length){
            for (int i=0 ; i<tab1.length; i++){
                if (tab1[i] == tab2[i]){
                    count++;
                }
            }
        } else {
            for (int i=0 ; i<tab2.length; i++){
                if (tab1[i] == tab2[i]){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     *
     * @param tab1 is a non null 2D array
     * @param tab2 is a non null 2D array
     * @return the number of elements that are the same at the same index
     *         more exactly the size of set {(i,j) such that tab1[i][j] == tab2[i][j]}
     */
    public static int count(int [][] tab1, int [][] tab2) {
        int count = 0;
        if (tab1.length < tab2.length){
            for (int i=0 ; i<tab1.length; i++){
                if (tab1[i].length < tab2[i].length){
                    for (int j=0; j<tab1[i].length;j++){
                        if (tab1[i][j] == tab2[i][j]){
                            count++;
                        }
                    }
                } else {
                    for (int j=0; j<tab2[i].length;j++){
                        if (tab1[i][j] == tab2[i][j]){
                            count++;
                        }
                    }
                }

            }
        } else {
            for (int i=0 ; i<tab2.length; i++){
                if (tab1[i].length < tab2[i].length){
                    for (int j=0; j<tab1[i].length;j++){
                        if (tab1[i][j] == tab2[i][j]){
                            count++;
                        }
                    }
                } else {
                    for (int j=0; j<tab2[i].length;j++){
                        if (tab1[i][j] == tab2[i][j]){
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}