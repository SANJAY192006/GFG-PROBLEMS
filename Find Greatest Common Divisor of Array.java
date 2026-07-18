class Solution {
    public int findGCD(int[] nums) {
        int n = 1001, m = 0;
        for(int num : nums){
            if(num < n)
                n = num;
            if(num > m)
                m = num;
          }
            return gcd(n , m);
        }
        private int gcd(int a, int b) {
            while( b != 0){
                int rem = a%b;
                a = b;
                b = rem;
            }
            return a;
        
    }
}
