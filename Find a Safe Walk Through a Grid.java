import java.util.*;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size(), n = grid.get(0).size();
        int[][] dist = new int[m][n];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        
        Deque<int[]> dq = new ArrayDeque<>();
        dist[0][0] = grid.get(0).get(0);
        dq.addFirst(new int[]{0, 0});
        
        int[] dirs = {0, 1, 0, -1, 0};
        
        while (!dq.isEmpty()) {
            int[] curr = dq.pollFirst();
            int x = curr[0], y = curr[1];
            
            if (x == m - 1 && y == n - 1) {
                return dist[x][y] < health;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i], ny = y + dirs[i + 1];
                
                if (nx >= 0 && nx < m && ny >= 0 && ny < n) {
                    int weight = grid.get(nx).get(ny);
                    if (dist[x][y] + weight < dist[nx][ny]) {
                        dist[nx][ny] = dist[x][y] + weight;
                        if (weight == 0) {
                            dq.addFirst(new int[]{nx, ny});
                        } else {
                            dq.addLast(new int[]{nx, ny});
                        }
                    }
                }
            }
        }
        
        return dist[m - 1][n - 1] < health;
    }
}
