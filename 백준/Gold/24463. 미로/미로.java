import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static char[][] map;
    static boolean[][] visited;
    static ArrayList<int[]> path = new ArrayList<>();
    static boolean found = false;
    static int startX, startY, endX, endY;

    static int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new char[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String row = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = row.charAt(j);
                if(map[i][j] == '.'){
                    map[i][j] = '@';
                }
            }
        }

        ArrayList<int[]> entries = new ArrayList<>();

        // 상단과 하단
        for (int j = 0; j < m; j++) {
            if (map[0][j] == '@') entries.add(new int[]{0, j});
            if (map[n - 1][j] == '@') entries.add(new int[]{n - 1, j});
        }

        // 좌측과 우측
        for (int i = 1; i < n - 1; i++) { // 이미 위아래는 처리했으므로 1~n-2만 검사
            if (map[i][0] == '@') entries.add(new int[]{i, 0});
            if (map[i][m - 1] == '@') entries.add(new int[]{i, m - 1});
        }

        if (entries.size() != 2) {
            System.out.println("입구와 출구는 정확히 2개여야 합니다.");
            return;
        }

        startX = entries.get(0)[0];
        startY = entries.get(0)[1];
        endX = entries.get(1)[0];
        endY = entries.get(1)[1];

        dfs(startX, startY);

        for(int i=0; i<path.size(); i++){
            int[] a = path.get(i);
            map[a[0]][a[1]] = '.';
        }
        map[endX][endY] = '.';
        for (int i = 0; i < n; i++) {
            System.out.println(new String(map[i]));
        }
    }

    static void dfs(int x, int y) {
        if(found == true) return; // 도착 체크 되어있으면 함수 빠져나가기

        if(x == endX && y == endY){
            found = true; //도착하면 체크
        }

        visited[x][y] = true; //방문 체크

        path.add(new int[]{x,y}); // 경로 저장

        for(int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(nx<0 || ny<0 || nx>=n || ny>=m) continue; //범위 벗어나는거 처리
            if(visited[nx][ny] == true) continue; //중복계산 안되게 처리
            if(map[nx][ny] == '+') continue; //벽이면 처리

            dfs(nx,ny);
            if(found == true) return;

        }

        path.remove(path.size() - 1); //길을 못찾은경우 도착지까지 가는 길이 아니기 때문에 지운다
    }
}