
import java.util.*;
import java.io.*;
public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n, m, k;
    static int map[][];
    static int dist[][][];
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
        dist = new int[n][m][k+1];

        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<m; j++){
                map[i][j] = str.charAt(j) - '0';
                Arrays.fill(dist[i][j], -1); //방문기록 초기화
            }
        }

        int result = bfs();

        System.out.print(result);
    }

    private static int bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,0}); //x y 벽뿌
        dist[0][0][0] = 1; //초기값 셋팅

        while(!q.isEmpty()){
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            int brk = poll[2];
            int cost = dist[x][y][brk];

            if(x == n-1 && y == m-1) return dist[x][y][brk]; //탈출조건

            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;

                //벽이 아닌경우
                if(map[nx][ny] == 0){
                    if(dist[nx][ny][brk] != -1) continue; //해당위치를 온적이 있다면 패스
                    dist[nx][ny][brk] = cost + 1; //거리값 갱신해주고
                    q.add(new int[]{nx,ny,brk});
                }
                //벽인 경우
                else{
                    if(brk >= k) continue; //벽 부실수있는 기회가 없으면 더이상 못움직임
                    if(dist[nx][ny][brk+1] != -1) continue; //해당위치를 벽 부시고 온적이 있다면 패스
                    dist[nx][ny][brk+1] = cost + 1; //거리값 갱신해주고
                    q.add(new int[]{nx,ny,brk+1});
                }



            }
        }

        return -1;
    }
}
