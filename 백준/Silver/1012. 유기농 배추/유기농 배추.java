
import java.util.*;
import java.io.*;
public class Main {
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};
    static int t,m,n;
    static int map[][];
    static boolean vit[][];
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        t = Integer.parseInt(br.readLine());
        for(int fn=0; fn<t; fn++){
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken()); //가로 y
            n = Integer.parseInt(st.nextToken()); //세로 x

            map = new int[n+1][m+1];
            vit = new boolean[n+1][m+1];

            int loc = Integer.parseInt(st.nextToken());

            for(int cn=0; cn<loc; cn++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y][x] = 1;
            }

            int value = 0;
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(map[i][j] == 0) continue;
                    if(vit[i][j] == false){
                        dfs(i,j);
                        value++;
                    }
                }
            }

            System.out.println(value);
        }

    }

    private static void dfs(int startX, int startY){
        vit[startX][startY] = true;

        for(int i=0; i<4; i++){
            int nx = startX + dx[i];
            int ny = startY + dy[i];

            if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
            if(vit[nx][ny] == true) continue;
            if(map[nx][ny] == 0) continue;

            dfs(nx,ny);
        }
    }
}
