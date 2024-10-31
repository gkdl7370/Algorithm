import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int v;
    static int e;
    static int start;
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
    static ArrayList<Node> list[];
    static int dist[];
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        list = new ArrayList[v+1];

        for(int i=1; i<=v; i++){
            list[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());

        start = Integer.parseInt(st.nextToken());

        for(int i=1; i<=e; i++){
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());

            list[x].add(new Node(y,z));
        }

        dist = new int[v+1];
        Arrays.fill(dist,Integer.MAX_VALUE);

        bfs();
        for(int i=1; i<=v; i++){
            if(i == start){
                System.out.println(0);
                continue;
            }
            if(dist[i] < Integer.MAX_VALUE) System.out.println(dist[i]);
            else System.out.println("INF");
        }

    }

    public static void bfs(){
        PriorityQueue<Node> p = new PriorityQueue<>();
        p.add(new Node(start,0));

        while (!p.isEmpty()){
            Node poll = p.poll();
            int x = poll.end;
            int len = poll.len;

            if(dist[x] < len) continue;

            for(int i=0; i<list[x].size(); i++){
                int nx = list[x].get(i).end;
                int nlen = list[x].get(i).len;

                if(dist[nx] > nlen + len){
                    dist[nx] = nlen + len;
                    p.add(new Node(nx, nlen+len));
                }

            }
        }

    }
}
