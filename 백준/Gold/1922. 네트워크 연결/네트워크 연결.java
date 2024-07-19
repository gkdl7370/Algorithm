
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int parent[];
    static class Node implements Comparable<Node>{

        int start;
        int end;
        int val;

        public Node(int start, int end, int val){
            this.start = start;
            this.end = end;
            this.val = val;
        }
        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        parent = new int[n+1];
        for(int i=1; i<=n; i++){
            parent[i] = i;
        }

        PriorityQueue<Node> p = new PriorityQueue<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            p.add(new Node(a,b,c));
        }

        int count=0;
        int line=0;

        while (!p.isEmpty() && line < n-1){
            Node now = p.poll();

            int to = find(now.start);
            int from = find(now.end);

            if(!same(to,from)){
                count += now.val;
                union(to, from);
                line++;
            }

        }

        System.out.println(count);

    }

    private static void union(int x, int y) {
        int to = find(x);
        int from = find(y);

        if(to != from){
            parent[from] = to;
        }
    }

    private static boolean same(int x, int y) {
        int to = find(x);
        int from = find(y);

        return to == from;
    }

    private static int find(int x) {
        if (parent[x] == x){
            return x;
        } else{
            return parent[x] = find(parent[x]);
        }
    }
}
