package ru.stqa.geometry.figures;

import java.util.Arrays;
import java.util.Objects;

public record Triangle(double a, double b, double c) {
    public Triangle{
        if(a <0 || b<0 || c<0)
        {
            throw new IllegalArgumentException("Triangle side cannot be negative");
        }
        if((a+b)<c || (a+c)<b || (c+b)<a){
            throw new IllegalArgumentException("Сумма двух сторон не может быть меньше третьей стороны");
        }
    }
    public double perimetr(){
        return a+b+c;
    }
    public  double area(){
        double p = (perimetr())/2;
        return Math.sqrt(p*(p-a)*(p-b)*(p-c));
    }

    public static void printTriangleArea(Triangle t){
        String text = String.format("Площадь треугольника со сторонами %f,%f,%f = %f", t.a,t.b,t.c, t.area());
        System.out.println(text);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Triangle triangle = (Triangle) o;
//        Я решил воспользоваться сортировкой массивов из-за слишком большого return
//        return     (Double.compare(a, triangle.a) == 0 && Double.compare(b, triangle.b) == 0 && Double.compare(c, triangle.c) == 0)
//                || (Double.compare(a, triangle.a) == 0 && Double.compare(b, triangle.c) == 0 && Double.compare(c, triangle.b) == 0)
//                || (Double.compare(a, triangle.b) == 0 && Double.compare(b, triangle.a) == 0 && Double.compare(c, triangle.c) == 0)
//                || (Double.compare(a, triangle.b) == 0 && Double.compare(b, triangle.c) == 0 && Double.compare(c, triangle.a) == 0)
//                || (Double.compare(a, triangle.c) == 0 && Double.compare(b, triangle.a) == 0 && Double.compare(c, triangle.b) == 0)
//                || (Double.compare(a, triangle.c) == 0 && Double.compare(b, triangle.b) == 0 && Double.compare(c, triangle.a) == 0);
        double[] t1 = {a,b,c};
        double[] t2 = {triangle.a,triangle.b,triangle.c};
        Arrays.sort(t1);
        Arrays.sort(t2);
        return Arrays.equals(t1,t2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
