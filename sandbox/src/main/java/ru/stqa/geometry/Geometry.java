package ru.stqa.geometry;

import ru.stqa.geometry.figures.*;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Geometry {
    public static void main(String[] args) {

        Supplier<Square> randomSquare = () -> new Square(new Random().nextDouble(100.0));

        var squares = Stream.generate(randomSquare).limit(5);
        squares.peek(Square::printSquareArea).forEach(Square::printPerimetrArea);





//        squares.forEach(Square::printSquareArea);
//        Consumer<Square> print = Square::printSquareArea;
//
//        Rectangle.printRectangleArea(new Rectangle(3.0,5.0));
//        Rectangle.printRectangleArea(new Rectangle(7.0,9.0));
//
//        Triangle.printTriangleArea(new Triangle(5.0,5.0,6.0));
//        Triangle.printTriangleArea(new Triangle(6.0,6.0,8.0));


    }

}
