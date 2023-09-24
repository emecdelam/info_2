package tests.basics;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import basics.KNN;
public class TestKNN {
    @Test
    public void testPredictSuccess() {
        KNN.Student[] etudiant = new KNN.Student[]{
                new KNN.Student(new double[]{90, 91}, true),  // étudiant_0
                new KNN.Student(new double[]{80, 75}, true),  // étudiant_1
                new KNN.Student(new double[]{70, 65}, false), // étudiant_2
                new KNN.Student(new double[]{30, 40}, true),  // étudiant_3
                new KNN.Student(new double[]{20, 30}, false), // étudiant_4
                new KNN.Student(new double[]{45, 33}, false), // étudiant_5

        };
        Assertions.assertTrue(KNN.predictSuccess(etudiant,new double[]{88,95},3));
    }
}
