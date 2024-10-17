import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] arr1 = {0, 1, -1, 0};
    static int[] arr2 = {1, 0, 0, -1};
    static int n, m;
    static int[][] map;
    static int[][] visited;  // 경로를 저장하는 배열 (메모이제이션)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new int[n][m];

        // 방문 여부 배열을 -1로 초기화 (방문하지 않은 곳을 표시)
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                visited[i][j] = -1;  // 방문하지 않았음을 표시
            }
        }

        System.out.println(dfs(0, 0));
    }

    // DFS를 메모이제이션 방식으로 구현
    private static int dfs(int x, int y) {
        // 목적지에 도달한 경우 1을 반환
        if (x == n - 1 && y == m - 1) {
            return 1;
        }

        // 이미 계산된 경로가 있다면 그 값을 반환 (메모이제이션)
        if (visited[x][y] != -1) {
            return visited[x][y];
        }

        int count = 0;

        // 4방향 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + arr1[i];
            int ny = y + arr2[i];

            // 배열 범위를 벗어난 경우 건너뛴다
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

            // 내리막길이 아닌 경우 건너뛴다
            if (map[nx][ny] >= map[x][y]) continue;

            // 경로를 탐색하고 경로 수를 더한다
            count += dfs(nx, ny);
        }

        // 계산된 결과를 저장하고 반환
        visited[x][y] = count;
        return count;
    }
}