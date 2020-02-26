
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.LinkedList;
import java.util.List;
import edu.princeton.cs.algs4.SET;
public class KdTree {

    private Node root = null;
    private int size = 0;
    private Point2D nearestPoint = null;
    private SET<Point2D> rangeSet;

    private class Node {

        private final Point2D point;
        private Node left;
        private Node right;
        private boolean isVertical;
        private RectHV rect;

        private Node(Point2D point, boolean isVertical, Node parent) {
            this.point = point;
            this.isVertical = isVertical;

            if (parent == null) {
                rect = new RectHV(0,0,1,1);
            }
            else {
                double xMin = parent.rect.xmin();
                double yMin = parent.rect.ymin();
                double xMax = parent.rect.xmax();
                double yMax = parent.rect.ymax();

                int com = parent.compareTo(parent.point);

                if (com > 0) {
                    if (isVertical) {
                        yMax = parent.point.y();
                    } else {
                        xMax = parent.point.x();

                    }
                } else {
                    if (isVertical) {

                        yMin = parent.point.y();
                    } else {
                        xMin = parent.point.x();
                    }
                }

                this.rect = new RectHV(xMin, yMin, xMax, yMax);
            }
        }

        public int compareTo(Point2D p) {
            if (this.isVertical) {
                return Double.compare(this.point.x(), p.x());

            }
            else {

                return Double.compare(this.point.y(), p.y());
            }
        }

        public void draw() {
            StdDraw.setPenRadius(0.005);

            if (isVertical) {
                StdDraw.setPenColor(StdDraw.RED);
                StdDraw.line(point.x(), rect.ymin(), point.x(), rect.ymax());
            } else {
                StdDraw.setPenColor(edu.princeton.cs.algs4.StdDraw.BLUE);
                StdDraw.line(rect.xmin(), point.y(), rect.xmax(), point.y());
            }

            StdDraw.setPenRadius(0.03);
            StdDraw.setPenColor(StdDraw.BLACK);
            point.draw();

            if (left != null) {
                left.draw();
            }

            if (right != null) {
                right.draw();
            }
        }

    }

    public         KdTree() {

    }                               // construct an empty set of points
    public           boolean isEmpty(){
        return size == 0;
    }                  // is the set empty?
    public               int size() {
        return size;
    }                         // number of points in the set
    public              void insert(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        root = add(root, p, true, null);

    }              // add the point to the set (if it is not already in the set)
    private Node add(Node node, Point2D point, boolean isVertical, Node previous) {
        if (node == null) {
            size++;
            return new Node(point, isVertical, previous);

        }

        if (node.point.compareTo(point) == 0) return node;
        if (node.compareTo(point) > 0) {
            node.left = add(node.left, point, !isVertical, node);
        }
        else {
            node.right = add(node.right, point, !isVertical, node);
        }
        return node;
    }
    public           boolean contains(Point2D p) {
        if (p == null) {
            throw new IllegalArgumentException();
        }

        return searchTree(root, p) != null;

    }            // does the set contain point p?

    private Node searchTree(Node node, Point2D point) {
        if (node == null) {
            return null;
        }

        if (node.point.compareTo(point) == 0) return node;
        if (node.compareTo(point) > 0) {
            return searchTree(node.left, point);
        }
        else {
            return searchTree(node.right, point);
        }
    }
    public              void draw() {
        if (root != null) {
            root.draw();
        }

    }                         // draw all points to standard draw
    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();

        rangeSet = new SET<Point2D>();
        range(root, rect, rangeSet);
        return rangeSet;
    }             // all points that are inside the rectangle (or on the boundary)

    private void range(Node node, RectHV rect, SET<Point2D> points) {

        if (node != null) {
            if (node.rect.intersects(rect)) {
                if (rect.contains(node.point)) {
                    points.add(node.point);
                }

                range(node.left, rect, points);
                range(node.right, rect, points);
            }
        }

    }
    public Point2D nearest(Point2D p) {
        if (p == null) throw new IllegalArgumentException();

        if (isEmpty()) return null;

        nearestPoint = root.point;
        nearestPoint(root, p);

        return nearestPoint;
    }

    private void nearestPoint(Node node, Point2D point) {
        if (node != null) {

            if (node.rect.distanceTo(point) < nearestPoint.distanceTo(point)) {


                if (node.point.distanceTo(point) < nearestPoint.distanceTo(point)) {
                    nearestPoint = node.point;
                }

                nearestPoint(node.left, point);
                nearestPoint(node.right, point);
            }
        }
    }


    //public static void main(String[] args)                  // unit testing of the methods (optional)
}
