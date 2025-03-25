import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int nx[] = {0,0,-1,1};//동서남북
    static int ny[] = {1,-1,0,0};
    static int n,m;
    static int map[][];
    static boolean vit[][];
    static List<int[]> virus;
    static int area[];
    static int val = Integer.MAX_VALUE;
    static int check = 0;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][n];
        virus = new ArrayList<>();
        area = new int[m];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                int a = Integer.parseInt(st.nextToken());

                map[i][j] = a;

                if(a == 2){
                    virus.add(new int[]{i,j});
                }
                if(a != 1){
                    check++;
                }
            }
        }

        virusLocation(0,0);

        if(val == Integer.MAX_VALUE){
            System.out.println(-1);
        } else{
            System.out.println(val-1);
        }

    }

    private static void virusLocation(int start, int cnt) {
        if(cnt == m){
            vit = new boolean[n][n];
            int a = bfs();
            if(a != -1){
                val = Math.min(val,a);
            }
            return;
        }

        for(int i=start; i<virus.size(); i++){
            area[cnt] = i;
            virusLocation(i+1,cnt+1);
        }
    }

    private static int bfs() {
        Queue<int[]> q = new LinkedList<>();
        int chk = check; //남은 공간 체크

        for(int i=0; i<area.length; i++){
            int[] temp = virus.get(area[i]);

            q.add(new int[]{temp[0],temp[1]});
            vit[temp[0]][temp[1]] = true;
            chk--; //바이러스 선언된곳은 공간 삭제
        }

        int count = m; //루틴 돌리기 위한 변수
        int time = 0; //시간 변수

        while (!q.isEmpty()){
            int[] poll = q.poll();
            int x = poll[0];
            int y = poll[1];

            for(int i=0; i<4; i++){
                int dx = x + nx[i];
                int dy = y + ny[i];

                if(dx<0 || dy<0 || dx>=n || dy>=n) continue;
                if(vit[dx][dy] == true) continue;
                if(map[dx][dy] == 1) continue;

                q.add(new int[]{dx,dy});
                map[dx][dy] = 2;
                vit[dx][dy] = true;

                chk--; //바이러스를 퍼뜨렸으니 바이러스 공간 삭제제
            }
            count--; //현재 위치에서 4바퀴 돌려서 바이러스  찾았으면 루틴 하나 감소
            if(count == 0){ //루틴 끝났으면
                time++; //시간 올려주고
                count = q.size(); //다시 루틴 시작

            }
        }

        if(chk != 0){
            time = -1;
        }

        return time;
    }
}
