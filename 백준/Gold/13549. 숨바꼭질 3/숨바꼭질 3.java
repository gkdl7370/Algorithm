
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int k;
    static boolean visited[];
    static int dist[];
    static class Point implements Comparable<Point>{
        int end;
        int val;

        public Point(int end, int val){
            this.end = end;
            this.val = val;
        }

        @Override
        public int compareTo(Point o) {
            return this.val - o.val;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        if(n>=k){
            visited = new boolean[n+2];
            dist = new int[n+2];
        } else{
            visited = new boolean[k+2];
            dist = new int[k+2];
        }

        Arrays.fill(dist,Integer.MAX_VALUE);


        bfs(n,0);

        System.out.println(dist[k]);
    }

    private static void bfs(int num, int val) {
        PriorityQueue<Point> p = new PriorityQueue();
        p.add(new Point(num,val));

        while (!p.isEmpty()){
            Point poll = p.poll();
            int now = poll.end;
            int sum = poll.val;

            if(now == k){
                dist[now] = sum;
                break;
            }

            if(now>=visited.length || now<0){
                continue;
            }

            if(visited[now] == true) continue;
            visited[now] = true;
            dist[now] = sum;

            for(int i=0; i<3; i++){
                if(i==0){
                    int next = now-1;
                    int nVal = sum+1;
                    p.add(new Point(next,nVal));
                } else if(i==1){
                    int next = now*2;
                    int nVal = sum;
                    p.add(new Point(next,nVal));
                } else if(i==2){
                    int next = now+1;
                    int nVal = sum+1;
                    p.add(new Point(next,nVal));
                }
            }

        }

    }
}
