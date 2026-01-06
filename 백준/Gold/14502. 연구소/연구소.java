
import java.util.*;
import java.io.*;

public class Main{
    static int[] dx = {0,1,-1,0};
    static int[] dy = {1,0,0,-1};
    static int n, m, result = 0;
    static int map[][];
    static boolean vit[][];
    static ArrayList<int[]> virus;

    public static void main(String args[])throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        virus = new ArrayList<>();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                int num = Integer.parseInt(st.nextToken());
                if(num == 2) virus.add(new int[]{i,j});
                map[i][j] = num;
            }
        }

        wall(0,0);

        System.out.print(result);
    }

    private static void wall(int dep,int start){
        if(dep == 3){
            vit = new boolean[n][m];
            result = Math.max(result,search());
            return;
        }

        // 2차원 배열을 1차원적으로 탐색하여 중복 제거
        for (int i = start; i < n * m; i++) {
            int r = i / m;
            int c = i % m;

            if (map[r][c] == 0) {
                map[r][c] = 1;
                wall(dep + 1,i + 1);
                map[r][c] = 0;
            }
        }
    }

    private static int search(){
        Queue<int[]> q = new LinkedList<>();
        for(int i=0; i<virus.size(); i++){
            q.add(virus.get(i));
        }
        int val = 0;
        while(!q.isEmpty()){
            int poll[] = q.poll();

            int x = poll[0];
            int y = poll[1];
            vit[x][y] = true;

            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                if(map[nx][ny] == 1) continue;
                if(map[nx][ny] == 2) continue;
                if(vit[nx][ny] == true) continue;

                q.add(new int[]{nx,ny});
                vit[nx][ny] = true;

            }

        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(map[i][j] == 1) continue;
                if(vit[i][j] == true) continue;
                val++;
            }
        }
        return val;
    }

}
