package tests.basics;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import basics.MagicSquare;
public class TestMagicSquare {
    @Test
    public void testIsMagicSquare(){
        int[][] input1 = new int[][]{
                {2,7,6},
                {9,5,1},
                {4,3,8}
        };
        int[][] input2 = new int[][]{
                {2,7,6},
                {8,5,1},
                {4,3,8}
        };
        Assertions.assertTrue(MagicSquare.isMagicSquare(input1));
        Assertions.assertFalse(MagicSquare.isMagicSquare(input2));
    }
}
