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

            for(int k=1; k<=nodes; k++){
                if(color[k] == 0){
                    bfs(k);
                }
            }

            System.out.println(isBipartite ? "YES" : "NO");
        }
    }

    private static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        color[start] = 1; //시작노드는 1 반대노드는 2

        while (!q.isEmpty()){
            int num = q.poll();

            for(int i=0; i<graph[num].size(); i++){
                int nx = graph[num].get(i);

                if(color[nx] == color[num]){
                    isBipartite = false;
                    return;
                } else if(color[nx] == 0){
                    if(color[num] == 1){
                        color[nx] = 2;
                        q.add(nx);
                    } else {
                        color[nx] = 1;
                        q.add(nx);
                    }
                }
            }

        }
    }
}
