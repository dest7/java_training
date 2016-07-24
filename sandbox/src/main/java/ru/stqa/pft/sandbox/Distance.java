package ru.stqa.pft.sandbox;

/**
 * Created by Edward on 22.07.2016.
 */
public class Distance {
    public static void main(String[] args){
        Point p = new Point(0.2,2,-0.3,3);



        //Rectangle r = new Rectangle(4,6);
       // System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());


        System.out.println("Расстояние между двумя точками = " + p.distanceMethod());
        //System.out.println("Расстояние между двумя точками = " + distance(p1,p2));
    }
}
