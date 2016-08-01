package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Edward on 31.07.2016.
 */
public class DistanceTest {
    @Test
    public void testDistance(){
        Point p1 = new Point(1.5,-2.3);
        Point p2 = new Point(1,3.6);

        //Assert.assertEquals(p1.distance(p2),5.921148537234985);
        Assert.assertEquals(p1.distance(p2),distanceMath(p1,p2));

        //5.921148537234985
        //Math.sqrt(Math.pow((p2.x-p1.x),2)+Math.pow((p2.y-p1.y),2))
    }
    public static double distanceMath(Point p1, Point p2){
        return Math.sqrt(Math.pow((p2.x-p1.x),2)+Math.pow((p2.y-p1.y),2));

        }
}
