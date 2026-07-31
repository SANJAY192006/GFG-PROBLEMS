class Solution {
    public int maxProduct(int n) {
      int first = 0;
      int second = 0;
      while(n>0){
        int digits = n%10;
        if(digits >= first){
            second = first;
            first = digits;
        }
        else if(digits > second){
            second = digits;
        }
        n /= 10;
      }  
      return first * second;
    }
}
