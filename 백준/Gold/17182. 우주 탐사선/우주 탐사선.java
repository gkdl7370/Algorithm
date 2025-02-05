import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m;
    static int map[][];
    static boolean visited[];
    static int minCost = Integer.MAX_VALUE;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];
        visited = new boolean[n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int k=0; k<n; k++){
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(map[i][j] > map[i][k] + map[k][j]){
                        map[i][j] = map[i][k] + map[k][j];
                    }
                }
            }
        }
        visited[m] = true;
        dfs(m,1,0);

        System.out.println(minCost);
    }

    static void dfs(int start, int count, int cost) {
        if(count == n){
            minCost = Math.min(cost, minCost);
        }

        for(int i=0; i<n; i++){
            if(visited[i] == true) continue;
            visited[i] = true;
            dfs(i,count+1, cost + map[start][i]);
            visited[i] = false;
        }
    }
}