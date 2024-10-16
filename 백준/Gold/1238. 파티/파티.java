import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int finish;
    static ArrayList<Node>[] list;
    static int dist[];
    static boolean visited[];
    static class Node implements Comparable<Node>{
        int end;
        int len;

        public Node(int end, int len){
            this.end = end;
            this.len = len;
        }

        @Override
        public int compareTo(Node o) {
            return this.len - o.len;
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        finish = Integer.parseInt(st.nextToken());

        dist = new int[n+1];
        list = new ArrayList[n+1];
        for(int i=0; i<list.length; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new Node(b,c));
        }

        for (int i=1; i<=n; i++){
            visited = new boolean[n+1];
            dfs(i);
        }

        Arrays.sort(dist);

        System.out.println(dist[n]);

    }

    private static void dfs(int a) {
        PriorityQueue<Node> p = new PriorityQueue<>();
        p.add(new Node(a,0));
        while (!p.isEmpty()){
            Node poll = p.poll();
            int x = poll.end;
            int count = poll.len;

            if(visited[x] == true) continue;
            visited[x] = true;
            if(x == finish){
                visited = new boolean[n+1];
                dist[a] = count;
                dfs2(a);
                return;
            }

            for(int i=0; i<list[x].size(); i++){
                Node next = list[x].get(i);
                if(next.end == a) continue;
                int nx = next.end;
                int nv = next.len;

                if(visited[nx] == true) continue;
                p.add(new Node(nx,nv+count));
            }
        }
    }

    private static void dfs2(int a) {
        PriorityQueue<Node> p = new PriorityQueue<>();
        p.add(new Node(finish,0));

        while (!p.isEmpty()){
            Node poll = p.poll();

            int x = poll.end;
            int count = poll.len;

            if(visited[x] == true) continue;
            visited[x] = true;
            if(x == a){
                dist[a] += count;
                return;
            }

            for(int i=0; i<list[x].size(); i++){
                Node next = list[x].get(i);
                int nx = next.end;
                int nv = next.len;

                if(visited[nx] == true) continue;
                p.add(new Node(nx,nv+count));
            }
        }
    }
}
