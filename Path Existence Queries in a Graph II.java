class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] qrr) {
       int arr[][] = new int[n][2];
       for(int i=0;i<n;i++){
        arr[i][0] = nums[i];
        arr[i][1] = i;
       } 
       Arrays.sort(arr,(a,b) ->{return a[0]-b[0];});
       int comp[] = new int[n];
       for(int i=1;i<n;i++){
        if(arr[i][0]-arr[i-1][0] <= maxDiff){
            comp[i] = comp[i-1];
        }
        else{
            comp[i] = comp[i-1]+1;
        }
       }
       HashMap<Integer,Integer> map = new HashMap<>();
       for(int i=0;i<n;i++){
        map.put(arr[i][1],i);
       }
       int next[] = new int[n];
       int r=0;
       for(int i=0;i<n;i++){
        while(r<n && arr[r][0] - arr[i][0] <= maxDiff){
            r++;
        }
        next[i] = r-1;
       }
       int par[][] = new int[n][20];
       for(int i=0;i<n;i++){
        par[i][0] = next[i];
       }
       for(int j=1;j<20;j++){
        for(int i=0;i<n;i++){
            par[i][j] = par[par[i][j-1]][j-1];
        }
       }
       int ans[] = new int[qrr.length];
       for(int i=0;i<qrr.length;i++){
        int u = qrr[i][0];
        int v = qrr[i][1];
        int au = map.get(u);
        int av = map.get(v);
        if(comp[au] != comp[av]){
            ans[i] = -1;
        }
        else{
            int min = Math.min(au,av);
            int max = Math.max(au,av);
            if(u==v){
                ans[i] = 0;
                continue;
            }
            int cur = min;
            int move = 0;
            for(int j=19;j>=0;j--){
                if(par[cur][j]<max){
                    cur = par[cur][j];
                    move += 1<<j;
                }
            }
            ans[i] = move+1;
        }
       }
       return ans;
    }
}
