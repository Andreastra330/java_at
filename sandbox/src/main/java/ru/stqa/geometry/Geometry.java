package ru.stqa.geometry;

import ru.stqa.geometry.figures.*;

public class Geometry {
    public static void main(String[] args) {

        Square.printSquareArea(new Square(7));
        Square.printSquareArea(new Square(5));
        Square.printSquareArea(new Square(3));

        Rectangle.printRectangleArea(new Rectangle(3.0,5.0));
        Rectangle.printRectangleArea(new Rectangle(7.0,9.0));

        Triangle.printTriangleArea(new Triangle(5.0,5.0,6.0));
        Triangle.printTriangleArea(new Triangle(6.0,6.0,8.0));


    }

}
