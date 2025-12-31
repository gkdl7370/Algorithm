
import java.util.*;
import java.io.*;
public class Main {
    static int n,m;
    static boolean vit[];
    static int map[];
    static int val[];
    static StringBuilder sb = new StringBuilder();
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n];
        val = new int[m];
        vit = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            map[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(map);

        dfs(0);

        System.out.print(sb);
    }

    private static void dfs(int dep){
        if(dep == m){
            for(int i=0; i<m; i++){
                sb.append(val[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for(int i=0; i<n; i++){
            if(vit[i] == true) continue;
            val[dep] = map[i];
            vit[i] = true;
            dfs(dep+1);
            vit[i] = false;
        }

    }
}
