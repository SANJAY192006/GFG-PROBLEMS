public class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length(), MOD = 1_000_000_007;
        long[] pow10 = new long[m + 1], prefX = new long[m + 1];
        int[] prefSum = new int[m + 1], nzCount = new int[m + 1];
        
        pow10[0] = 1;
        for (int i = 0; i < m; i++) {
            pow10[i + 1] = (pow10[i] * 10) % MOD;
            int d = s.charAt(i) - '0';
            prefX[i + 1] = d != 0 ? (prefX[i] * 10 + d) % MOD : prefX[i];
            prefSum[i + 1] = prefSum[i] + d;
            nzCount[i + 1] = nzCount[i] + (d != 0 ? 1 : 0);
        }
        
        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0], r = queries[i][1];
            int k = nzCount[r + 1] - nzCount[l];
            if (k > 0) {
                long x = (prefX[r + 1] - (prefX[l] * pow10[k]) % MOD + MOD) % MOD;
                ans[i] = (int) ((x * (prefSum[r + 1] - prefSum[l])) % MOD);
            }
        }
        return ans;
    }
}
