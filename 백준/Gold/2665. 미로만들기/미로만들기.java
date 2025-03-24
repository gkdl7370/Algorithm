import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int nx[] = {0,0,-1,1};//동서남북
    static int ny[] = {1,-1,0,0};
    static int n;
    static int max = Integer.MAX_VALUE;
    static int map[][];
    static boolean vit[][];
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

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        vit = new boolean[n][n];

        for(int i=0; i<n; i++){
            String num = br.readLine();
            for(int j=0; j<n; j++){
              int a = num.charAt(j);
              if(a == '1'){
                  map[i][j] = 0;
              } else{
                  map[i][j] = 1;
              }
            }
        }

        bfs(0,0);

        System.out.println(map[n-1][n-1]);
    }

    private static void bfs(int a, int b) {
        PriorityQueue<Point> q = new PriorityQueue<>();
        q.add(new Point(a,b,0));
        vit[a][b] = true;
        while (!q.isEmpty()){
            Point poll = q.poll();

            int x = poll.x;
            int y = poll.y;
            int c = poll.c;

            if(x == n-1 && y == n-1){
                map[x][y] = c;
                break;
            }


            for(int i=0; i<4; i++){
                int dx = x + nx[i];
                int dy = y + ny[i];

                if(dx<0 || dy<0 || dx>=n || dy>=n) continue;
                if(vit[dx][dy] == true) continue;

                if(map[dx][dy] == 1){
                    map[dx][dy] = map[dx][dy] + c;
                    q.add(new Point(dx, dy, map[dx][dy]));
                    vit[dx][dy] = true;
                } else{
                    q.add(new Point(dx, dy, c));
                    vit[dx][dy] = true;
                }

            }
        }
    }
}