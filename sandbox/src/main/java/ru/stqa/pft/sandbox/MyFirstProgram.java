package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
	hello("world");
    hello("users");
    hello("Edward");

    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());


    Rectangle r = new Rectangle(4,6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    Point p1 = new Point(1.5,-2.3,0.3,2);

    System.out.println("Расстояние между двумя точками = " + distance(p1));

  }

  public static double distance(Point p1){
      return Math.sqrt(Math.pow((p1.x2-p1.x),2)+Math.pow((p1.y2-p1.y),2));

  }


  public static void hello(String somebody){

    System.out.println("Hi, " + somebody + "!");
  }

}