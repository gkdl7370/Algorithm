import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};
    static int n;
    static int map[][];
    static int visited[][];
    static int max = Integer.MIN_VALUE;
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        visited = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, dfs(i, j));
            }
        }

        System.out.print(max);
    }

    private static int dfs(int x, int y) {
        if (visited[x][y] != 0) {
            return visited[x][y];
        }

        visited[x][y] = 1; // 기본적으로 현재 위치에서 시작할 수 있는 최댓값은 1
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
            if (map[x][y] < map[nx][ny]) { // 이동 조건: 대나무가 더 많아야 함
                visited[x][y] = Math.max(visited[x][y], dfs(nx, ny) + 1);
            }
        }

        return visited[x][y];
    }
}
