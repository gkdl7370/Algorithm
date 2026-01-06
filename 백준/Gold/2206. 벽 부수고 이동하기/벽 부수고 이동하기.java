
import java.util.*;
import java.io.*;

public class Main {
    static int dx[] = {0,1,0,-1};
    static int dy[] = {1,0,-1,0};
    static int n, m;
    static int[][] map;
    static int[][][] dist;

    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //n*m행렬 최단경로 상하좌우 벽 한개까지 부시기 가능
        //시간복잡도 1000 * 1000 1000000 < 100000000
        //최단경로 bfs 사용
        //벽 부셨는지 안부셨는지 체크  = 방문체크 3차원으로 작성 [x] [y] [벽뿌 확인]

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        dist = new int[n][m][2];

        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<m; j++){
                map[i][j] = str.charAt(j) - '0';
                dist[i][j][0] = -1;
                dist[i][j][1] = -1;
            }
        }

        int result = broken();


        System.out.print(result);
    }

    private static int broken(){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,0}); //처음에는 벽을 부실필요 없으니 0으로 입력
        dist[0][0][0] = 1; //시작점도 포함한다고 문제에 나옴

        while(!q.isEmpty()){
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            int brk = poll[2]; //벽뿌 유무
            int cost = dist[x][y][brk];

            if(x == n-1 && y == m-1) {
                return cost;
            }

            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;

                //방문체크
                // 벽을 부시고 온 경우
                if(brk == 1 && map[nx][ny] == 1) continue; //벽을 부셨으면 다음 벽이면 못감
                else if(brk == 1 && map[nx][ny] == 0){ //벽을 부셨는데 다음 벽 아니면
                    if(dist[nx][ny][1] == -1){ //벽 부셔서 와본적 없으면
                        dist[nx][ny][1] = cost + 1;
                        q.add(new int[]{nx,ny,1});
                    }
                }
                // 벽을 안부시고 온 경우
                if(brk == 0 && map[nx][ny] == 1){ //벽을 안부셨는데 벽이다
                    if (dist[nx][ny][1] == -1) { //벽을 부시고 방문한적이 없으면
                        dist[nx][ny][1] = cost + 1;
                        q.add(new int[]{nx,ny,1});
                    }
                } else if(brk == 0 && map[nx][ny] == 0){ //벽 안부셨는데 벽이 아니다
                    if (dist[nx][ny][0] == -1) { //벽을 부시고 방문한적이 없으면
                        dist[nx][ny][0] = cost + 1;
                        q.add(new int[]{nx,ny,0});
                    }
                }
            }
        }
        return -1;
    }
}
