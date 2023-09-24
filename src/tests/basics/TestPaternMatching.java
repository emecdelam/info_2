package tests.basics;

import basics.PatternMatching;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestPaternMatching {
    @Test
    public void testFind(){
        String input = "this is a hello, here's another one";
        Assertions.assertEquals(PatternMatching.find("hello",input),10);
        System.out.println(PatternMatching.find("","test"));
    }
}
