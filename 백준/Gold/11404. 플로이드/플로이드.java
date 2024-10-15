import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int val[][];
    static List<Node>[] list;
    static boolean[] visited;

    static class Node implements Comparable<Node> {
        int to;
        int value;

        public Node(int to, int value) {
            this.to = to;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return this.value - o.value;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        val = new int[n+1][n+1];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            list[a].add(new Node(b,c));

        }

        for(int i=1; i<=n; i++){
            visited = new boolean[n + 1];
            bfs(i);
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(j == n){
                    System.out.println(val[i][j]);
                } else{
                    System.out.print(val[i][j] + " ");
                }
            }
        }
    }

    private static void bfs(int a) {
        PriorityQueue<Node> p = new PriorityQueue<>();
        p.add(new Node(a,0));

        while (!p.isEmpty()){
            Node poll = p.poll();

            int x = poll.to;
            int count = poll.value;

            if(visited[x] == true) continue;
            visited[x] = true;
            val[a][x] = count;

            for(int i=0; i<list[x].size(); i++){
                Node next = list[x].get(i);

                if(visited[next.to] == false){
                    p.add(new Node(next.to, next.value+count));
                }
            }
        }

    }
}
