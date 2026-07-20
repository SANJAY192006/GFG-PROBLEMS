import java.util.*;

class Solution {
    public int maximumSafenessFactor(List<List<Integer>> grid) {
        int n = grid.size(), dirs[][] = {{0,1}, {0,-1}, {1,0}, {-1,0}};
        if (grid.get(0).get(0) == 1 || grid.get(n-1).get(n-1) == 1) return 0;

        int[][] dist = new int[n][n];
        for (int[] row : dist) Arrays.fill(row, -1);
        Queue<int[]> q = new LinkedList<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid.get(r).get(c) == 1) { dist[r][c] = 0; q.offer(new int[]{r, c}); }
            }
        }

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int[] d : dirs) {
                int nr = curr[0] + d[0], nc = curr[1] + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && dist[nr][nc] == -1) {
                    dist[nr][nc] = dist[curr[0]][curr[1]] + 1;
                    q.offer(new int[]{nr, nc});
                }
            }
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        pq.offer(new int[]{dist[0][0], 0, 0});
        dist[0][0] = -1; // reuse dist matrix to mark visited

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (curr[1] == n - 1 && curr[2] == n - 1) return curr[0];

            for (int[] d : dirs) {
                int nr = curr[1] + d[0], nc = curr[2] + d[1];
                if (nr >= 0 && nr < n && nc >= 0 && nc < n && dist[nr][nc] != -1) {
                    pq.offer(new int[]{Math.min(curr[0], dist[nr][nc]), nr, nc});
                    dist[nr][nc] = -1; 
                }
            }
        }
        return 0;
    }
}
