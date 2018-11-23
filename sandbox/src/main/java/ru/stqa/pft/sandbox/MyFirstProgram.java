package ru.stqa.pft.sandbox;

public class MyFirstProgram {

    public static void main(String[] args) {
        hello("wer");
        Square s = new Square(5);
        Rectangle r = new Rectangle(5,4);

        System.out.println("Площадь квадрата со стороной l = " + s.l + " равна " + s.area());
        System.out.println("Площадь прямоугольника со сторонами a= " + r.a + " b = " + r.b + " равна " + r.area());

        Point a = new Point(1,2);
        Point b = new Point(1,2);
        System.out.println("Расстояние между двумя точками " +
                "a("+ a.x + "," + a.y + ")"+ " и b("+ b.x + "," + b.y + ")" +" равно " + Point.distance(a,b));
    }
    public static void hello(String someboby){

        System.out.println("Hello"+ someboby);
    }

}
