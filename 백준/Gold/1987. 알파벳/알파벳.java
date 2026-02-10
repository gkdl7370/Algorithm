
import java.util.*;
import java.io.*;
public class Main {
    static int[] dx = {-1,0,1,0}; //북0 동1 남2 서3
    static int[] dy = {0,1,0,-1};
    static int r,c, val;
    static char map[][];
    static boolean vit[][];
    static boolean ap[] = new boolean[26];
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        map = new char[r][c];
        vit = new boolean[r][c];

        for(int i=0; i<r; i++){
            String str = br.readLine();
            for(int j=0; j<c; j++){
                map[i][j] = str.charAt(j);
            }
        }

        val = 1;
        vit[0][0] = true;
        ap[map[0][0] - 65] = true;
        int result = dfs(0,0,1); //depth, sum

        System.out.print(result);
    }

    public static int dfs(int x, int y, int sum){

        if(val < sum){
            val = sum;
        }

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0 || ny<0 || nx>=r || ny>=c) continue;
            if(vit[nx][ny] == true) continue;
            if(ap[map[nx][ny] - 65] == true) continue;

            ap[map[nx][ny] - 65] = true;
            vit[nx][ny] = true;
            dfs(nx, ny, sum+1);

            ap[map[nx][ny] - 65] = false;
            vit[nx][ny] = false;
        }

        return val;
    }
}
