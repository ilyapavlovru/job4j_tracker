package ru.job4j.condition;

import org.junit.Assert;
import org.junit.Test;

public class PointTest {
    @Test
    public void distance1() {
        int inX1 = 0;
        int inY1 = 0;
        int inX2 = 2;
        int inY2 = 0;
        Point a = new Point(inX1, inY1);
        Point b = new Point(inX2, inY2);
        double expected = 2.0;
        double out = a.distance(b);
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void distance2() {
        int inX1 = 0;
        int inY1 = 0;
        int inX2 = 0;
        int inY2 = 2;
        Point a = new Point(inX1, inY1);
        Point b = new Point(inX2, inY2);
        double expected = 2.0;
        double out = a.distance(b);
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void distance3() {
        int inX1 = 1;
        int inY1 = 1;
        int inX2 = 2;
        int inY2 = 2;
        Point a = new Point(inX1, inY1);
        Point b = new Point(inX2, inY2);
        double expected = 1.41;
        double out = a.distance(b);
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void distance3dFirst() {
        int inX1 = 1;
        int inY1 = 2;
        int inZ1 = 3;
        int inX2 = -7;
        int inY2 = -2;
        int inZ2 = 4;
        Point a = new Point(inX1, inY1, inZ1);
        Point b = new Point(inX2, inY2, inZ2);
        double expected = 9.0;
        double out = a.distance(b);
        Assert.assertEquals(expected, out, 0.1);
    }

    @Test
    public void distance3dSecond() {
        int inX1 = 0;
        int inY1 = -3;
        int inZ1 = 3;
        int inX2 = 3;
        int inY2 = 1;
        int inZ2 = 3;
        Point a = new Point(inX1, inY1, inZ1);
        Point b = new Point(inX2, inY2, inZ2);
        double expected = 5.0;
        double out = a.distance(b);
        Assert.assertEquals(expected, out, 0.1);
    }
}
