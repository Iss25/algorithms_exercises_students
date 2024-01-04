package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;

/**
 * Author Pierre Schaus
 *
 * Assume the following 5x5 matrix that represent a grid surface:
 * int [][] tab = new int[][] {{1,3,3,1,3},
 *                             {4,2,2,4,5},
 *                             {4,4,5,4,2},
 *                             {1,4,2,3,6},
 *                             {1,1,1,6,3}};
 *
 * Given a global water level, all positions in the matrix
 * with a value <= the water level are flooded and therefore unsafe.
 * So, assuming the water level is 3,
 * all safe points are highlighted between parenthesis:
 *
 *   1 , 3 , 3 , 1 , 3
 *  (4), 2 , 2 ,(4),(5)
 *  (4),(4),(5),(4), 2
 *   1 ,(4), 2 , 3 ,(6)
 *   1 , 1 , 1 ,(6), 3}
 *
 * The method you need to implement is
 * a method that find a safe-path between
 * two positions (row,column) on the matrix.
 * The path assume you only make horizontal or vertical moves
 * but not diagonal moves.
 *
 * For a water level of 4, the shortest path
 * between (1,0) and (1,3) is
 * (1,0) -> (2,0) -> (2,1) -> (2,2) -> (2,3) -> (1,3)
 *
 *
 * Complete the code below so that the {@code  shortestPath} method
 * works as expected
 */
public class GlobalWarmingPaths {

    int waterLevel;
    int [][] altitude;

    public GlobalWarmingPaths(int[][] altitude, int waterLevel) {
        this.waterLevel = waterLevel;
        this.altitude = altitude;
    }


    /**
     * Computes the shortest path between point p1 and p2
     * @param p1 the starting point
     * @param p2 the ending point
     * @return the list of the points starting
     *         from p1 and ending in p2 that corresponds
     *         the shortest path.
     *         If no such path, an empty list.
     */
    public List<Point> shortestPath(Point p1, Point p2) {
        boolean [][] marked = new boolean[altitude.length][altitude[0].length];
        Point [][] to = new Point[altitude.length][altitude[0].length];
        final int[][] dir = new int[][] {{-1,0},{0,1},{1,0},{0,-1}};
        List<Point> queue = new ArrayList<>();
        List<Point> path = new ArrayList<>();

        if(p1.equals(p2)){
            if (altitude[p1.getX()][p1.getY()] > waterLevel){
                path.add(p1);
                return path;
            }
            return null;
        }

        queue.add(p1);
        while (!queue.isEmpty()){
            Point curr = queue.remove(0);
            if(curr.equals(p2)){
                break;
            }
            for (int i = 0; i < 4; i++) {
                int x = curr.getX() + dir[i][0];
                int y = curr.getY() + dir[i][1];

                if((0 <= x && x < altitude.length) && (0 <= y && y < altitude[0].length) && altitude[x][y] > waterLevel){
                    if(!marked[x][y]){
                        marked[x][y] = true;
                        to[x][y] = curr;
                        queue.add(new Point(x,y));
                    }
                }
            }
        }
        if(marked[p2.getX()][p2.getY()]){
            for (Point i = p2; !i.equals(p1) ; i = to[i.getX()][i.getY()]) {
                path.add(i);
            }
            path.add(p1);
            Collections.reverse(path);
        }
        return path;

    }

    /**
     * This class represent a point in a 2-dimension discrete plane.
     * This is used to identify the cells of a grid
     * with X = row, Y = column
     */
    static class Point {
        private final int x;
        private final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Point) {
                Point p = (Point) o;
                return p.x == this.x && p.y == this.y;
            }
            return false;
        }
    }
}
