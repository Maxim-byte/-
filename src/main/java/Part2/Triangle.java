package Part2;

import java.security.spec.ECField;

public class Triangle {

    public static class Point {
        public double x, y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    private Point a, b, c;

    public Triangle(Point a, Point b, Point c) throws Exception {
        if (a == null || b == null || c == null) {
            throw new NullPointerException("Point not may be null.");
        }

        double sideA = Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y));
        double sideB = Math.sqrt((b.x - c.x) * (b.x - c.x) + (b.y - c.y) * (b.y - c.y));
        double sideC = Math.sqrt((a.x - c.x) * (a.x - c.x) + (a.y - c.y) * (a.y - c.y));

        if (sideA * sideB * sideC == 0 || sideA + sideB <= sideC || sideA + sideC <= sideB) {
            throw new Exception("Incorrect Points!\n");
        }

        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void setC(Point c) {
        this.c = c;
    }

    public void setB(Point b) {
        this.b = b;
    }

    public void setA(Point a) {
        this.a = a;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public Point getC() {
        return c;
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "a=" + a.toString() +
                ", b=" + b.toString() +
                ", c=" + c.toString() +
                ", area=" + getArea() +
                ", perimeter=" + getPerimeter() +
                ", medianIntersection=" + getMedianIntersection() +
                '}';
    }

    public double getArea() {
        return (1.0 / 2.0) * Math.abs(((b.x - a.x) * (c.y - a.y) - (c.x - a.x) * (b.y - a.x)));
    }

    public double getPerimeter() {
        return Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y)) +
                Math.sqrt((a.x - c.x) * (a.x - c.x) + (a.y - c.y) * (a.y - c.y)) +
                Math.sqrt((b.x - c.x) * (b.x - c.x) + (b.y - c.y) * (b.y - c.y));
    }

    public Point getMedianIntersection() {
        return new Point((a.x + b.x + c.x) / 3, (a.y + b.y + c.y) / 3);
    }

}
