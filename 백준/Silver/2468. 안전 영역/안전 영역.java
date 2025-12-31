
import java.util.*;
import java.io.*;
public class Main {
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};
    static int n;
    static int map[][];
    static boolean vit[][];
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        int max = 0;
        int min = Integer.MAX_VALUE;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(max < map[i][j]) max = map[i][j];
                if(min > map[i][j]) min = map[i][j];
            }
        }
        //100*100*100 10^6
        int value;
        int count = 0;
        for(int wl=0; wl<=max; wl++){
            value = 0;
            vit = new boolean[n][n];
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    if(map[i][j] <= wl) continue;
                    if(vit[i][j] == true) continue;
                    dfs(i,j,wl);
                    value++;
                }
            }
            if(count<value) {
                count = value;
            }
        }

        System.out.println(count);
    }

    private static void dfs(int x, int y, int wl){
        vit[x][y] = true;

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0 || ny<0 || nx>=n || ny>=n) continue;
            if(vit[nx][ny] == true) continue;
            if(map[nx][ny]<=wl) continue;

            dfs(nx,ny,wl);
        }
    }
}
