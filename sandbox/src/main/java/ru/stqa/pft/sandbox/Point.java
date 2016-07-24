package ru.stqa.pft.sandbox;

/**
 * Created by Edward on 22.07.2016.
 */
public class Point{
    public double x;
    public double y;
    public double x2;
    public double y2;

    public Point (double x, double y,double x2,double y2){
        this.x=x;
        this.y=y;
        this.x2=x2;
        this.y2=y2;
    }

   // Пока не понял как конструктор собрать

    public double distanceMethod(){
        //return Math.sqrt(Math.pow((p2.x-p1.x),2)+Math.pow((p2.y-p1.y),2));
        return Math.sqrt(Math.pow((this.x2-this.x),2)+Math.pow((this.y2-this.y),2));
    }
}

