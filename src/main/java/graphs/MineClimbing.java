package graphs;

import java.util.Arrays;
import java.util.PriorityQueue;


/**
 * You just bought yourself the latest game from the Majong™
 * studio (recently acquired by Macrosoft™): MineClimb™.
 * In this 3D game, the map is made up of size 1
 * dimensional cube blocks, aligned on a grid,
 * forming vertical columns of cubes.
 * There are no holes in the columns, so the state
 * of the map can be represented as a matrix M of size n⋅m
 * where the entry M_{i,j} is the height of
 * the cube column located at i,j (0 ≤ i < n, 0 ≤ j < m).
 * You can't delete or add cubes, but you do have
 * climbing gear that allows you to move from one column to another
 * (in the usual four directions (north, south, east, west),
 * but not diagonally). The world of MineClimb™ is round:
 * the position north of (0,j) is (n-1,j), the position
 * west of (i,0) is (i,m-1) , and vice versa.
 * <p>
 * The time taken to climb up or down a column depends on
 * the difference in height between the current column and the next one.
 * Precisely, the time taken to go from column (i,j)
 * to column (k,l) is |M_{i,j}-M_{k,l}|
 * <p>
 * Given the map of the mine, an initial position
 * and an end position, what is the minimum time needed
 * to reach the end position from the initial position?
 */
public class MineClimbing {

    public static class Pos_Cost implements Comparable<Pos_Cost>{
        public int x;
        public int y;
        public int cost;
        public Pos_Cost(int x,int y, int cost){
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
        @Override
        public int compareTo(Pos_Cost o) {
            return -o.cost + cost;
        }

    }

    /**
     * Returns the minimum distance between (startX, startY) and (endX, endY), knowing that
     * you can climb from one mine block (a,b) to the other (c,d) with a cost Math.abs(map[a][b] - map[c][d])
     * <p>
     * Do not forget that the world is round: the position (map.length,i) is the same as (0, i), etc.
     * <p>
     * map is a matrix of size n times m, with n,m > 0.
     * <p>
     * 0 <= startX, endX < n
     * 0 <= startY, endY < m
     */
    public static int best_distance(int[][] map, int startX, int startY, int endX, int endY) {
        final int[][] dir = new int[][] {{1,0},{-1,0},{0,-1},{0,1}};
        int[][] cost_to = new int[map.length][map[0].length];
        for (int i = 0; i < cost_to.length; i++) {
            Arrays.fill(cost_to[i],Integer.MAX_VALUE);
        }
        cost_to[startX][startY] = 0;
        PriorityQueue<Pos_Cost> queue = new PriorityQueue<>();
        queue.add(new Pos_Cost(startX,startY,0));
        while (!queue.isEmpty()){
            Pos_Cost curr = queue.poll();
            if(curr.x == endX && curr.y == endY){
                break;
            }
            for (int[] pos : dir) {
                int x = (pos[0] + curr.x + map.length) % map.length;
                int y = (pos[1] + curr.y + map[0].length) % map[0].length;
                int cost = Math.abs(map[curr.x][curr.y] - map[x][y]);
                if (cost_to[x][y] > cost + curr.cost){
                    cost_to[x][y] = cost + curr.cost;
                    queue.add(new Pos_Cost(x,y,cost_to[x][y]));
                }
            }
        }
        return cost_to[endX][endY];
    }
}
