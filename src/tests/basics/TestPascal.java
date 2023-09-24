package tests.basics;

import basics.Pascal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPascal {
    @Test
    public void testPascal(){

        int[] actual = Pascal.pascal(5);
        int[] expected = new int[]{1, 4, 6, 4, 1};
        Assertions.assertEquals(expected.length, actual.length);
        Assertions.assertArrayEquals(expected, actual);
    }
}
