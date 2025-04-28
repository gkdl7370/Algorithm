import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine()); // 노드 수
            int[] parent = new int[n + 1]; // 부모 저장
            boolean[] visited = new boolean[n + 1];

            for (int i = 0; i < n - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                parent[b] = a;
            }

            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            // node1의 모든 조상 표시
            while (node1 != 0) {
                visited[node1] = true;
                node1 = parent[node1];
            }

            // node2 조상 올라가면서 처음 방문한 곳 찾기
            while (node2 != 0) {
                if (visited[node2]) {
                    System.out.println(node2);
                    break;
                }
                node2 = parent[node2];
            }
        }
    }
}
