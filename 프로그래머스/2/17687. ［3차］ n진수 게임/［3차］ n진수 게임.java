class Solution {
    public String solution(int n, int t, int m, int p) {
       String answer = "";

        String vlaue = "";
        int i = p-1;
        int c = 0;
        while (vlaue.length() < t){
                
            String a = Integer.toString(c,n);

            a = a.toUpperCase();

            answer += a;
            c++;
            
            if(i<answer.length()){
                vlaue += answer.charAt(i);
                i+=m;
            }
        }

        return vlaue;
    }

}
