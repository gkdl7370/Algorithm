import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int nx[] = {0,0,-1,1};//동서남북
    static int ny[] = {1,-1,0,0};
    static char[][] map;
    static boolean[][] vit1;
    static boolean[][] vit2;
    static int val = 0;
    static int n, m;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int arr[][] = new int[2][2];
        map = new char[n][m];
        vit1 = new boolean[n][m];
        vit2 = new boolean[n][m];

        int ch = 0;
        for(int i=0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<m; j++){
                map[i][j] = str.charAt(j);

                if(map[i][j] == 'o'){
                    arr[ch] = new int[]{i, j};
                    ch++;
                }
            }
        }

        bfs(arr);

        System.out.println(val);
    }

    private static void bfs(int[][] arr) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{arr[0][0], arr[0][1], arr[1][0], arr[1][1], 0});

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int x1 = now[0], y1 = now[1];
            int x2 = now[2], y2 = now[3];
            int count = now[4];

            if (count >= 10) {
                val = -1;
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx1 = x1 + nx[i];
                int ny1 = y1 + ny[i];
                int nx2 = x2 + nx[i];
                int ny2 = y2 + ny[i];

                int fall = 0;

                if (nx1 < 0 || ny1 < 0 || nx1 >= n || ny1 >= m) fall++;
                if (nx2 < 0 || ny2 < 0 || nx2 >= n || ny2 >= m) fall++;

                if (fall == 1) {
                    val = count + 1;
                    return;
                }
                if (fall == 2) continue;

                // 벽 체크
                if (map[nx1][ny1] == '#') {
                    nx1 = x1;
                    ny1 = y1;
                }
                if (map[nx2][ny2] == '#') {
                    nx2 = x2;
                    ny2 = y2;
                }

                q.add(new int[]{nx1, ny1, nx2, ny2, count + 1});
            }
        }

        val = -1; // 못 떨어뜨리면
    }
}
