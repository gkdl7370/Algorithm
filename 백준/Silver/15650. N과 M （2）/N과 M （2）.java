
import java.util.*;
import java.io.*;
public class Main {
    static int n,m;
    static boolean vit[];
    static int map[];
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[m];
        vit = new boolean[n+1];

        dfs(0, 1);

    }

    private static void dfs(int dep, int now){
        if(dep == m){
            for(int i=0; i<m; i++){
                System.out.print(map[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i=now; i<=n; i++){
            map[dep] = i;
            dfs(dep+1, i+1);
        }

    }
}
