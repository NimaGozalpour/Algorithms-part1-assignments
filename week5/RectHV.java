import edu.princeton.cs.algs4.StdDraw;
public class RectHV {
    private final double xmin;
    private final double ymin;
    private final double xmax;
    private final double ymax;
    public    RectHV(double xmin, double ymin,      // construct the rectangle [xmin, xmax] x [ymin, ymax]
                     double xmax, double ymax) {
        if (xmin > xmax || ymin > ymax) {
            throw new IllegalArgumentException();
        }

        this.xmax = xmax;
        this.xmin = xmin;
        this.ymax = ymax;
        this.ymin = ymin;

    }     // throw an IllegalArgumentException if (xmin > xmax) or (ymin > ymax)
    public  double xmin() {
        return xmin;
    }                           // minimum x-coordinate of rectangle
    public  double ymin() {
        return ymin;
    }                           // minimum y-coordinate of rectangle
    public  double xmax() {
        return xmax;

    }                           // maximum x-coordinate of rectangle
    public  double ymax() {
        return ymax;
    }                           // maximum y-coordinate of rectangle
    public boolean contains(Point2D p) {
        return p.x() >= xmin && p.x() <= xmax && p.y() >= ymin && p.y() <= ymax;
    }              // does this rectangle contain the point p (either inside or on boundary)?
    public boolean intersects(RectHV that) {


        if ((this.ymax - this.ymin) > (that.ymax - that.ymin)) {
            if (that.ymin <= this.ymax && this.ymin <= that.ymin) {
                if ((this.xmax - this.xmin) > (that.xmax - that.xmin)) {
                    if (that.xmin <= this.xmax && this.xmin <= that.xmin) {
                        return true;
                    }

                    if (that.xmax <= this.xmax && this.xmin <= that.xmax) {
                        return true;
                    }
                } else {
                    if (this.xmin <= that.xmax && that.xmin <= this.xmin) {
                        return true;
                    }

                    if (this.xmax <= that.xmax && that.xmin <= this.xmax) {
                        return true;
                    }
                }
            }


            if (that.ymax <= this.ymax && this.ymin <= that.ymax) {

                if ((this.xmax - this.xmin) > (that.xmax - that.xmin)) {
                    if (that.xmin <= this.xmax && this.xmin <= that.xmin) {
                        return true;
                    }

                    if (that.xmax <= this.xmax && this.xmin <= that.xmax) {
                        return true;
                    }
                } else {
                    if (this.xmin <= that.xmax && that.xmin <= this.xmin) {
                        return true;
                    }

                    if (this.xmax <= that.xmax && that.xmin <= this.xmax) {
                        return true;
                    }
                }

            }
        }



        if ((this.ymax - this.ymin) <= (that.ymax - that.ymin)) {

            if (this.ymin <= that.ymax && that.ymin <= this.ymin) {
                if ((this.xmax - this.xmin) > (that.xmax - that.xmin)) {
                    if (that.xmin <= this.xmax && this.xmin <= that.xmin) {
                        return true;
                    }

                    if (that.xmax <= this.xmax && this.xmin <= that.xmax) {
                        return true;
                    }
                }

                else {
                    if (this.xmin <= that.xmax && that.xmin <= this.xmin) {
                        return true;
                    }
                    if (this.xmax <= that.xmax && that.xmin <= this.xmax) {
                        return true;
                    }
                }
            }

            if (this.ymax <= that.ymax && that.ymin <= this.ymax) {

                if ((this.xmax - this.xmin) > (that.xmax - that.xmin)) {

                    if (that.xmin <= this.xmax && this.xmin <= that.xmin) {
                        return true;
                    }
                    if (that.xmax <= this.xmax && this.xmin <= that.xmax) {
                        return true;
                    }
                }
                else {

                    if (this.xmin <= that.xmax && that.xmin <= this.xmin) {
                        return true;
                    }
                    if (this.xmax <= that.xmax && that.xmin <= this.xmax) {
                        return true;
                    }
                }
            }
        }
        return false;


    }         // does this rectangle intersect that rectangle (at one or more points)?
    public  double distanceTo(Point2D p) {
        if (ymin >= p.y()) {
            if (xmin > p.x()) {
                Point2D p1 = new Point2D(xmin, ymin);
                return p.distanceTo(p1);
            }

            if (xmax < p.x()) {
                Point2D p1 = new Point2D(xmax, ymin);
                return p.distanceTo(p1);
            }

            return ymin - p.y();

        }

        if (ymax <= p.y()) {
            if (xmin >= p.x()) {
                Point2D p1 = new Point2D(xmin, ymax);
                return p.distanceTo(p1);
            }

            if (xmax <= p.x()) {
                Point2D p1 = new Point2D(xmax, ymax);
                return p.distanceTo(p1);
            }

            return p.y() - ymax;
        }

        if (xmin >= p.x()) {
            return xmin - p.x();
        }

        if (xmax <= p.x()) {
            return p.x() - xmax;
        }

        return 0;

    }            // Euclidean distance from point p to closest point in rectangle
    public  double distanceSquaredTo(Point2D p) {
        return distanceTo(p) * distanceTo(p);
    }    // square of Euclidean distance from point p to closest point in rectangle
    public boolean equals(Object that) {
        if (that == null) return false;
        if (this.getClass() != that.getClass()) return false;

        RectHV newRect = (RectHV) that;
        if (newRect.ymax != this.ymax) return false;
        if (newRect.xmax != this.xmax) return false;
        if (newRect.ymin != this.ymin) return false;
        if (newRect.xmin != this.xmin) return false;
        return true;

    }            // does this rectangle equal that object?
    public    void draw() {
        StdDraw.rectangle(xmin, ymax, xmax - xmin, ymax - ymin);
    }                           // draw to standard draw
    public  String toString(){
        return "("+xmin+", "+ymin+", "+xmax+", "+ymax+")";
    }                       // string representation
}
