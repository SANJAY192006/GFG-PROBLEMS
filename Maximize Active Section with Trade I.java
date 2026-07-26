class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int ones = 0;
        for(char c : s.toCharArray())
            if(c == '1')
            ones++;
        s = "1"+s+"1";
        int n = s.length();
        int i = 0;
        int ans = ones;
        while(i<n && s.charAt(i) == '1')
            i++;
            int c1 = 0;
            while(i<n && s.charAt(i) == '0'){
                c1++;
                i++;
            }
            while (i<n){
                int c2 = 0;
                while(i<n && s.charAt(i) == '1'){
                    c2++;
                    i++;
                }
                if(c2 == 0)
                break;
                int c3 = 0;
                while(i<n && s.charAt(i) == '0'){
                    c3++;
                    i++;
                }
                if(c3 == 0)
                break;
                ans = Math.max(ans, ones + c1 + c3);
                c1 = c3;
            }
            return ans;
        }
    
}
