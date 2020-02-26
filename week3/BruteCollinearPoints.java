import java.util.ArrayList;

public class BruteCollinearPoints {

    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.IllegalArgumentException();
        }

        for (Point p : points) {
            if (p == null) {
                throw new java.lang.IllegalArgumentException();
            }
        }



        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new IllegalArgumentException();
                }
            }

        }



        Point[] copyPoints = points.clone();

        edu.princeton.cs.algs4.Quick.sort(copyPoints);

        ArrayList<LineSegment> segs = new ArrayList<>();

        for (int i = 0; i < points.length - 3; i++) {
            for (int j = i + 1; j < points.length - 2; j++) {
                for (int k = j + 1; k < points.length - 1; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        double s1 = copyPoints[i].slopeTo(copyPoints[j]);
                        if (copyPoints[i].slopeTo(copyPoints[k]) == s1 && copyPoints[i].slopeTo(copyPoints[l]) == s1) {
                            segs.add(new LineSegment(copyPoints[i], copyPoints[l]));
                        }
                    }

                }

            }

        }

        segments = segs.toArray(new LineSegment[segs.size()]);

    }   // finds all line segments containing 4 points
    public           int numberOfSegments() {
        return segments.length;

    }        // the number of line segments
    public LineSegment[] segments() {
        return segments.clone();
    }              // the line segments
}
