package ru.job4j.condition;

import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TriangleTest {
    @Test
    public void area1() {
        int inX1 = -1;
        int inY1 = -3;
        int inX2 = 3;
        int inY2 = 4;
        int inX3 = 5;
        int inY3 = -5;
        Point a = new Point(inX1, inY1);
        Point b = new Point(inX2, inY2);
        Point c = new Point(inX3, inY3);
        Triangle t = new Triangle(a, b, c);
        double expected = 25.0;
        double out = t.area();
        Assert.assertEquals(expected, out, 0.01);
    }

    @Test
    public void area2() {
        int inX1 = 3;
        int inY1 = 2;
        int inX2 = 7;
        int inY2 = 5;
        int inX3 = 0;
        int inY3 = 0;
        Point a = new Point(inX1, inY1);
        Point b = new Point(inX2, inY2);
        Point c = new Point(inX3, inY3);
        Triangle t = new Triangle(a, b, c);
        double expected = 0.5;
        double out = t.area();
        Assert.assertEquals(expected, out, 0.01);
    }
}
