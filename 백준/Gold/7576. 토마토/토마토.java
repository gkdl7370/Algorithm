import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,1,0,-1};
    static int n,m;
    static int tomato;
    static int map[][];
    static boolean vit[][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        vit = new boolean[n][m];

        Queue<int[]> q = new LinkedList<>();
        tomato = 0;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                int a = Integer.parseInt(st.nextToken());
                map[i][j] = a;
                if(a == 1) {
                    q.add(new int[]{i,j}); //토마토 위치 저장
                    vit[i][j] = true;
                }
                if(a == 0) tomato++;

            }
        }

        int val = bfs(q);

        if(tomato == 0){
            System.out.print(val);
        }
        else System.out.print(-1);
    }

    private static int bfs(Queue<int[]> q) {
        int time = 0;
        while (!q.isEmpty()){
            if(tomato == 0) break;

            time++; //시간 체크
            int num = q.size();

            for(int k=0; k<num; k++){
                int[] poll = q.poll();
                int x = poll[0];
                int y = poll[1];

                for(int i=0; i<4; i++){
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                    if(vit[nx][ny] == true) continue;
                    if(map[nx][ny] == -1 || map[nx][ny] == 1) continue;

                    q.add(new int[]{nx,ny});
                    vit[nx][ny] = true;
                    map[nx][ny] = 1;
                    tomato--;

                }
            }


        }



        return time;
    }
}

