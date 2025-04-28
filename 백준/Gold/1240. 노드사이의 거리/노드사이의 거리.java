import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n,m, val;
    static int map[][];
    static boolean vit[];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];


        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[a][b] = c;
            map[b][a] = c;
        }

        for(int k=0; k<m; k++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            vit = new boolean[n+1];
            val = Integer.MAX_VALUE;
            dfs(start, end, 0);

            System.out.println(val);
        }

    }

    private static void dfs(int start, int end, int cnt) {
        if(val < cnt) return;

        if(start == end){
            val = cnt;
            return;
        }

        for(int i=1; i<=n; i++){
            if(map[start][i] != 0){
                if(vit[i] == true) continue;
                vit[start] = true;
                dfs(i,end,cnt + map[start][i]);
                vit[start] = false;
            }
        }
    }
}
