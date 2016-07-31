package ru.stqa.pft.sandbox;

/**
 * Created by Edward on 22.07.2016.
 */
public class Distance {
    public static void main(String[] args) {

       // Point p1 = new Point(1.5, -2.3);
       // Point p2 = new Point(0, 3.6);
        //System.out.println("Расстояние между двумя точками = " + p1.distance(p2));

        Point p1 = new Point(1.5,-2.3);
        Point p2 = new Point(1,3.6);
        double d = p1.distance(p2);
        System.out.println("Расстояние между двумя точками = " + d);



    }
    //public static double distance(Point p1, Point p2){
       // return Math.sqrt(Math.pow((p2.x-p1.x),2)+Math.pow((p2.y-p1.y),2));

   // }
}
