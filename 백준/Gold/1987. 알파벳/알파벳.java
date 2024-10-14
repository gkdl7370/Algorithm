import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[] arr1 = {0, 1, 0, -1}; // 동, 남, 서, 북
    static int[] arr2 = {1, 0, -1, 0};
    static char[][] map;
    static boolean[][] visited; // 방문 체크 배열
    static Set<Character> usedAlphabets = new HashSet<>(); // 사용된 알파벳을 체크하는 Set
    static int max = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visited = new boolean[n][m]; // 방문 체크용 배열 초기화

        // 지도 입력 받기
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        // 시작 위치 (0, 0) 처리
        usedAlphabets.add(map[0][0]);
        visited[0][0] = true;

        // DFS 호출 (시작 위치와 카운트 1로 시작)
        dfs(0, 0, 1);

        // 결과 출력
        System.out.println(max);
    }

    // 깊이 우선 탐색 (DFS)
    private static void dfs(int x, int y, int count) {
        // 최대 카운트 갱신
        max = Math.max(max, count);

        // 네 방향 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + arr1[i];
            int ny = y + arr2[i];

            // 좌표 범위 체크
            if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;

            // 이미 방문한 알파벳이거나, 이미 방문한 좌표이면 패스
            if (visited[nx][ny] || usedAlphabets.contains(map[nx][ny])) continue;

            // 새로운 알파벳을 방문
            visited[nx][ny] = true;
            usedAlphabets.add(map[nx][ny]);

            // 다음 위치로 이동 (재귀 호출)
            dfs(nx, ny, count + 1);

            // 백트래킹: 상태 복원
            visited[nx][ny] = false;
            usedAlphabets.remove(map[nx][ny]);
        }
    }
}
