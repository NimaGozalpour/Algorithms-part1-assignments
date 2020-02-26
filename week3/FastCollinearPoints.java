
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class FastCollinearPoints {

    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {
        if (points == null) {
            throw new java.lang.IllegalArgumentException();
        }

        for (Point p : points) {
            if (p == null){
                throw new java.lang.IllegalArgumentException();
            }
        }


        for (int i = 0; i < points.length - 1; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0) {
                    throw new java.lang.IllegalArgumentException();
                }

            }

        }



        ArrayList<LineSegment> segs = new ArrayList<>();
        Point[] pointsCopy = new Point[points.length];
        pointsCopy = points;

        edu.princeton.cs.algs4.Quick.sort(pointsCopy);

        for (int i = 0; i < points.length - 1; i++) {
            Point p = pointsCopy[i];

            Double[][] doubles = new Double[points.length - i - 1][2];
            int count = 0;
            for (int j = i + 1; j < points.length; j++) {
                doubles[count][0] = p.slopeTo(points[j]);
                doubles[count][1] =(double) j;
                count++;
            }

            Arrays.sort(doubles, arrayComparator);


            count = 0;
            Double baseSlope = 0.0;
            for (int j = 0; j < doubles.length; j++) {
                edu.princeton.cs.algs4.StdOut.println(doubles[j][0]+" "+doubles[j][1]);
            }
            for (int j = 0; j < doubles.length - 1; j++) {
                if (count == 0) {
                    baseSlope = doubles[j][0];
                }
                edu.princeton.cs.algs4.StdOut.println(doubles[j + 1][0]+"    "+baseSlope);
                edu.princeton.cs.algs4.StdOut.println(count);
                if (baseSlope.equals(doubles[j + 1][0])) {
                    count++;

                }

                else {
                    if (count >= 3) {
                        Double in = doubles[j][1];
                        int index = in.intValue();
                        segs.add(new LineSegment(pointsCopy[i], pointsCopy[index]));

                    }
                    count = 0;
                }
                if (count >= 3) {
                    Double in = doubles[doubles.length - 1][1];
                    int index = in.intValue();
                    segs.add(new LineSegment(pointsCopy[i], pointsCopy[index]));

                }

            }
        }
        segments = segs.toArray(new LineSegment[segs.size()]);

    }     // finds all line segments containing 4 or more points
    public           int numberOfSegments() {
        return segments.length;
    }        // the number of line segments
    public LineSegment[] segments() {
        return segments.clone();
    }               // the line segments

    private Comparator<Double[]> arrayComparator = new Comparator<Double[]>() {
        @Override
        public int compare(Double[] o1, Double[] o2) {
            return o1[0].compareTo(o2[0]);
        }
    };
}
