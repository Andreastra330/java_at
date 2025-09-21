package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TriangleTests {
    @Test
    @DisplayName("Проверка периметра треугольника")
    void trianglePerimetr() {
        Assertions.assertEquals(16, new Triangle(5.0, 5.0, 6.0).perimetr());
    }

    @Test
    @DisplayName("Проверка площади треугольника")
    void triangleArea() {
        Assertions.assertEquals(12, new Triangle(5.0, 5.0, 6.0).area());
    }

    @Test
    @DisplayName("Проверка что любая из сторон не может быть меньше 0")
    void cannotSideWithNegativeValue() {
        try {
            new Triangle(-5.0, 5, 5);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {

        }
    }

    @Test
    @DisplayName("Проверка что сумма любых двух сторон не может быть меньше третьей стороны")
    void sumTwoSideCannotBeLessThirdSide() {
        try {
            new Triangle(3.0, 6.0, 10.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception) {

        }
    }

    @Test
    void testEquality() {
        var Original = new Triangle(3.0, 4.0, 5.0);
        var t1 = new Triangle(3.0, 4.0, 5.0);
        var t2 = new Triangle(3.0, 5.0, 4.0);
        var t3 = new Triangle(4.0, 5.0, 3.0);
        var t4 = new Triangle(4.0, 3.0, 5.0);
        var t5 = new Triangle(5.0, 3.0, 4.0);
        var t6 = new Triangle(5.0, 4.0, 3.0);
        Assertions.assertEquals(Original, t1);
        Assertions.assertEquals(Original, t2);
        Assertions.assertEquals(Original, t3);
        Assertions.assertEquals(Original, t4);
        Assertions.assertEquals(Original, t5);
        Assertions.assertEquals(Original, t6);
    }
    @Test
    void testNonEquality(){
        var t1 = new Triangle(3.0,4.0,5.0);
        var t2 = new Triangle(3.0,9.0,6.0);
        Assertions.assertNotEquals(t1,t2);
    }

    @Test
    void testEqualityTrue(){
        var t1 = new Triangle(3.0,4.0,5.0);
        var t2 = new Triangle(3.0,4.0,5.0);
        Assertions.assertTrue(t1.equals(t2));
    }
}
