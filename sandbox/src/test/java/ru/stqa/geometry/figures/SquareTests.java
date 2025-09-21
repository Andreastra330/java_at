package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SquareTests {
    @Test
    @DisplayName("Проверка площади квадрата")
    void canCalculateArea() {
        //Можно писать и так
//        var s = new Square(5.0);
//        double result = s.area();
//        Assertions.assertEquals(25.0,result);
        Assertions.assertEquals(25.0, new Square(5.0).area());
    }

    @Test
    @DisplayName("Проверка периметра квадрата")
    void canCalculatePerimetr() {
        Assertions.assertEquals(20.0, new Square(5.0).perimetr());
    }

    @Test
    @DisplayName("Проверка что сторона не может быть меньше 0")
    void cannotCreateSquareWithNegativeSide(){
        try {
            new Square(-5.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception)
        {

        }
    }

    @Test
    void testEquality(){
        var s1 = new Square(5.0);
        var s2 = new Square(5.0);
        Assertions.assertEquals(s1,s2);
    }

    @Test
    void testNonEquality(){
        var s1 = new Square(5.0);
        var s2 = new Square(4.0);
        Assertions.assertNotEquals(s1,s2);
    }

    @Test
    void testEqualityTrue(){
        var s1 = new Square(5.0);
        var s2 = new Square(5.0);
        Assertions.assertTrue(s1.equals(s2));
    }
}
