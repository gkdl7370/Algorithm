import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int mongkey1[]={0,1,-1,0};
    static int mongkey2[]={1,0,0,-1};
    static int horse1[]={-2,-2,-1,-1,1,1,2,2};
    static int horse2[]={-1,1,-2,2,-2,2,-1,1};

    static int k;
    static int w, h;
    static int map[][];
    static boolean[][][] visited;
    static class Point {
        int x, y, cnt, len;

        public Point(int x, int y, int cnt, int len) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.len = len;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        map = new int[h][w];
        visited = new boolean[h][w][k + 1];

        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < w; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0, k, 0));
        visited[0][0][k] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            int x = p.x;
            int y = p.y;
            int cnt = p.cnt;
            int len = p.len;

            if (x == h - 1 && y == w - 1) return len;

            // 원숭이 이동
            for (int i = 0; i < 4; i++) {
                int nx = x + mongkey1[i];
                int ny = y + mongkey2[i];

                if (nx >= 0 && ny >= 0 && nx < h && ny < w && map[nx][ny] == 0 && !visited[nx][ny][cnt]) {
                    visited[nx][ny][cnt] = true;
                    q.add(new Point(nx, ny, cnt, len + 1));
                }
            }

            // 말 이동
            if (cnt > 0) {
                for (int i = 0; i < 8; i++) {
                    int nx = x + horse1[i];
                    int ny = y + horse2[i];

                    if (nx >= 0 && ny >= 0 && nx < h && ny < w && map[nx][ny] == 0 && !visited[nx][ny][cnt - 1]) {
                        visited[nx][ny][cnt - 1] = true;
                        q.add(new Point(nx, ny, cnt - 1, len + 1));
                    }
                }
            }
        }

        return -1;
    }
}