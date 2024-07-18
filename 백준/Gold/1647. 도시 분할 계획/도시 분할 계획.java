
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

        public Node(int a, int b, int c){
            this.start = a;
            this.end = b;
            this.val = c;
        }
        @Override
        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];

        for(int i=1; i<=n; i++){
            parent[i] = i;//최상위 노드 초기화
        }

        PriorityQueue<Node> p = new PriorityQueue<>();

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            p.add(new Node(start,end,val));
        }

        int sum=0;
        int max=0;
        int line=0;

        while (!p.isEmpty() && line < n-1){

            Node now = p.poll();

            int start = find(now.start);//부모노드 찾고
            int end = find(now.end);

            if(!same(start,end)){ //같이 안이어져있으면
                sum += now.val;
                union(start,end);
                line++;
                max = Math.max(max,now.val);
            }
        }

        System.out.println(sum-max);
    }

    private static void union(int start, int end) {

        if(start!=end){
            parent[end] = start;
        }
    }

    private static boolean same(int start, int end) {

        return start == end;
    }

    private static int find(int start) {
        if(parent[start] == start){
            return start;
        } else{
            int a = find(parent[start]);
            parent[start] = a;
            return a;
        }
    }
}
