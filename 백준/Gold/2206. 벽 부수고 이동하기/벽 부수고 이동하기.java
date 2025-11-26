import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,1,0,-1};
    static int n,m;
    static int map[][];
    static int vit[][][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        vit = new int[n][m][2];


        for(int j=0; j<n; j++){
            String str = br.readLine();
            for(int k=0; k<m; k++){
                map[j][k] = str.charAt(k) -'0';
                vit[j][k][1] = -1;
                vit[j][k][0] = -1;
            }
        }

        int val = bfs();


        System.out.println(val);
    }

    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,0});
        vit[0][0][0] =  1;

        while (!q.isEmpty()){
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            int broken = poll[2];

            if(x == n-1 && y == m-1) return vit[x][y][broken];

            for(int i=0; i<4; i++){
                int nx = x +dx[i];
                int ny = y +dy[i];

                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
               
                if(map[nx][ny] == 0){ //벽이 아닐때
                    if(vit[nx][ny][broken] == -1){ //처음와보는거면
                        vit[nx][ny][broken] = vit[x][y][broken] + 1;
                        q.add(new int[]{nx,ny,broken});
                    }
                }
                if(map[nx][ny] == 1){ //벽일때
                    if(broken == 0){ //벽뿌 기회가 있다면
                        if(vit[nx][ny][1] == -1){//처음 와봤다면 벽을 부수고
                            vit[nx][ny][1] = vit[x][y][0] + 1;
                            q.add(new int[]{nx,ny,1});
                        }
                    }
                }
            }

        }
        return -1;
    }


}

