
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n, m, k;
    static int map[][];
    static int broken[][];
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //시간복잡도 기본 n*m*k 1000*1000*10 10^7
        //해결방안 - 최단거리BFS
        // 1.dist[][][]  x y 벽뿌횟수를 저장하는 3차원 배열  시간초과 나오면 2번으로 수정하자
        // 2.broken[][]  x y 좌표만 넣고 해당 위치에 벽뿌 횟수 저장 - 적은 벽뿌로 온게 제일 좋다고 생각

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        broken = new int[n][m];

        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<m; j++){
                map[i][j] = str.charAt(j) - '0';
                broken[i][j] = Integer.MAX_VALUE;
            }
        }

        int result = bfs();

        System.out.print(result);
    }

    private static int bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,0,1}); //x y 벽뿌 거리
        broken[0][0] = 0; //벽뿌 초기값 셋팅

        while(!q.isEmpty()){
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            int brk = poll[2];
            int dist = poll[3];

            if(x == n-1 && y == m-1) return dist;

            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;

                //벽이 아닌경우
                if(map[nx][ny] == 0){
                    if(broken[nx][ny] <= brk) continue; // 지금보다 벽뿌가 적은 횟수로 도착한적 있으면 굳이 안가도 된다
                    broken[nx][ny] = brk;
                    q.add(new int[]{nx,ny,brk,dist + 1}); //다음좌표 brk는 유지 거리는 1증가
                }
                //벽인 경우
                else{
                    if(brk >= k) continue;
                    if(broken[nx][ny] <= brk + 1) continue; // 현재 +1 보다 벽뿌가 적은 횟수로 도착한적 있으면 굳이 안가도 된다
                    broken[nx][ny] = brk + 1;
                    q.add(new int[]{nx,ny,brk + 1,dist + 1}); //다음좌표 brk 거리 1증가
                }

            }

        }

        return -1;
    }
}
