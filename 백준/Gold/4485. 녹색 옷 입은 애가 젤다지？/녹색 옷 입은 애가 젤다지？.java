import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int dx[] = {1,0,-1,0};
    static int dy[] = {0,1,0,-1};
    static int map[][];
    static int dist[][];
    static int t,n;

    static class Point implements Comparable<Point>{
        int x;
        int y;
        int c;

        public Point(int x, int y, int c){
            this.x = x;
            this.y = y;
            this.c = c;
        }

        @Override
        public int compareTo(Point o) {
            return this.c - o.c;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        t = 1;
        while (true){

            n = Integer.parseInt(br.readLine());
            if (n==0) break;

            map = new int[n][n];
            dist = new int[n][n];

            for(int i=0; i<n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<n; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            dijkstra(0,0);

            System.out.println("Problem " + t +": " + dist[n-1][n-1]);
            t++;
        }


    }

    private static void dijkstra(int a, int b) {
        PriorityQueue<Point> p = new PriorityQueue<>();
        p.add(new Point(a,b,map[a][b]));
        dist[a][b] = map[a][b];

        while (!p.isEmpty()){
            Point poll = p.poll();
            int x = poll.x;
            int y = poll.y;
            int c = poll.c;

            if(dist[x][y] < c) continue; //지금 가격보다 크면 필요없음

            for(int i=0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx<0 || ny<0 || nx>=n || ny>=n) continue; //범위 체크

                int nextCost = map[nx][ny] + c;

                if(nextCost < dist[nx][ny]){
                    dist[nx][ny] = nextCost;
                    p.add(new Point(nx,ny,nextCost));
                }
            }
        }
    }

}
