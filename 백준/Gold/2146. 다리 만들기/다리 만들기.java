import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 1, -1, 0};
    static int[] dy = {1, 0, 0, -1};
    static int n;
    static int[][] map;
    static boolean[][] visited;

    static class Point {
        int x, y, dist, region;

        Point(int x, int y, int dist, int region) {
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.region = region;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // Step 1: 섬 구분
        int region = 2;
        visited = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 1 && !visited[i][j]) {
                    bfsMarkIsland(i, j, region);
                    region = region + 1;
                }
            }
        }


        // Step 2: 다리 길이 계산
        int minDist = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] > 1) {
                    visited = new boolean[n][n];
                    minDist = Math.min(minDist, bfsFindBridge(i, j, map[i][j]));
                }
            }
        }

        System.out.println(minDist);
    }

    static void bfsMarkIsland(int x, int y, int region) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y, 0, region));
        visited[x][y] = true;
        map[x][y] = region;

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny] && map[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    map[nx][ny] = region;
                    q.add(new Point(nx, ny, 0, region));
                }
            }
        }
    }

    static int bfsFindBridge(int x, int y, int region) {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(x, y, 0, region));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < n && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    if (map[nx][ny] == 0) {
                        q.add(new Point(nx, ny, p.dist + 1, region));
                    } else if (map[nx][ny] != region) {
                        return p.dist;
                    }
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}
