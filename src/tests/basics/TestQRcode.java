package tests.basics;

import basics.QRcode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestQRcode {
    @Test
    public void testEquals(){
        boolean [][] t0 = new boolean[][] {
        {false,true,false,false},
        {false,false,true,true},
        {true,false,false,true},
        {true,true,false,true}
        };
        boolean [][] t2 = new boolean[][] {
        {true,false,true,true},
        {true,false,false,true},
        {true,true,false,false},
        {false,false,true,false}
        };
        Assertions.assertEquals(new QRcode(t0), new QRcode(t2));
    }
    @Test
    public void testRotation(){
        boolean[][] input1 = new boolean[][]{
                {true,false},
                {false,true}
        };
        boolean[][] output = new boolean[][]{{false,true},{true,false}};
        Assertions.assertTrue(areBooleanArraysEqual(input1, QRcode.rotateMatrix(output)));
    }
    private static boolean areBooleanArraysEqual(boolean[][] arr1, boolean[][] arr2) {
        if (arr1.length != arr2.length || arr1[0].length != arr2[0].length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            for (int j = 0; j < arr1[i].length; j++) {
                if (arr1[i][j] != arr2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
