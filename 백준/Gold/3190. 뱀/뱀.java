
import java.util.*;
import java.io.*;
public class Main {
    // 북(0), 동(1), 남(2), 서(3)
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int n, k, l;
    static int[][] map;
    static Queue<String[]> move = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        map = new int[n][n];

        // 1. 사과 위치 입력 (문제는 1,1부터 시작하므로 1씩 빼줌)
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            map[r][c] = 9; // 사과는 9로 표시
        }

        // 2. 방향 전환 정보 입력
        l = Integer.parseInt(br.readLine());
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            String x = st.nextToken();
            String c = st.nextToken();
            move.add(new String[]{x, c});
        }

        System.out.println(solve());
    }

    private static int solve() {
        boolean[][] isBody = new boolean[n][n];
        Queue<int[]> body = new LinkedList<>();
        body.add(new int[]{0,0});
        isBody[0][0] = true;
        int x = 0;
        int y = 0;
        int head = 1;
        int time = 0;
        while(true){
            time++;
            int nx = x + dx[head];
            int ny = y + dy[head];

            if(nx<0 || ny<0 || nx>=n || ny>=n) return time;
            if(isBody[nx][ny]) return time;

            isBody[nx][ny] = true;
            body.add(new int[]{nx,ny});

            if(map[nx][ny] == 9){
                map[nx][ny] = 0;
            } else{
                int[] tail = body.poll();
                isBody[tail[0]][tail[1]] = false;
            }

            if(!move.isEmpty()){
                String now[] = move.peek();
                int moveTime = Integer.parseInt(now[0]);
                if(moveTime == time){
                    String turn = now[1];
                    if(turn.equals("D")){
                        head = (head+1)%4;
                    } else{
                        head = (head+3)%4;
                    }
                    move.poll();
                }

            }
            x = nx;
            y = ny;
        }

    }

}
