
import java.util.*;
import java.io.*;
public class Main {
    static int n, m;
    static int map[];
    static boolean vit[][];
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[m];
        vit = new boolean[m][n+1];

        dfs(0,1);
        System.out.print(sb);
    }
    static void dfs(int dep, int now){

        if(dep == m){
            for(int i=0; i<m; i++){
                sb.append(map[i] + " ");
            }
            sb.append('\n');
            return;
        }
        for(int i=now; i<=n; i++){
            if(vit[dep][i] == true) continue;
            vit[dep][i] = true;
            map[dep] = i;
            dfs(dep+1, i);
            vit[dep][i] = false;
        }
    }
}
