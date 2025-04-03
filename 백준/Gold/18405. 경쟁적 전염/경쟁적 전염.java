import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int nx[] = {0,0,-1,1};//동서남북
    static int ny[] = {1,-1,0,0};
    static int n,k,s,x,y;
    static int map[][];
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
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        map = new int[n+1][n+1];

        PriorityQueue<Point> p = new PriorityQueue<>();

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] != 0){
                    p.add(new Point(i,j,map[i][j]));
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());

        bfs(p);

        System.out.println(map[x][y]);
    }

    private static void bfs(PriorityQueue<Point> p) {
        PriorityQueue<Point> p2 = new PriorityQueue<>();
        int num = p.size();
        for(int i=0; i<num; i++){
            p2.add(p.poll());
        }
        while (s>0){
            num = p2.size();
            for(int i=0; i<num; i++){
                p.add(p2.poll());
            }
            while (!p.isEmpty()){
                Point poll = p.poll();
                int x = poll.x;
                int y = poll.y;
                int c = poll.c;

                for(int i=0; i<4; i++){
                    int dx = x + nx[i];
                    int dy = y + ny[i];

                    if(dx<1 || dy<1 || dx>n || dy>n) continue;
                    if(map[dx][dy] != 0) continue;
                    map[dx][dy] = c;
                    p2.add(new Point(dx,dy,c));
                }
            }
            s--;
        }

    }
}
