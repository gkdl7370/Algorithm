import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,1,0,-1};
    static int n,m,c;
    static int zero = 0;
    static int map[][][];
    static boolean vit[][][];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        map = new int[c][n][m];
        vit = new boolean[c][n][m];

        Queue<int[]> q = new LinkedList<>();


        for(int i=0; i<c; i++){
            for(int j=0; j<n; j++){
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<m; k++){
                    map[i][j][k] = Integer.parseInt(st.nextToken());
                    if(map[i][j][k] == 1){
                        q.add(new int[]{i,j,k});
                        vit[i][j][k] = true;
                    }
                    if(map[i][j][k] == 0){
                        zero++;
                    }
                }
            }
        }

        if(zero == 0){
            System.out.println(0);
            return;
        }


        int val = bfs(q);


        if(zero == 0) System.out.println(val);
        else System.out.println(-1);


    }

    private static int bfs(Queue<int[]> q) {
        int time = 0;

        while (!q.isEmpty()){
            if(zero == 0) {
                return time;
            }

            time++;
            int repeat = q.size();



            for(int t=0; t<repeat; t++){
                int[] poll = q.poll();
                int h = poll[0];
                int x = poll[1];
                int y = poll[2];



                for(int i=0; i<4; i++){
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if(nx<0 || ny<0 || nx>=n || ny>=m) continue;
                    if(map[h][nx][ny] == -1)continue;
                    if(vit[h][nx][ny] == true)continue;

                    map[h][nx][ny] = 1;
                    zero--;
                    q.add(new int[]{h,nx,ny});
                    vit[h][nx][ny] = true;


                }
                if(h-1>=0){ //아래칸 있으면
                    if(map[h-1][x][y] != -1){
                        if(vit[h-1][x][y] != true){
                            map[h-1][x][y] = 1;
                            zero--;
                            q.add(new int[]{h-1,x,y});
                            vit[h-1][x][y] = true;
                        }
                    }

                }
                if(h+1<c){ //윗칸 있으면
                    if(map[h+1][x][y] != -1) {
                        if(vit[h+1][x][y] != true) {
                            map[h+1][x][y] = 1;
                            zero--;
                            q.add(new int[]{h+1,x,y});
                            vit[h+1][x][y] = true;

                        }
                    }
                }

            }
        
        }


        return -1;
    }


}

