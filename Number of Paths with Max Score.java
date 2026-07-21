class Solution {
    private static int mod = 1000000007;
    private class Pair{
        int val;
        long paths;
        Pair(int val, long paths){
            this.val = val;
            this.paths = paths;
        }
        public void compare(Pair p1, Pair p2){
            int max = Math.max(p1.val, p2.val);
            if(max == -1 || this.val > max){
                return;
            }
            if(this.val < max){
                this.val = max;
                this.paths = 0;
            }
            if(this.val == p1.val){
                this.paths = (this.paths + p1.paths) % mod;
            }
            if(this.val == p2.val){
                this.paths = (this.paths + p2.paths) % mod;
            }
        }
    }
    private Pair func(List<String> board, int r, int c, int n, Pair[][] dp){
        if(r == n-1 && c == n-1){
            return new Pair(0, 1);
        }
        if(r == n || c == n || board.get(r).charAt(c) == 'X'){
            return new Pair(-1, 0);
        }
        if(dp[r][c] != null){
            return dp[r][c];
        }
        
        Pair pair = func(board, r + 1, c + 1, n, dp);
        Pair pair1 = func(board, r + 1, c, n, dp);
        Pair pair2 = func(board, r, c + 1, n, dp);
        
        pair.compare(pair1, pair2);
        
        if(pair.val != -1){
            pair.val += board.get(r).charAt(c) - '0';
        }
        
        return dp[r][c] = pair;
    }
    
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        board.set(0, '0' + board.get(0).substring(1)); // replace 'E' with '0'
        
        Pair[][] dp = new Pair[n][n];
        
        Pair pair = func(board, 0, 0, n, dp);
        
        int[] ans = new int[2];
        if(pair.val != -1){
            ans[0] = pair.val;
            ans[1] = (int)pair.paths;
        }
        return ans;
    }
}
