package ru.stqa.geometry.figures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SquareTests {
    @Test
    @DisplayName("Проверка функции area")
    void canCalculateArea(){
        Assertions.assertEquals(25.0, Square.area(5.0));
    }

    @Test
    @DisplayName("Проверка функции perimetr")
    void canCalculatePerimetr(){
        Assertions.assertEquals(20.0,Square.perimetr(5.0));
    }
}
