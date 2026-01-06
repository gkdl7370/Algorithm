import java.util.*;
import java.io.*;
public class Main {
    static int v, e, start;
    static int value[];
    static class Node implements Comparable<Node>{
        int point;
        int dist;
        Node(int point, int dist){
            this.point = point;
            this.dist = dist;

        }
        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
    static PriorityQueue<Node> q;
    static ArrayList<Node> list[];
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        value = new int[v+1];

        Arrays.fill(value,Integer.MAX_VALUE);

        start = Integer.parseInt(br.readLine());

        list = new ArrayList[v+1];
        for(int i=1; i<=v; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            list[x].add(new Node(y,dist));
        }

        search();

        for(int i=1; i<=v; i++){
            if(i == start){
                System.out.println(0);
                continue;
            }
            if(value[i] < Integer.MAX_VALUE) System.out.println(value[i]);
            else System.out.println("INF");
        }
    }

    private static void search(){
        q = new PriorityQueue<>();
        q.add(new Node(start,0));

        while(!q.isEmpty()){
            Node poll = q.poll();
            int now = poll.point;
            int dist = poll.dist;

            if(value[now] < dist) continue;
            
            for(int i=0; i<list[now].size(); i++){
                int next = list[now].get(i).point;
                int nextDist = list[now].get(i).dist;

                nextDist = dist + nextDist;

                if(value[next] > nextDist) {
                    value[next] = nextDist;
                    q.add(new Node(next,nextDist));
                }
            }


        }
    }
}
