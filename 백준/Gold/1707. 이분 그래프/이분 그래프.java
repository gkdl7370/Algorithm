import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] color;
    static ArrayList<Integer>[] graph;
    static boolean isBipartite;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine()); // 테스트 케이스 수

        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int nodes = Integer.parseInt(st.nextToken());
            int edges = Integer.parseInt(st.nextToken());

            graph = new ArrayList[nodes + 1];
            color = new int[nodes + 1]; // 색상 배열 초기화 (0: 미방문, 1: 색1, 2: 색2)
            isBipartite = true;

            for (int j = 1; j <= nodes; j++) {
                graph[j] = new ArrayList<>();
            }

            for (int j = 0; j < edges; j++) {
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                graph[u].add(v);
                graph[v].add(u);
            }

            // 모든 컴포넌트를 검사하여 이분 그래프 판별
            for (int j = 1; j <= nodes; j++) {
                if (color[j] == 0) {
                    bfs(j); // 각 컴포넌트에 대해 BFS 탐색
                }
            }

            System.out.println(isBipartite ? "YES" : "NO");
        }
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        color[start] = 1; // 시작 노드에 색상 1 할당

        while (!queue.isEmpty() && isBipartite) {
            int node = queue.poll();

            for (int i = 0; i < graph[node].size(); i++) {
                int neighbor = graph[node].get(i);
                if (color[neighbor] == 0) {
                    color[neighbor] = (color[node] == 1) ? 2 : 1; // 인접 노드에 반대 색상 할당
                    queue.add(neighbor);
                } else if (color[neighbor] == color[node]) {
                    isBipartite = false;
                    return;
                }
            }
        }
    }
}
