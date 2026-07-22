class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[] group = new int[n];
        int st = 1;
        for(int i=0;i<n-1;i++){
            group[i] = st;
            if(nums[i+1] - nums[i]>maxDiff){
                st++;
            }
        }
        group[n-1] = st;
        int m = queries.length;
        boolean[] ans = new boolean[m];
        for(int i=0;i<m;i++){
            int u = queries[i][0];
            int v = queries[i][1];
            ans[i] = group[u] == group[v];
        }
        return ans;
    }
}
