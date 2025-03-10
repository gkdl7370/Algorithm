class Solution {
    int solution(int[][] land) {
         int n = land.length;
        int m = land[0].length;
        for(int i=1; i<n; i++){
            for(int j=0; j<m; j++){
                int num = 0;
                
                for(int k=0; k<m; k++){
                    if(k != j) num = Math.max(num, land[i-1][k]);
                }


                land[i][j] += num;
            }
        }
        int answer = 0;
        for (int j = 0; j < m; j++) {
            answer = Math.max(answer, land[n - 1][j]);
        }

        return answer;
    }
}