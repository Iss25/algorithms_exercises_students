package exam;

import java.util.*;

/**
 * Santa Claus is streamlining his delivery routes with a magical map.
 * This year, the Elves' initial calculations for the distances between houses are a bit off, failing to respect the triangle inequality,
 * which states that the direct path between two points should not be longer than any indirect path.
 * However, Santa also wants to ensure that the new distances do not imply a shorter path than originally calculated,
 * keeping the realism of actual travel distances.
 *
 * Santa needs this map adjusted to balance efficiency and realism.
 * Your task is to modify the magical map so that it adheres to the triangle inequality
 * while ensuring that no direct path becomes shorter than in the original map.
 * In other terms it means that for all pairs (i, j) the value
 * the cost i->j must be the shortest path between i and j in the original map.
 */
public class SantaMagicalMap {


    /**
     * Adjust minimally the distance matrix to satisfy the triangle inequality.
     * The cost i->j must become the shortest path between i and j in the original map.
     *
     *
     * @param matrix The distance matrix, matrix[i][j] is the distance between house i and house j.
     *               It is a square matrix with the same number of rows and columns.
     *               The matrix is not necessarily symmetric, i.e. matrix[i][j] != matrix[j][i],
     *               but it is zero on the diagonal, i.e. matrix[i][i] = 0.
     */
    public static void adjustDistanceMatrix(int[][] matrix) {
        int N = matrix.length;

        int[][] dist = new int[N][N];

        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);

            final int finalI = i;
            PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt((index) -> dist[finalI][index]));
            pq.add(i);
            dist[i][i] = 0;

            while (!pq.isEmpty()) {
                int u = pq.poll();

                for (int j = 0; j < N; j++) {
                    if (dist[i][u] + matrix[u][j] < dist[i][j]) {
                        dist[i][j] = dist[i][u] + matrix[u][j];
                        pq.add(j);
                    }
                }
            }
        }
        System.arraycopy(dist,0,matrix,0,dist.length);
    }
}