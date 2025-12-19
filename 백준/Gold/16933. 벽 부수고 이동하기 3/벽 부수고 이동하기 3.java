
import java.util.*;
import java.io.*;
public class Main {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n,m,k;
    static int map[][];
    static int broken[][];
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        broken = new int[n][m]; //해당 좌표에 벽 몇번부시고 도착했는지 확인용

        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<m; j++){
                map[i][j] = str.charAt(j) -'0';
                broken[i][j] = Integer.MAX_VALUE;
            }
        }

        int result = bfs();

        System.out.print(result);
    }

    private static int bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,0,1,0}); //x좌표 y좌표 벽뿌횟수, 거리, 낮0/밤1
        broken[0][0] = 0;

        while(!q.isEmpty()){
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];
            int brk = poll[2];
            int dist = poll[3];
            int sun = poll[4];

            if(x == n-1 && y == m-1) return dist;

            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx<0 || ny<0 || nx>=n || ny>=m) continue;

                //벽이 아닌경우
                if(map[nx][ny] == 0){
                    if (broken[nx][ny] > brk) {
                        broken[nx][ny] = brk;
                        if(sun == 0){
                            q.add(new int[]{nx, ny, brk, dist + 1, 1});
                        } else{
                            q.add(new int[]{nx, ny, brk, dist + 1, 0});
                        }

                    }
                }
                //벽인 경우
                else{
                    if(sun == 0 && brk < k){ //낮이면서 벽 부실수있으면
                        if(broken[nx][ny] > brk +1){
                            broken[nx][ny] = brk + 1;
                            q.add(new int[]{nx, ny, brk + 1, dist + 1, 1});
                        }
                    } else if(sun == 1 && brk < k){ //밤이면서 벽 부실수있으면
                        q.add(new int[]{x, y, brk, dist + 1, 0});
                    }
                }


            }
        }

        return -1;
    }
}
