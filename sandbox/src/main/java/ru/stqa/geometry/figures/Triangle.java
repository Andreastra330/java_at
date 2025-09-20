package ru.stqa.geometry.figures;

public record Triangle(double a,double b,double c) {
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
}
