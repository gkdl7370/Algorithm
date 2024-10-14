import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int min;
    static List<Integer[]> list = new LinkedList<>();
    private static ArrayList<Stack<int[]>> cameraHistories = new ArrayList<>();
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];


        for (int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0 && map[i][j] != 6) {
                    list.add(new Integer[]{i,j,map[i][j]});
                }
            }
        }

        min = Integer.MAX_VALUE;

        dfs(0);

        System.out.println(min);
    }

    private static void dfs(int cameraIndex) {
        if (cameraIndex == list.size()) {  // 모든 카메라 처리 완료
            int count = 0;
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    if (map[i][j] == 0) {
                        count++;
                    }
                }
            }
            min = Math.min(min, count);  // 최소 사각지대 업데이트

            return;
        }

        // 현재 카메라 좌표 및 정보
        Integer[] camera = list.get(cameraIndex);
        int x = camera[0];
        int y = camera[1];
        int type = camera[2];

        // 카메라별 기록 스택 추가
        cameraHistories.add(new Stack<>());

        if (type == 1) {  // 1번 카메라
            for (int i = 0; i < 4; i++) {  // 4방향 모두 확인
                observe(cameraIndex, x, y, i);  // 감시 영역 설정
                dfs(cameraIndex + 1);  // 다음 카메라로 이동
                unobserve(cameraIndex);  // 감시 영역 복원
            }
        } else if (type == 2) {  // 2번 카메라 (양방향)
            for (int i = 0; i < 2; i++) {
                observe(cameraIndex, x, y, i);  // 첫 번째 방향
                observe(cameraIndex, x, y, i + 2);  // 반대 방향
                dfs(cameraIndex + 1);
                unobserve(cameraIndex);
            }
        } else if (type == 3) {  // 3번 카메라 (직각)
            for (int i = 0; i < 4; i++) {
                observe(cameraIndex, x, y, i);  // 첫 번째 방향 0동 1남 2서 3북
                observe(cameraIndex, x, y, (i + 1) % 4);  // 직각 방향 1남 2서 3북 0동
                dfs(cameraIndex + 1);
                unobserve(cameraIndex);
            }
        } else if (type == 4) {  // 4번 카메라 (세 방향)
            for (int i = 0; i < 4; i++) {
                observe(cameraIndex, x, y, i);  // 첫 번째 방향
                observe(cameraIndex, x, y, (i + 1) % 4);  // 두 번째 방향
                observe(cameraIndex, x, y, (i + 2) % 4);  // 세 번째 방향
                dfs(cameraIndex + 1);
                unobserve(cameraIndex);
            }
        } else if (type == 5) {  // 5번 카메라 (네 방향 모두)
            for (int i = 0; i < 4; i++) {
                observe(cameraIndex, x, y, i);  // 모든 방향 감시
            }
            dfs(cameraIndex + 1);
            unobserve(cameraIndex);
        }
    }

    // 감시 영역 설정 (카메라 번호별로 기록)
    private static void observe(int cameraIndex, int x, int y, int direction) {
        Stack<int[]> history = cameraHistories.get(cameraIndex);  // 현재 카메라의 스택

        if (direction == 0) {  // 동쪽
            for (int k = y; k < m; k++) {
                if (map[x][k] == 6) break;  // 벽을 만나면 중단
                if (map[x][k] == 0) {
                    map[x][k] = 9;
                    history.push(new int[]{x, k});  // 감시 영역 기록
                }
            }
        }else if (direction == 1) {  // 남쪽
            for (int k = x; k < n; k++) {
                if (map[k][y] == 6) break;
                if (map[k][y] == 0) {
                    map[k][y] = 9;
                    history.push(new int[]{k, y});
                }
            }
        }  else if (direction == 2) {  // 서쪽
            for (int k = y; k >= 0; k--) {
                if (map[x][k] == 6) break;
                if (map[x][k] == 0) {
                    map[x][k] = 9;
                    history.push(new int[]{x, k});
                }
            }
        } else if (direction == 3) {  // 북쪽
            for (int k = x; k >= 0; k--) {
                if (map[k][y] == 6) break;
                if (map[k][y] == 0) {
                    map[k][y] = 9;
                    history.push(new int[]{k, y});
                }
            }
        }
    }

    // 감시 영역 복원 (카메라별로 복원)
    private static void unobserve(int cameraIndex) {
        Stack<int[]> history = cameraHistories.get(cameraIndex);  // 해당 카메라의 스택
        while (!history.isEmpty()) {
            int[] coord = history.pop();
            map[coord[0]][coord[1]] = 0;  // 복원
        }
    }
}
