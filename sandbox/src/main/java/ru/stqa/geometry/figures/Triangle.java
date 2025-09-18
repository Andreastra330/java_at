package ru.stqa.geometry.figures;

public record Triangle(double a,double b,double c) {
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
}
