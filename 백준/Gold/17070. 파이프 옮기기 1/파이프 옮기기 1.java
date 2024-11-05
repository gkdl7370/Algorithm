import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int map[][];
    static int n;
    static int count;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        count = 0;
        dfs(1, 2, 0); // 초기에는 (1,1)과 (1,2)에 파이프가 가로로 놓임

        System.out.println(count);
    }

    private static void dfs(int x, int y, int dir) {
        if (x == n && y == n) { // 목표 위치에 도달한 경우
            count++;
            return;
        }

        // 가로 방향
        if (dir == 0) {
            if (y + 1 <= n && map[x][y + 1] == 0) { // 가로로 이동
                dfs(x, y + 1, 0);
            }
            if (x + 1 <= n && y + 1 <= n && map[x][y + 1] == 0 && map[x + 1][y] == 0 && map[x + 1][y + 1] == 0) { // 대각선 이동
                dfs(x + 1, y + 1, 2);
            }
        }
        // 세로 방향
        else if (dir == 1) {
            if (x + 1 <= n && map[x + 1][y] == 0) { // 세로로 이동
                dfs(x + 1, y, 1);
            }
            if (x + 1 <= n && y + 1 <= n && map[x][y + 1] == 0 && map[x + 1][y] == 0 && map[x + 1][y + 1] == 0) { // 대각선 이동
                dfs(x + 1, y + 1, 2);
            }
        }
        // 대각선 방향
        else if (dir == 2) {
            if (y + 1 <= n && map[x][y + 1] == 0) { // 가로로 이동
                dfs(x, y + 1, 0);
            }
            if (x + 1 <= n && map[x + 1][y] == 0) { // 세로로 이동
                dfs(x + 1, y, 1);
            }
            if (x + 1 <= n && y + 1 <= n && map[x][y + 1] == 0 && map[x + 1][y] == 0 && map[x + 1][y + 1] == 0) { // 대각선 이동
                dfs(x + 1, y + 1, 2);
            }
        }
    }
}
