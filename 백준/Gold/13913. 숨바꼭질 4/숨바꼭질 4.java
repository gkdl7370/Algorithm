
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int k;
    static boolean[] visited;
    static int[] dist;
    static int[] arr;
    static int[] check;
    static class Point implements Comparable<Point>{
        int end;
        int val;
        int num;
        public Point(int end, int val, int num){
            this.end = end;
            this.val = val;
            this.num = num;
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

        arr = new int[100000];
        if(n>k){
            visited = new boolean[n+2];
            dist = new int[n+2];
            check = new int[n+2];
        }else{
            visited = new boolean[k+2];
            dist = new int[k+2];
            check = new int[k+2];
        }

        dfs(n);

        System.out.println(dist[k]);

        ArrayList list = new ArrayList();
        int c = k;
        while (c != n){
            list.add(check[c]);
            c = check[c];
        }

        for(int i=list.size()-1; i>=0; i--){
            System.out.print(list.get(i) + " ");
        }

        System.out.print(k);

    }

    private static void dfs(int n) {
        PriorityQueue<Point> p = new PriorityQueue<>();
        p.add(new Point(n,0, 5));

        while (!p.isEmpty()){
            Point poll = p.poll();

            int now = poll.end;
            int val = poll.val;
            int num = poll.num;

            if(now>=visited.length || now<0){
                continue;
            }

            if(visited[now] == true) continue;

            visited[now] = true;
            dist[now] = val;
            check[now] = num;

            if(now == k){
                break;
            }

            p.add(new Point(now+1, dist[now]+1, now));
            p.add(new Point(now*2, dist[now]+1, now));
            p.add(new Point(now-1, dist[now]+1, now));

        }
    }
}
