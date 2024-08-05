class Solution {
    static int answer;
    static boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
         answer = -1;
        visited = new boolean[dungeons.length];

        dfs(0, k, dungeons);

        return answer;
    }
    
        public static void dfs(int count, int k, int[][] dungeons) {
        for (int i=0; i<dungeons.length; i++){
            if(visited[i] != true && dungeons[i][0]<=k){
                visited[i] = true;
                k = k - dungeons[i][1];
                dfs(count+1, k, dungeons);
                k = k + dungeons[i][1];
                visited[i] = false;
            }
        }

        answer = Math.max(answer,count);

    }
}