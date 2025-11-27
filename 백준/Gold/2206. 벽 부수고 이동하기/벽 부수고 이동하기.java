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
        vit = new int[n][m][2]; // 0벽 안부신 상태 1벽 부신 상태 확인용

        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<m; j++){
                map[i][j] = str.charAt(j) - '0';
                vit[i][j][0] = -1;
                vit[i][j][1] = -1;
            }
        }

        int val = bfs();
        System.out.println(val);
    }

    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,0}); //초기 상태
        vit[0][0][0] = 1;
        vit[0][0][1] = 1;

        while (!q.isEmpty()){
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            int broken = poll[2];

            if(x == n-1 && y == m-1) return vit[x][y][broken];

            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx<0||ny<0||nx>=n||ny>=m) continue; // 범위 체크

                //벽인경우
                if(map[nx][ny] == 1){
                    if(broken == 0){ //벽 안부신상태여야 부실수있으니까
                        if(vit[nx][ny][1] == -1){ //벽 부시고 도착한적이 없는지 확인
                            vit[nx][ny][1] = vit[x][y][0] + 1; //이제 벽을 부시니까 전값은 무조건 안부신 상태의 값에 +1
                            q.add(new int[]{nx,ny,1});
                        }
                    }
                }

                //벽이아닌경우
                if(map[nx][ny] == 0){
                    if(vit[nx][ny][broken] == -1){ //처음와봤으면 broken유무는 안중요함 벽을 안부실거니까
                        vit[nx][ny][broken] = vit[x][y][broken] + 1;
                        q.add(new int[]{nx,ny,broken});
                    }
                }
            }

        }

        return -1;
    }
}

