
import edu.princeton.cs.algs4.SET;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class PointSET {

    private final SET<Point2D> set;
    public         PointSET() {
        set = new SET<Point2D>();
    }                               // construct an empty set of points
    public           boolean isEmpty() {
        return set.isEmpty();
    }                      // is the set empty?
    public               int size() {
        return set.size();
    }                         // number of points in the set
    public              void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();

        }
        set.add(p);
    }              // add the point to the set (if it is not already in the set)
    public           boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();

        }

        return set.contains(p);
    }            // does the set contain point p?
    public              void draw() {
        for (Point2D p : set) {
            p.draw();
        }
    }                        // draw all points to standard draw
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) {
            throw new IllegalArgumentException();
        }
        SET<Point2D> rangeSet = new SET<Point2D>();
        for (Point2D p : set) {
            if (rect.contains(p)) rangeSet.add(p);
        }

        return rangeSet;
    }            // all points that are inside the rectangle (or on the boundary)
    public           Point2D nearest(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }
        if (isEmpty()) return null;
        Point2D near = null;
        double dis = 1000000000;
        for (Point2D p1 : set) {
            if (dis > p.distanceTo(p1)) {
                dis = p.distanceTo(p1);
                near = p1;
            }
        }

        return near;
    }            // a nearest neighbor in the set to point p; null if the set is empty

    /*
    public static void main(String[] args)                  // unit testing of the methods (optional)
    */

}
