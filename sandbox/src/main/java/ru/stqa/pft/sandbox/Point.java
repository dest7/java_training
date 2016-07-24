package ru.stqa.pft.sandbox;

/**
 * Created by Edward on 22.07.2016.
 */
public class Point{
    public double x;
    public double y;

    public Point (double x, double y){
        this.x = x;
        this.y = y;
    }

   // Пока не понял как конструктор собрать
     public double distance(){
        //return Math.sqrt(Math.pow((p2.x-p1.x),2)+Math.pow((p2.y-p1.y),2));
       return Math.sqrt(Math.pow((this.x-this.x),2)+Math.pow((this.y-this.y),2));
    }
}

