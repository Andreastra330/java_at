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
    void cannotSideWithNegativeValue(){
        try{
            new Triangle(-5.0,5,5);
            Assertions.fail();
        } catch (IllegalArgumentException exception){

        }
    }

    @Test
    @DisplayName("Проверка что сумма любых двух сторон не может быть меньше третьей стороны")
    void sumTwoSideCannotBeLessThirdSide(){
        try {
            new Triangle(3.0,6.0,10.0);
            Assertions.fail();
        } catch (IllegalArgumentException exception)
        {

        }
    }
}
