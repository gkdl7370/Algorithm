import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,1,0,-1};

    static int n,m,v;
    static int map[][];
    static boolean vit[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        map = new int[n+1][n+1];
        vit = new boolean[n+1];

        for(int i=1; i<=m; i++){
            st = new StringTokenizer(br.readLine());
              int x = Integer.parseInt(st.nextToken());
              int y = Integer.parseInt(st.nextToken());
              map[x][y] = 1;
              map[y][x] = 1;

        }
        int count = 0;
        v = 0;
        dfs(1);

        System.out.print(v);
    }

    private static void dfs(int start) {
        vit[start] = true;

        for(int i=1; i<=n; i++){
            if(map[start][i]==1){
                if(vit[i] == false){
                    v += 1;
                    dfs(i);
                }
            }
        }

    }

}

