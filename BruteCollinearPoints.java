/* *****************************************************************************
 *  Name: Joshua Slocum
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

    // private int that will keep track of line segments
    private int N = 0;

    // will use a line segment variable to keep track of line segments
    private LineSegment segment;

    // line segment array will be used
    private LineSegment[] LineSegments = new LineSegment[N];

    // will use the Point class that I have made for now

    // I can make error handling below more robust if
    // I instead use points.length for the for loop checks
    // below code handles errors as follows:
    // checks if points array is null
    // checks if any point in the array is null
    // checks if any point in the array is identical
    public BruteCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        for (int i = 0; i < 4; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
            Point p = points[i];
            for (int j = i + 1; j < 4; j++) {
                if (p.compareTo(points[j]) == 0) throw new IllegalArgumentException();
            }
        }

        // brute force approach to the problem
        // believe this works but need to check to make sure line segments
        // don't also include sub segments
        // TODO: make sure sub segments are not included in the line segments
        for (int i = 0; i < points.length; i++) {
            for (int j = 1; j < points.length; j++) {
                for (int k = 2; k < points.length; k++) {
                    for (int l = 3; l < points.length; l++) {
                        Point p = points[i];
                        Point q = points[j];
                        Point r = points[k];
                        Point s = points[l];
                        // TODO: implement a check for points themselves being
                        // TODO: minimal/maximal by using my Point class compareTo method
                        if (p.slopeTo(q) == p.slopeTo(r) && p.slopeTo(r) == p.slopeTo(s)) {
                            if (p.compareTo(q) < 0 && p.compareTo(r) < 0 && p.compareTo(s) < 0
                                    && s.compareTo(r) > 0 && s.compareTo(q) > 0
                                    && s.compareTo(p) > 0) {
                                segment = new LineSegment(p, s);
                                N++;
                                LineSegment[] temp = new LineSegment[N];
                                for (int z = 0; z < LineSegments.length; z++) {
                                    temp[z] = LineSegments[z];
                                }
                                temp[N - 1] = segment;
                                LineSegments = temp;
                            }
                        }
                    }
                }
            }
        }
    }

    public int numberOfSegments() {
        return N;
    }

    public LineSegment[] segments() {
        return LineSegments;
    }

    public static void main(String[] args) {
        // read the n points from a file
        // In in = new In(args[0]);
        // int n = in.readInt();
        // Point[] points = new Point[n];
        // for (int i = 0; i < n; i++) {
        //     int x = in.readInt();
        //     int y = in.readInt();
        //     points[i] = new Point(x, y);
        // }

        int n = StdIn.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            points[i] = new Point(x, y);
        }


        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
        }
        StdOut.println(collinear.numberOfSegments());
    }
}
