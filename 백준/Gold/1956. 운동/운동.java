import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 플로이드 워샬 예제
    public static int V, N, a, b, c, answer, max;
    public static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        arr = new int[V + 1][V + 1];
        max = 1000000000;

        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                arr[i][j] = max;
            }
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()); // start
            b = Integer.parseInt(st.nextToken()); // end
            c = Integer.parseInt(st.nextToken()); // cost
            arr[a][b] = c;
        }
        // 플로이드 워샬
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if(i == j) continue;
                    arr[i][j] = Math.min(arr[i][k] + arr[k][j], arr[i][j]);
                }
            }
        }
        answer = max;
        // 사이클을 찾아준다.
        for (int i = 1; i <= V; i++) { // 시작점
            for (int j = 1; j <= V; j++) { // 끝점
                if(i == j) continue;
                if (arr[i][j] != max && arr[j][i] != max) {
                    answer = Math.min(answer, arr[i][j] + arr[j][i]);
                }
            }
        }

        if (answer == max) {
            answer = -1;
        }
        System.out.println(answer);
    }
}

