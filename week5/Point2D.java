import edu.princeton.cs.algs4.StdDraw;

public class Point2D implements Comparable<Point2D> {

    private final double x;
    private final double y;
    public Point2D(double x, double y){
        this.x = x;
        this.y = y;
    }
    // construct the point (x, y)
    public  double x() {
        return x;
    }                              // x-coordinate
    public  double y() {
        return y;
    }                              // y-coordinate
    public  double distanceTo(Point2D that) {
        double dis = Math.sqrt(Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2));
        return dis;
    }         // Euclidean distance between two points
    public  double distanceSquaredTo(Point2D that) {
        return Math.pow(this.x - that.x, 2) + Math.pow(this.y - that.y, 2);
    }  // square of Euclidean distance between two points
    public     int compareTo(Point2D that) {
        if (this.y < that.y) {
            return -1;
        }

        else if (this.y == that.y) {
            if (this.x < that.x) {
                return -1;
            }

            else if (this.x == that.x) {
                return 0;

            }

            return 1;
        }

        return 1;
    }         // for use in an ordered symbol table
    public boolean equals(Object that) {
        if (that == null) return false;
        if (this.getClass() != that.getClass()) return false;
        Point2D newOne = (Point2D) that;
        if (this.y != newOne.y) return false;
        if (this.x != newOne.x) return false;
        return true;
    }              // does this point equal that object?
    public    void draw() {
        StdDraw.point(this.x, this.y);
    }                           // draw to standard draw
    public  String toString() {
        return "("+x()+", "+y()+")";
    }                      // string representation
}
