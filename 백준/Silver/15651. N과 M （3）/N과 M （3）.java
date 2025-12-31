
import java.util.*;
import java.io.*;
public class Main {
    static int n,m;
    static boolean vit[];
    static int map[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[m];
        vit = new boolean[n+1];

        dfs(0);

        System.out.print(sb);
    }

    private static void dfs(int dep){
        if(dep == m){
            for(int i=0; i<m; i++){
                sb.append(map[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i=1; i<=n; i++){
            map[dep] = i;
            dfs(dep+1);
        }

    }
}
