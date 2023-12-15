package graphs;

import java.util.LinkedList;
import java.util.Collections;
/**
 * We are interested in solving a maze represented
 * by a matrix of integers 0-1 of size nxm.
 * This matrix is a two-dimensional array.
 * An entry equal to '1' means that there
 * is a wall and therefore this position is not accessible,
 * while '0' means that the position is free.
 * We ask you to write a Java code to discover
 * the shortest path between two coordinates
 * on this matrix from (x1, y1) to (x2, y2).
 * The moves can only be vertical (up/down) or horizontal (left/right)
 * (not diagonal), one step at a time.
 * The result of the path is an Iterable of
 * coordinates from the origin to the destination.
 * These coordinates are represented by integers
 * between 0 and n * m-1, where an integer 'a'
 * represents the position x =a/m and y=a%m.
 * If the start or end position is a wall
 * or if there is no path, an empty Iterable must be returned.
 * The same applies if there is no path
 * between the origin and the destination.
 */
public class Maze {
    public static Iterable<Integer> shortestPath(int[][] maze, int x1, int y1, int x2, int y2) {
        if(maze[x1][y1] == 1 || maze[x2][y2] == 1){
            return new LinkedList<>();
        }
        LinkedList<Integer> queue = new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();
        int size = maze.length * maze[0].length;
        int[] to = new int[size];
        boolean[] visited = new boolean[size];
        int src = ind(x1,y1,maze[0].length);
        int dest = ind(x2,y2,maze[0].length);
        visited[ind(x1,y1, maze[0].length)] = true;
        queue.add(ind(x1,y1,maze[0].length));
        boolean found = false;
        final int[][] neighbours = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

        while (!queue.isEmpty() && !found){
            int act = queue.remove();
            int x_act = row(act, maze[0].length);
            int y_act = row(act, maze[0].length);
            for (int i = 0; i < 4; i++) {
                int xn = x_act + neighbours[i][0];
                int yn = y_act + neighbours[i][1];

                if(( 0 <= xn && xn < maze.length) && (0 <= yn && yn < maze[0].length ) && maze[xn][yn] != 1){
                    int pos = ind(xn,yn,maze[0].length);
                    if(!visited[pos]){
                        queue.add(pos);
                        to[pos] = act;
                        visited[pos] = true;
                        if(xn == x2 && yn == y2) found = true;
                    }
                }
            }
        }

        if(visited[dest]){
            while(dest != src){
                res.add(dest);
                dest = to[dest];
            }
            res.add(dest);
            Collections.reverse(res);
            return res;
        }
        return res;
    }

    public static int ind(int x, int y, int lg) {
        return x * lg + y;
    }

    public static int row(int pos, int mCols) {
        return pos / mCols;
    }

    public static int col(int pos, int mCols) {
        return pos % mCols;
    }

}
