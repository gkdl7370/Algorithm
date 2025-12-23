
import java.util.*;
import java.io.*;
public class Main {
    static int n, m;
    static int map[];
    static int vit[];
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[m+1];
        vit = new int[n+1];

        dfs(0,1);
    }

    static void dfs(int depth, int now){
        if(depth == m){
            for(int i=0; i<m; i++){
                System.out.print(map[i] + " ");
            }
            System.out.println();
            return;
        }

        for(int i=now; i<=n; i++){
            if(vit[i] == 1) continue;
            map[depth] = i;
            vit[i] = 1;
            dfs(depth+1,i);
            vit[i] = 0;
        }
    }
}
