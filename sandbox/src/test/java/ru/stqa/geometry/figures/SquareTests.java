package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SquareTests {
    @Test
    @DisplayName("Проверка площади квадрата")
    void canCalculateArea(){
        //Можно писать и так
//        var s = new Square(5.0);
//        double result = s.area();
//        Assertions.assertEquals(25.0,result);
        Assertions.assertEquals(25.0, new Square(5.0).area());
    }

    @Test
    @DisplayName("Проверка периметра квадрата")
    void canCalculatePerimetr(){
        Assertions.assertEquals(20.0,new Square(5.0).perimetr());
    }

    @Test
    @DisplayName("Проверка периметра треугольника")
    void trianglePerimetr(){
        Assertions.assertEquals(16,new Triangle(5.0,5.0,6.0).perimetr());
    }

    @Test
    @DisplayName("Проверка площади треугольника")
    void triangleArea(){
        Assertions.assertEquals(12,new Triangle(5.0,5.0,6.0).area());
    }
}
